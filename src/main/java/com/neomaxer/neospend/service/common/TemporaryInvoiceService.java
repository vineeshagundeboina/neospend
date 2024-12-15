package com.neomaxer.neospend.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.TemporaryInvoice;
import com.neomaxer.neospend.repositories.masters.TemporaryInvoiceRepo;

@Service
public class TemporaryInvoiceService extends GenericService<TemporaryInvoice> {

	public TemporaryInvoiceService(GenericRepository<TemporaryInvoice> repository) {
		super(repository);

	}
	
	@Autowired
	TemporaryInvoiceRepo temporaryInvoiceRepo;
//
//	@Transactional
//    public TemporaryInvoice createTemporaryInvoice(TemporaryInvoice temporaryInvoice) {
//        // Generate a unique invoice number
//        String invoiceNumber = generateInvoiceNumber();
//        temporaryInvoice.setInvoiceNo(invoiceNumber);
//
//        // Save the temporary invoice
//        temporaryInvoiceRepo.save(temporaryInvoice);
//
//        return temporaryInvoice;
//    }

//    private String generateInvoiceNumber() {
//        // Get the current year and the next year
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int nextYear = currentYear + 1;
//
//        // Find the highest invoice sequence number for the current year
//        Integer maxSequence = temporaryInvoiceRepo.findMaxInvoiceSequence(currentYear);
//        if (maxSequence == null) {
//            maxSequence = 0;
//        }
//
//        
//        int newSequence = maxSequence + 1;
//
//        // Create the invoice number
//        return String.format("INV-%02d-%02d-%04d", currentYear % 100, nextYear % 100, newSequence);
//    }
}