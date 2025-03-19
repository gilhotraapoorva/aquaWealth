package com.aquawealth.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_payments")
public class LoanPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "loanId", nullable = false)
    private com.aquawealth.model.Loan loan;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(length = 50)
    private String paymentType;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public com.aquawealth.model.Loan getLoan() {
        return loan;
    }

    public void setLoan(com.aquawealth.model.Loan loan) {
        this.loan = loan;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "loanPayment{" +
                "paymentId=" + paymentId +
                ", loan=" + loan +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
