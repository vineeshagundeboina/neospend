package com.neomaxer.neospend.service.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.exception.GenericException;
import com.neomaxer.neospend.models.masters.ExpenseHistory;
import com.neomaxer.neospend.models.masters.ExpenseInvoice;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.models.masters.RequestStatus;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.models.masters.Workflow;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseInvoiceRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseRequestRepo;

@Service
public class ExpenseRequestService extends GenericService<ExpenseRequest> {

	public ExpenseRequestService(GenericRepository<ExpenseRequest> repository) {

		super(repository);
	}

	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;

	@Autowired
	private ExpenseRequestRepo expenseRequestRepo;

	@Autowired
	private ExpenseInvoiceRepo expenseInvoiceRepo;

	private Workflow workflow;

	public ExpenseRequest updateRequestStatus(UUID expenseRequestId, RequestStatus newStatus) {

		ExpenseRequest expenseRequest = expenseRequestRepo.findById(expenseRequestId)
				.orElseThrow(() -> new EntityNotFoundException("ExpenseRequest not found"));

		if (expenseRequest.getRequestStatus() == RequestStatus.APPROVED
				|| expenseRequest.getRequestStatus() == RequestStatus.REJECTED) {
			throw new GenericException("Cannot update status for an already APPROVED or REJECTED request");
		}

		expenseRequest.setRequestStatus(newStatus);

		return expenseRequestRepo.save(expenseRequest);
	}

	public void updateExpenseRequestStatusAndCreateExpenseInvoice(UUID expenseRequestId) {

		ExpenseRequest expenseRequest = expenseRequestRepo.findById(expenseRequestId).orElseThrow(
				() -> new EntityNotFoundException("ExpenseRequest not found with ID: " + expenseRequestId));

		if (expenseRequest.getRequestStatus() == RequestStatus.APPROVED) {

			ExpenseInvoice expenseInvoice = new ExpenseInvoice();
			expenseInvoice.setId(expenseRequestId);
			expenseInvoice.setDescription(expenseRequest.getDescription());
			expenseInvoice.setExpenseDate(expenseRequest.getExpenseDate());

			expenseInvoice.setRequestAmount(expenseRequest.getRequestAmount());

			expenseInvoiceRepo.save(expenseInvoice);

			expenseRequest.setRequestStatus(RequestStatus.INVOICED);

			expenseRequestRepo.save(expenseRequest);
		}
	}

	public void saveExpenseRequest(ExpenseRequest expenseRequest) {

		ExpenseHistory expenseHistory = new ExpenseHistory();
		expenseHistory.setRequestStatus(expenseRequest.getRequestStatus());
		expenseHistory.setRequestNotes(expenseRequest.getRequestNotes());
		expenseHistory.setMakerRole(expenseRequest.getWorkflow().getMakerRole());
		expenseHistory.setCheckerRole(expenseRequest.getWorkflow().getCheckerRole());
		expenseHistory.setExpenseRequest(expenseRequest);
		ExpenseHistory response = expenseHistoryRepo.save(expenseHistory);
		System.out.println(response.toString());

	}


 /*public ExpenseRequest createExpenseRequest(ExpenseRequest expenseRequest) {
 UUID sanctionRequestId = expenseRequest.getSanctionRequest().getId();

		SanctionRequest sanctionRequest = sanctionRequestRepo.findById(sanctionRequestId)
				.orElseThrow(() -> new GenericException("SanctionRequest not found"));

		if (sanctionRequest.getSanctionStatus().equals("APPROVED")
				&& expenseRequest.getAmount().compareTo(sanctionRequest.getSanctionAmount()) <= 0) {

		DelegationRole delegationRole = delegationRoleRepo.findByDelegationName("A_level")
				.orElseThrow(() -> new GenericException("DelegationRole not found"));
		BigDecimal approvalAmount = delegationRole.getDelegationAmount();

		if (expenseRequest.getAmount().compareTo(approvalAmount) >= 0) {

			if (expenseRequest.getRequestStatus() != null && expenseRequest.getRequestStatus().contains("DR")) {
				expenseRequest.setRequestStatus("FWD");

			}
		} else {
			if (expenseRequest.getRequestStatus() != null && expenseRequest.getRequestStatus().contains("DR")) {
				expenseRequest.setRequestStatus("APPROVED");

			}
		}

		List<String> existingAttachments = expenseRequest.getAttachments();
		if (expenseRequest.getAttachments() != null && !expenseRequest.getAttachments().isEmpty()) {
			List<String> newAttachments = expenseRequest.getAttachments();
			List<String> mergedAttachments = mergeAttachments(existingAttachments, newAttachments);
			expenseRequest.setAttachments(mergedAttachments);
		}

		ExpenseRequest response = expenseRequestRepo.save(expenseRequest);
		updateTotalAmount(response);
		saveExpenseRequest(response);
		return response;

		}  
		else {
			throw new GenericException("ExpenseRequest creation not allowed because RequestAmount is greater than Sanction Amount");
		
		}
 }

	private List<String> mergeAttachments(List<String> existingAttachments, List<String> newAttachments) {
		List<String> mergedAttachments = new ArrayList<>(existingAttachments);
		for (String attachment : newAttachments) {
			if (!mergedAttachments.contains(attachment)) {
				mergedAttachments.add(attachment);
			}
		}
		return mergedAttachments;
	}*/

	/*public ExpenseRequest createRejectExpenseRequest(ExpenseRequest expenseRequest) {
		if (expenseRequest.getRequestStatus() != null && (expenseRequest.getRequestStatus().contains("FWD")
				|| expenseRequest.getRequestStatus().contains("DR"))) {
			expenseRequest.setRequestStatus("REJECT");

		}

		return expenseRequestRepo.save(expenseRequest);
	}*/

	public ExpenseRequest uploadAttachment(UUID expenseRequestId, MultipartFile[] files) {
		ExpenseRequest expenseRequest = expenseRequestRepo.findById(expenseRequestId)
				.orElseThrow(() -> new GenericException("ExpenseRequest not found"));

    	List<String> existingAttachments = expenseRequest.getAttachments();
		List<String> newAttachments = new ArrayList<>();

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				String attachmentUrl = saveAttachment(file);
				if (!existingAttachments.contains(attachmentUrl)) {
					newAttachments.add(attachmentUrl);
				}
			}
		}
		return expenseRequest;
		
	}

//		List<String> mergedAttachments = mergeAttachments(existingAttachments, newAttachments);
//		expenseRequest.setAttachments(mergedAttachments);
//		expenseRequestRepo.save(expenseRequest);
//
//		return expenseRequest;
//	}

	/*private List<String> mergeAttachments(List<String> existingAttachments, List<String> newAttachments) {
		// TODO Auto-generated method stub
		return null;
	}*/

	private String getAttachmentUrl(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		return fileName;
	}

	private String saveAttachment(MultipartFile file) {
		try {
			String attachmentUrl = getAttachmentUrl(file);
			String storageLocation = "C:\\splenta\\file_upload6666";
			
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


}
