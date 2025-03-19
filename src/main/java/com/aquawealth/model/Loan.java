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
