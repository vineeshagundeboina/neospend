package com.neomaxer.neospend.repositories.masters;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, UUID>, JpaSpecificationExecutor<Country> {

	@Query("select s from Country s where (lower(s.countryCode) like lower(concat('%', :str, '%')) or lower(s.countryName) like lower(concat('%', :str, '%'))) and s.isActive = :active")
	Page<Country> getAllCountry(@Param("str") String str, @Param("active") boolean active, Pageable pageable);

}
