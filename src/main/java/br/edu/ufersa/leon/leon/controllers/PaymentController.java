package br.edu.ufersa.leon.leon.controllers;

import br.edu.ufersa.leon.leon.dtos.payment.PaymentReportDTO;
import br.edu.ufersa.leon.leon.services.PaymentService;
import br.edu.ufersa.leon.leon.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final UserService userService;
    private final PaymentService paymentService;

    public PaymentController(UserService userService, PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentReportDTO>> findAll() {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var month = LocalDate.now().getMonthValue();
        return userService.findUserByEmail(userEmail)
                .map(user -> ResponseEntity.ok(paymentService.getPaymentsBetween(user, month, month)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PaymentReportDTO>> findAllBetween(
            @RequestParam(value = "initialMonth") int initialMonth,
            @RequestParam(value = "finalMonth") int finalMonth
    ) {
        var userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findUserByEmail(userEmail)
                .map(user -> ResponseEntity.ok(paymentService.getPaymentsBetween(user, initialMonth, finalMonth)))
                .orElse(ResponseEntity.notFound().build());
    }
}
