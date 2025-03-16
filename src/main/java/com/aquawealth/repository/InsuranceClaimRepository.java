package com.aquawealth.repository;

import com.aquawealth.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Long> {

}
