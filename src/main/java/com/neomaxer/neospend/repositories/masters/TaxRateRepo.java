package com.neomaxer.neospend.repositories.masters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.TaxRate;

@Repository
public interface TaxRateRepo extends GenericRepository<TaxRate	> {

	TaxRate findByTaxRateName(String taxCode);
	
	@Query("select t from TaxRate t where (lower(CAST(t.gst AS text)) like lower(concat('%', :str, '%')) ) and t.isActive = :active")
	Page<TaxRate> getAllTax(@Param("str") String str, @Param("active") boolean active, Pageable pageable);

}
