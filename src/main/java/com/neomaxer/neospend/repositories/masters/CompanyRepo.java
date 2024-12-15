package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {

	@Query("select s from Company s where (lower(s.companyName) like lower(concat('%', :str, '%')) or lower(s.companyLocation) like lower(concat('%', :str, '%'))) and s.isActive = :active")
	Page<Company> getAllCompany(@Param("str") String str, @Param("active") boolean active, Pageable pageable);

	Optional<Company> findByCompanyPanNumber(String companyPanNumber);

}
