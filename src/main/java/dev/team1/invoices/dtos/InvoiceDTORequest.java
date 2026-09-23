package dev.team1.invoices.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record InvoiceDTORequest(
  @NotBlank(message = "El campo 'invoice_number no debe de ser vacío")
  UUID invoiceNumber
) {

}
