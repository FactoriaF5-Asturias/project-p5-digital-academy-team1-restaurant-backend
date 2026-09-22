package dev.team1.invoices.dtos;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;

@Builder 
public record InvoiceDTOResponse(
  Long id,
  Long orderId,
  String invoiceNumber,
  BigDecimal amount,
  Instant paiAat
) {

}
