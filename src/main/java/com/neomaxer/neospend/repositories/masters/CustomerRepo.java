package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.masters.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID>,JpaSpecificationExecutor<Customer> {

    Optional<Customer> findByPhone(String phone);
   
    Page<Customer> findAllByCompanyId(UUID companyId, PageRequest pageRequest);

	Customer findByUser(User custUser);
 
	
	@Query("select c from Customer c where (lower(c.firstName) like lower(concat('%', :str, '%')) or lower(c.lastName) like lower(concat('%', :str, '%')) or lower(c.email) like lower(concat('%', :str, '%')) or lower(c.phone) like lower(concat('%', :str, '%')) ) and c.isActive =:active ")
	Page<Customer> getAllCustomers(@Param("str") String str, @Param("active") boolean active, Pageable pageable);
}
