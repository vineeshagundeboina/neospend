package com.neomaxer.neospend.repositories.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neomaxer.neospend.models.masters.InvoiceSequence;

@Repository
public interface InvoiceSequenceRepo extends JpaRepository<InvoiceSequence, Long> {
    InvoiceSequence findByPrefix(String prefix);

}
