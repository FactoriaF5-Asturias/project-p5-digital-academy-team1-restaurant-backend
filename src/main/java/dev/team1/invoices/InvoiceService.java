package dev.team1.invoices;

import org.springframework.stereotype.Service;

@Service 
public class InvoiceService {
  private final InvoiceRepository invoiceRepository;

  public InvoiceService(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }
}
