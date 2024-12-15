package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.auth.Profile;

public interface ProfileRepo extends GenericRepository<Profile> {

	Optional<Profile> findByemail(String email);

	Optional<Profile> findByphone(String mobileNumber);
	
	Profile findByUserId(UUID userId);


//	Optional<Profile> findByIdAndMpin(UUID userId, String mpin);

	//Optional<Profile> findByphoneAndemail(String phone, String email);

}
