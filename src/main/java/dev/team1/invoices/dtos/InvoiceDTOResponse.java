package dev.team1.invoices.dtos;

import java.math.BigDecimal;
import java.time.Instant;

import dev.team1.orders.OrderEntity;
import lombok.Builder;

@Builder 
public record InvoiceDTOResponse(
  Long id,
  OrderEntity order,
  String invoice_number,
  BigDecimal amount,
  Instant paid_at
) {
  
}
