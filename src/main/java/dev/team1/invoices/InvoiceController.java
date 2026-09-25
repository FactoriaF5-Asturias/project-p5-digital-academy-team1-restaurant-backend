package dev.team1.invoices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.team1.contracts.IInvoiceService;
import dev.team1.invoices.dtos.InvoiceDTORequest;
import dev.team1.invoices.dtos.InvoiceDTOResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "${api-endpoint}/invoices")
public class InvoiceController {
  private final IInvoiceService invoiceService;

  public InvoiceController(IInvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping("")
  public ResponseEntity<InvoiceDTOResponse> create(@Valid @RequestBody InvoiceDTORequest dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<InvoiceDTOResponse> findById(@PathVariable Long id) {
      return ResponseEntity.ok(invoiceService.findById(id));
  }
}
