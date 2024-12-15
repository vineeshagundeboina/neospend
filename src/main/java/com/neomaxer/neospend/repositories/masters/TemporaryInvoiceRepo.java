package com.neomaxer.neospend.repositories.masters;

import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.TemporaryInvoice;

@Repository
public interface TemporaryInvoiceRepo extends GenericRepository<TemporaryInvoice> {

	

	//Integer findMaxInvoiceSequence(int currentYear);

//	@Query("SELECT MAX(ti.invoiceSequence) FROM TemporaryInvoice ti WHERE YEAR(ti.invoiceDate) = :currentYear")
//	Integer findMaxInvoiceSequence(@Param("currentYear") int currentYear);
}
