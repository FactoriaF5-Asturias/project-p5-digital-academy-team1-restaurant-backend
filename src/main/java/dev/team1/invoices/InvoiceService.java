package dev.team1.invoices;

import org.springframework.stereotype.Service;

import dev.team1.invoices.dtos.InvoiceDTORequest;

@Service 
public class InvoiceService {
  private final InvoiceRepository invoiceRepository;

  public InvoiceService(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }
  
}
