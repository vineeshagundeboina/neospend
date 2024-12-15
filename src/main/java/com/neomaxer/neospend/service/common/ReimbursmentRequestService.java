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
import com.neomaxer.neospend.models.masters.ExpenseHistory;
import com.neomaxer.neospend.models.masters.ReimbursmentRequest;
import com.neomaxer.neospend.models.masters.RequestStatus;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.repositories.masters.DelegationRoleRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;
import com.neomaxer.neospend.repositories.masters.ReimbursmentRequestRepo;
import com.neomaxer.neospend.repositories.masters.SanctionRequestRepo;

@Service
public class ReimbursmentRequestService extends GenericService<ReimbursmentRequest> {
	public ReimbursmentRequestService(GenericRepository<ReimbursmentRequest> repository) {
		super(repository);
	}

	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;

	@Autowired
	private SanctionRequestRepo sanctionRequestRepo;

	@Autowired
	private ReimbursmentRequestRepo reimbursmentRequestRepo;

	@Autowired
	private DelegationRoleRepo delegationRoleRepo;

	public ReimbursmentRequest createReimbursmentRequest(ReimbursmentRequest reimbursmentRequest) {
		UUID sanctionRequestId = reimbursmentRequest.getSanctionRequest().getId();

		List<String> existingAttachments = reimbursmentRequest.getAttachments();
		if (reimbursmentRequest.getAttachments() != null && !reimbursmentRequest.getAttachments().isEmpty()) {
			List<String> newAttachments = reimbursmentRequest.getAttachments();
			List<String> mergedAttachments = mergeAttachments(existingAttachments, newAttachments);
			reimbursmentRequest.setAttachments(mergedAttachments);
		}

		ReimbursmentRequest response = reimbursmentRequestRepo.save(reimbursmentRequest);
		//saveReimbursmentRequest(response);
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

	public ReimbursmentRequest createRejectReimbursmentRequest(ReimbursmentRequest reimbursmentRequest) {
		if (reimbursmentRequest.getRequestStatus().toString() != null
				&& (reimbursmentRequest.getRequestStatus().toString().contains("FORWARDED")
						|| reimbursmentRequest.getRequestStatus().toString().contains("DRAFT"))) {
			reimbursmentRequest.setRequestStatus(RequestStatus.REJECTED);
			reimbursmentRequest.setForward(false);
			reimbursmentRequest.setReject(true);
			reimbursmentRequest.setApprove(false);
		}

		return reimbursmentRequestRepo.save(reimbursmentRequest);
	}

	public ReimbursmentRequest uploadAttachment(UUID reimbursmentRequestId, MultipartFile[] files) {
		ReimbursmentRequest reimbursmentRequest = reimbursmentRequestRepo.findById(reimbursmentRequestId)
				.orElseThrow(() -> new GenericException("ExpenseRequest not found"));

		List<String> existingAttachments = reimbursmentRequest.getAttachments();
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
		reimbursmentRequest.setAttachments(mergedAttachments);
		reimbursmentRequestRepo.save(reimbursmentRequest);

		return reimbursmentRequest;
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

	public void saveReimbursmentRequest(ReimbursmentRequest reimbursmentRequest) {

		ExpenseHistory expenseHistory = new ExpenseHistory();
		expenseHistory.setRequestStatus(reimbursmentRequest.getRequestStatus());
		expenseHistory.setRequestAmount(reimbursmentRequest.getReimbursmentAmount());

		expenseHistory.setRequestNotes(reimbursmentRequest.getRequestNotes());
		expenseHistory.setMakerRole(reimbursmentRequest.getWorkflow().getMakerRole());
		expenseHistory.setCheckerRole(reimbursmentRequest.getWorkflow().getCheckerRole());

		expenseHistory.setReimbursmentRequest(reimbursmentRequest);
		expenseHistoryRepo.save(expenseHistory);
		//System.out.println(response.toString());

	}

}
