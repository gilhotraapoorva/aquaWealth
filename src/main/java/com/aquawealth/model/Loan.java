package com.aquawealth.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Corrected: Now properly linked to User entity

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    private double interestRate;

    private int termMonths;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCollateralDetails() {
        return collateralDetails;
    }

    public void setCollateralDetails(String collateralDetails) {
        this.collateralDetails = collateralDetails;
    }

    @Column(length = 255)
    private String purpose;

    @Column(length = 255)
    private String collateralDetails;

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", user=" + (user != null ? user.getUserId() : null) +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                ", purpose='" + purpose + '\'' +
                ", collateralDetails='" + collateralDetails + '\'' +
                '}';
    }


}
