package com.aquawealth.service;

import com.aquawealth.model.Loan;
import com.aquawealth.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan applyForLoan(Loan loan){
        loan.setTermMonths(loan.getTermMonths() * 12);
        loan.setInterestRate(loan.getCollateralDetails() != null ? 12.1 : 17);
        return loanRepository.save(loan);
    }
}
