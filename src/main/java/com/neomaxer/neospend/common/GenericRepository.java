package com.neomaxer.neospend.common;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.neomaxer.neospend.models.common.BaseEntity;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

}
