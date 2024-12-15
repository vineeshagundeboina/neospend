package com.neomaxer.neospend.repositories.masters;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.Setting;

@Repository
public interface SettingRepo extends JpaRepository<Setting, UUID>, JpaSpecificationExecutor<Setting> {

//	Optional<Setting> findBySettingKeyAndStoreId(String key, UUID storeId);
//
//	Optional<Setting> findBySettingKeyAndDistributorId(String key, UUID distributorId);
//
//	Optional<Setting> findFirstBySettingKeyAndStoreIsNullAndDistributorIsNull(String settingKey);
//
//	Page<Setting> findByStoreIsNullAndDistributorIsNull(PageRequest of);
//
//	List<Setting> findByStoreIdAndSettingKey(UUID storeId, String settingKey);
//
//	List<Setting> findByDistributorIdAndSettingKey(UUID distributorId, String settingKey);

//	Optional<Setting> findBySettingKeyAndStoreId(String key, UUID storeId);
//
//	Optional<Setting> findBySettingKeyAndDistributorId(String key, UUID distributorId);
//
//	Optional<Setting> findFirstBySettingKeyAndStoreIsNullAndDistributorIsNull(String settingKey);
//
//	Page<Setting> findByStoreIsNullAndDistributorIsNull(PageRequest of);
//
//	List<Setting> findByStoreIdAndSettingKey(UUID storeId, String settingKey);
//
//	List<Setting> findByDistributorIdAndSettingKey(UUID distributorId, String settingKey);

}
