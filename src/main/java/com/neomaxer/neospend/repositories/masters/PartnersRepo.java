package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.Partner;

@Repository
public interface PartnersRepo extends JpaRepository<Partner, UUID>, JpaSpecificationExecutor<Partner> {

	Optional<Partner> findByPartnerCode(String partnerCode);

	Optional<Partner> findByPanNumberContainingIgnoreCase(String panNumber);

}
