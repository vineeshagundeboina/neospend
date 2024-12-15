package com.neomaxer.neospend.repositories.common;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.auth.Role;
import com.neomaxer.neospend.models.common.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, UUID> {

    public Page<Notification> findByRoleIn(Collection<Role> roles, PageRequest of);
}
