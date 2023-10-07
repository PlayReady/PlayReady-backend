package com.playready.PlayReadyBackend.repository;

import com.playready.PlayReadyBackend.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {
}
