package com.playready.PlayReadyBackend.repository;

import com.playready.PlayReadyBackend.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {


    @Query("SELECT i.file FROM Invoice i WHERE i.id = :invoiceId")
    byte[] findFileById(Long invoiceId);
}
