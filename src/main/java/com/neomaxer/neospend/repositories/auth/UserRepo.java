package com.neomaxer.neospend.repositories.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.auth.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>,JpaSpecificationExecutor<User> {

	Optional<User> findByUserName(String username);

	Optional<User> findByUserNameAndPassword(String username, String Password);

	Optional<User> findByResetToken(String token);

    Optional<User> findByActivationToken(String token);

    Optional<User> findFirstByEmail(String email);

	User findByMobileNumber(String phone);

	Optional<User> findByIdAndMpin(UUID userId, String mpin);

	Optional<User> findByMobileNumberOrEmail(String mobileNumber, String email);
	
	/*@Query("select s from User s where (lower(s.email) like lower(concat('%', :str, '%')) or lower(s.userName) like lower(concat('%', :str, '%'))) and s.isActive = :active ")
	Page<User> getAllUser(@Param("str") String str, @Param("active") boolean active, Pageable pageable);*/
	
	@Query("SELECT DISTINCT u FROM User u JOIN u.roles r WHERE (LOWER(u.email) LIKE LOWER(CONCAT('%', :str, '%')) OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :str, '%'))) AND u.isActive = :active AND LOWER(r.name) LIKE LOWER(CONCAT('%', :role, '%'))")
	Page<User> getAllUser(@Param("str") String str, @Param("active") boolean active, @Param("role") String role, Pageable pageable);

//	Page<User> findAllByCompanyId(UUID companyId, PageRequest of);

}
