package com.aquawealth.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aquawealth.model.Loan;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
}
