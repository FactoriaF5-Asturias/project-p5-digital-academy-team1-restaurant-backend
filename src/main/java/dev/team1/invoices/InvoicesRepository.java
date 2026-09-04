package dev.team1.invoices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesRepository extends JpaRepository<InvoiceEntity, Long>{

}
