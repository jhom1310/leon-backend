package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.payment.PaymentReportDTO;
import br.edu.ufersa.leon.leon.entities.Payment;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentReportDTO> getPaymentsBetween(User user, int initialMonth, int finalMonth) {
        var initialDate = LocalDate.now().withMonth(initialMonth).withDayOfMonth(1);
        var firstDayAtFinalMonth = initialDate.withMonth(finalMonth);
        var finalDate = firstDayAtFinalMonth.withDayOfMonth(firstDayAtFinalMonth.lengthOfMonth());
        var payments = paymentRepository.getPaymentsBetween(user.getId(), initialDate, finalDate);
        return mapPaymentsToReports(payments);
    }

    private List<PaymentReportDTO> mapPaymentsToReports(List<Payment> payments) {
        return payments.stream()
                .collect(Collectors.groupingBy(payment -> payment.getPaymentDay().getMonth()))
                .entrySet().stream()
                .map(entry -> PaymentReportDTO.from(entry.getKey().getValue(), sortPayments(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<Payment> sortPayments(List<Payment> payments) {
        var comparator = Comparator.comparing(Payment::getPaymentDay);
        return payments.stream().sorted(comparator).collect(Collectors.toList());
    }
}
