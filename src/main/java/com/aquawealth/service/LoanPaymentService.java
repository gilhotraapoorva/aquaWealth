package com.aquawealth.service;

import com.aquawealth.model.Loan;
import com.aquawealth.model.LoanPayment;
import com.aquawealth.repository.LoanPaymentRepository;
import com.aquawealth.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LoanPaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanPaymentRepository loanPaymentRepository;

    public LoanPayment makePayment(Long loanId,BigDecimal amount, String paymentType){
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            throw new RuntimeException("Loan not found");
        }
        LoanPayment payment = new LoanPayment();
        payment.setLoan(loanOpt.get());
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);

        return loanPaymentRepository.save(payment);
    }
}
