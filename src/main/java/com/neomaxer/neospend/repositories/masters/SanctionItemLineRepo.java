package com.neomaxer.neospend.repositories.masters;

import java.util.List;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.SanctionItemLine;
import com.neomaxer.neospend.models.masters.SanctionRequest;

public interface SanctionItemLineRepo extends GenericRepository<SanctionItemLine> {
	List<SanctionItemLine> findBySanctionRequest(SanctionRequest sanctionRequest);

}
