package com.aquawealth.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aquawealth.model.LoanPayment;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
}
