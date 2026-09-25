package dev.team1.invoices;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

  public Optional<InvoiceEntity> findByInvoice_number(UUID invoiceNumber);

  boolean existsByInvoiceNumber(UUID invoiceNumber);
}
