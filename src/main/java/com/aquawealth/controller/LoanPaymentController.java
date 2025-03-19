package com.aquawealth.controller;

import com.aquawealth.model.LoanPayment;
import com.aquawealth.service.LoanPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/payements")
public class LoanPaymentController {

    @Autowired
    private LoanPaymentService loanPaymentService;

    @GetMapping("/make")
    public String showPaymentForm() {
        return "make-payment";
    }

    @PostMapping("/process-payment")
    public String processPayment(
            @RequestParam Long loanId,
            @RequestParam BigDecimal amount,
            @RequestParam String paymentType,
            Model model
    ) {
        LoanPayment payment = loanPaymentService.makePayment(loanId, amount, paymentType);
        model.addAttribute("payment", payment);
        return "payment-success";
    }
}
