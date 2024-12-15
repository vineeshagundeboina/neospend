package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.ExpenseHistory;
@Repository
public interface ExpenseHistoryRepo extends JpaRepository<ExpenseHistory, UUID> {

	Optional<ExpenseHistory> findById(UUID requestId);
}
