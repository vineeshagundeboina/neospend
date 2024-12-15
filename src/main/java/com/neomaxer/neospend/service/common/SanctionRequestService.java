
package com.neomaxer.neospend.service.common;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.exception.GenericException;
import com.neomaxer.neospend.models.masters.DelegationRole;
import com.neomaxer.neospend.models.masters.RequestStatus;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.repositories.masters.DelegationRoleRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;
import com.neomaxer.neospend.repositories.masters.SanctionRequestRepo;

@Service
public class SanctionRequestService extends GenericService<SanctionRequest> {
	public SanctionRequestService(GenericRepository<SanctionRequest> repository) {
		super(repository);

	}

	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;

	@Autowired
	DelegationRoleRepo delegationRoleRepo;

	@Autowired
	SanctionRequestRepo sanctionRequestRepo;

	public SanctionRequest createSanctionRequest(SanctionRequest sanctionRequest) {

		DelegationRole delegationRole = delegationRoleRepo.findByDelegationName("A_level")
				.orElseThrow(() -> new GenericException("DelegationRole not found"));
		BigDecimal approvalAmount = delegationRole.getDelegationAmount();

		if (sanctionRequest.getSanctionAmount().compareTo(approvalAmount) >= 0) {

			if (sanctionRequest.getRequestStatus().toString() != null && sanctionRequest.getRequestStatus().toString().contains("DR")) {
				sanctionRequest.setRequestStatus(RequestStatus.FORWARD);
				sanctionRequest.setForward(true);
				sanctionRequest.setReject(false);
				sanctionRequest.setApprove(false);
			}
		} else {
			if (sanctionRequest.getRequestStatus().toString() != null && sanctionRequest.getRequestStatus().toString().contains("DR")) {
				sanctionRequest.setRequestStatus(RequestStatus.APPROVED);
				sanctionRequest.setForward(true);
				sanctionRequest.setReject(false);
				sanctionRequest.setApprove(false);
			}
		}

		List<String> existingAttachments = sanctionRequest.getAttachments();
		if (sanctionRequest.getAttachments() != null && !sanctionRequest.getAttachments().isEmpty()) {
			List<String> newAttachments = sanctionRequest.getAttachments();
			List<String> mergedAttachments = mergeAttachments(existingAttachments, newAttachments);
			sanctionRequest.setAttachments(mergedAttachments);
		}

		SanctionRequest response = sanctionRequestRepo.save(sanctionRequest);
		//saveSanctionRequest(response);
		return response;

	}

	private List<String> mergeAttachments(List<String> existingAttachments, List<String> newAttachments) {
		List<String> mergedAttachments = new ArrayList<>(existingAttachments);
		for (String attachment : newAttachments) {
			if (!mergedAttachments.contains(attachment)) {
				mergedAttachments.add(attachment);
			}
		}
		return mergedAttachments;
	}

	public SanctionRequest createRejectSanctionRequest(SanctionRequest sanctionRequest) {
		if (sanctionRequest.getRequestStatus().toString() != null && (sanctionRequest.getRequestStatus().toString().contains("FWD")
				|| sanctionRequest.getRequestStatus().toString().contains("DR"))) {
			sanctionRequest.setRequestStatus(RequestStatus.REJECTED);
			sanctionRequest.setForward(false);
			sanctionRequest.setReject(true);
			sanctionRequest.setApprove(false);
		}

		return sanctionRequestRepo.save(sanctionRequest);
	}

	public SanctionRequest uploadAttachment(UUID sanctionRequestId, MultipartFile[] files) {
		SanctionRequest sanctionRequest = sanctionRequestRepo.findById(sanctionRequestId)
				.orElseThrow(() -> new GenericException("SanctionRequest not found"));

		List<String> existingAttachments = sanctionRequest.getAttachments();
		List<String> newAttachments = new ArrayList<>();

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				String attachmentUrl = saveAttachment(file);
				if (!existingAttachments.contains(attachmentUrl)) {
					newAttachments.add(attachmentUrl);
				}
			}
		}

		List<String> mergedAttachments = mergeAttachments(existingAttachments, newAttachments);
		sanctionRequest.setAttachments(mergedAttachments);
		sanctionRequestRepo.save(sanctionRequest);

		return sanctionRequest;
	}

	private String getAttachmentUrl(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		return fileName;
	}

	private String saveAttachment(MultipartFile file) {
		try {
			String attachmentUrl = getAttachmentUrl(file);
			String storageLocation = "C:\\splenta\\file_upload";
			File directory = new File(storageLocation);
			if (!directory.exists()) {
				FileUtils.forceMkdir(directory);
			}

			File targetFile = new File(directory, attachmentUrl);
			file.transferTo(targetFile);

			return attachmentUrl;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

//	public void saveSanctionRequest(SanctionRequest sanctionRequest) {
//
//		ExpenseHistory expenseHistory = new ExpenseHistory();
//		expenseHistory.setRequestStatus(sanctionRequest.getRequestStatus());
//		expenseHistory.setRequestNotes(sanctionRequest.getNotes());
//		expenseHistory.setMakerRole(sanctionRequest.getWorkflow().getMakerRole());
//		expenseHistory.setCheckerRole(sanctionRequest.getWorkflow().getCheckerRole());
//		// expenseHistory.SetSanctionRequest(sanctionRequest);
//		expenseHistory.setSanctionRequest(sanctionRequest);
//		ExpenseHistory response = expenseHistoryRepo.save(expenseHistory);
//		System.out.println(response.toString());
//
//		// return response;
//	}

}
