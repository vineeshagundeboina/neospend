package com.neomaxer.neospend.repositories.masters;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.ExpenseCategory;

public interface ExpenseCategoryRepo extends GenericRepository<ExpenseCategory> {
	
	 @Query("SELECT ec FROM ExpenseCategory ec WHERE ec.id = :categoryId")
	    ExpenseCategory findByIdWithoutParent(@Param("categoryId") UUID categoryId);
	}


