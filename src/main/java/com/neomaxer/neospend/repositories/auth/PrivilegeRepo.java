package com.neomaxer.neospend.repositories.auth;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.auth.Privilege;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, UUID>,  JpaSpecificationExecutor<Privilege>  {

	Privilege findByName(String name);
	
	@Query("select s from Privilege s where (lower(s.name) like lower(concat('%', :str, '%')) or lower(s.controllerIdentifier) like lower(concat('%', :str, '%')) or lower(s.methodIdentifier) like lower(concat('%', :str, '%')) or  lower(s.apiPath) like lower(concat('%', :str, '%')) or  lower(s.description) like lower(concat('%', :str, '%'))) and s.isActive = :active")
	Page<Privilege> getAllPrivilege(@Param("str") String str, @Param("active") boolean active, Pageable pageable);

}
