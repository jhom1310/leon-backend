package br.edu.ufersa.leon.leon.dtos.payment;

import br.edu.ufersa.leon.leon.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReportDTO {
    private int month;
    private List<PaymentDTO> payments;

    public static PaymentReportDTO from(int month, List<Payment> payments) {
        var dto = new PaymentReportDTO();
        dto.setMonth(month);
        var paymentsDTOs = payments.stream().map(PaymentDTO::fromEntity).collect(Collectors.toList());
        dto.setPayments(paymentsDTOs);
        return dto;
    }
}
