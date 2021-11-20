package br.edu.ufersa.leon.leon.dtos.payment;

import br.edu.ufersa.leon.leon.entities.Payment;
import br.edu.ufersa.leon.leon.entities.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDTO {
    private int day;
    private String classe;
    private double cost;
    private PaymentStatus status;

    static PaymentDTO fromEntity(Payment payment) {
        var dto = new PaymentDTO();
        dto.setDay(payment.getPaymentDay().getDayOfMonth());
        dto.setClasse(payment.getClasse().getModality().getName());
        dto.setCost(payment.getCost());
        dto.setStatus(payment.getStatus());
        return dto;
    }
}
