package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;
import java.util.UUID;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.models.masters.SanctionRequest;

public interface ExpenseRequestRepo extends GenericRepository<ExpenseRequest> {

  //SanctionRequest findSanctionRequestByIDandSanctionStatus(String userName, ExpenseType expenseType);

	
	SanctionRequest findSanctionRequestById(UUID sanctionRequestId);

	ExpenseRequest findByRequestStatus(String string);

	Optional<ExpenseRequest> findById(UUID expenseRequestId);
}
