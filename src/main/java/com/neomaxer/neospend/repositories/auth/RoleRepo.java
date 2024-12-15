package com.neomaxer.neospend.repositories.auth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.auth.Role;

public interface RoleRepo extends GenericRepository<Role> {

	Page<Role> findAllByRoleType(String roleType, PageRequest of);

    Role findByname(String retailerRoleName);
//    Role findByRoleName(String roleName);
    
    @Query("select s from Role s where (lower(s.description) like lower(concat('%', :str, '%'))) and s.isActive = :active")
	Page<Role> getAllRole(@Param("str") String str, @Param("active") boolean active, Pageable pageable);

}
