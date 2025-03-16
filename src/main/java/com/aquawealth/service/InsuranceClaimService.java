//package com.aquawealth.service;
//
//import com.aquawealth.model.ClaimStatus;
//import com.aquawealth.model.InsuranceClaim;
//import com.aquawealth.model.InsurancePolicy;
//import com.aquawealth.model.User;
//import com.aquawealth.repository.InsuranceClaimRepository;
//import com.aquawealth.repository.InsurancePolicyRepository;
//import com.aquawealth.repository.UserRepository;
//import com.aquawealth.util.WeatherAPIUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Optional;
//import java.util.List;
//
//@Service
//public class InsuranceClaimService {
//
//    @Autowired
//    private InsuranceClaimRepository claimRepository;
//
//    @Autowired
//    private InsurancePolicyRepository policyRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private WeatherAPIUtil weatherAPIUtil;
//
//    public List<InsuranceClaim> getAllClaims() {
//        return claimRepository.findAll();
//    }
//
//    public String processClaim(String governmentId, String city, LocalDate date, BigDecimal claimAmount) {
//        // Fetch insurance policy for the user
//        Optional<InsurancePolicy> policyOpt = policyRepository.findByGovernmentId(governmentId.trim());
//        if (policyOpt.isEmpty()) {
//            return "Claim Rejected: No active insurance policy found.";
//        }
//
//        InsurancePolicy policy = policyOpt.get();
//
//        // Ensure the provided governmentId matches the one in the policy
//        if (!policy.getGovernmentId().equalsIgnoreCase(governmentId.trim())) {
//            return "Claim Rejected: Provided government ID does not match the policy records.";
//        }
//
//        // Convert LocalDate to Date
//        Date claimDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        // Validate claim within the policy period
//        if (date.isBefore(policy.getStartDate()) || date.isAfter(policy.getEndDate())) {
//            return "Claim Rejected: Claim date is outside the valid policy period.";
//        }
//
//        // Check extreme weather conditions
//        String weatherCondition = weatherAPIUtil.getWeather(city, date.toString());
//        if (!(weatherCondition.contains("Flood") || weatherCondition.contains("Heavy Rain") || weatherCondition.contains("Thunderstorm"))) {
//            return "Claim Rejected: No severe weather conditions found.";
//        }
//
//        // Check if claim amount is within coverage amount
//        if (claimAmount.compareTo(BigDecimal.valueOf(policy.getCoverageAmount())) > 0) {
//            return "Claim Rejected: Claim amount exceeds coverage amount.";
//        }
//
//        // Deduct claim amount from policy coverage
//        BigDecimal updatedCoverage = BigDecimal.valueOf(policy.getCoverageAmount()).subtract(claimAmount);
//        policy.setCoverageAmount(updatedCoverage.doubleValue());
//        policyRepository.save(policy);
//
//        // Save the claim
//        InsuranceClaim claim = new InsuranceClaim();
//        claim.setPolicy(policy);
//        claim.setClaimAmount(claimAmount);
//        claim.setClaimDate(claimDate);
//        claim.setIncidentDate(claimDate);
//        claim.setPlace(city);
//        claim.setStatus(ClaimStatus.APPROVED);
//
//        claimRepository.save(claim);
//        return "Claim Approved: Severe weather detected in " + city + " on " + date;
//    }
//}
package com.aquawealth.service;

import com.aquawealth.model.ClaimStatus;
import com.aquawealth.model.InsuranceClaim;
import com.aquawealth.model.InsurancePolicy;
import com.aquawealth.repository.InsuranceClaimRepository;
import com.aquawealth.repository.InsurancePolicyRepository;
import com.aquawealth.util.WeatherAPIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class InsuranceClaimService {

    @Autowired
    private InsuranceClaimRepository claimRepository;

    @Autowired
    private InsurancePolicyRepository policyRepository;

    @Autowired
    private WeatherAPIUtil weatherAPIUtil;

    public List<InsuranceClaim> getAllClaims() {
        return claimRepository.findAll();
    }

    public String processClaim(String governmentId, String city, LocalDate date, BigDecimal claimAmount) {
        //  Check if the user has an active policy
        Optional<InsurancePolicy> policyOpt = policyRepository.findByGovernmentId(governmentId.trim());
        if (policyOpt.isEmpty()) {
            return "Claim Rejected: No active insurance policy found.";
        }

        InsurancePolicy policy = policyOpt.get();

        // Ensure the governmentId matches the policy record
        if (!policy.getGovernmentId().equalsIgnoreCase(governmentId.trim())) {
            return "Claim Rejected: Provided government ID does not match the policy records.";
        }

        //  Validate claim is within policy coverage period
        if (date.isBefore(policy.getStartDate()) || date.isAfter(policy.getEndDate())) {
            return "Claim Rejected: Claim date is outside the valid policy period.";
        }

        //  Check extreme weather conditions
        String weatherCondition = weatherAPIUtil.getWeather(city, date.toString());
        if (!(weatherCondition.contains("Flood") || weatherCondition.contains("Heavy Rain") || weatherCondition.contains("Thunderstorm"))) {
            return "Claim Rejected: No severe weather conditions found.";
        }

        //  Validate claim amount does not exceed coverage
        if (claimAmount.compareTo(BigDecimal.valueOf(policy.getCoverageAmount())) > 0) {
            return "Claim Rejected: Claim amount exceeds coverage amount.";
        }

        // Deduct claim amount from coverage
        BigDecimal updatedCoverage = BigDecimal.valueOf(policy.getCoverageAmount()).subtract(claimAmount);
        policy.setCoverageAmount(updatedCoverage.doubleValue());
        policyRepository.save(policy);

        // Save claim only if approved
        InsuranceClaim claim = new InsuranceClaim();
        claim.setPolicy(policy);
        claim.setClaimAmount(claimAmount);
        claim.setClaimDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        claim.setIncidentDate(claim.getClaimDate());
        claim.setPlace(city);
        claim.setStatus(ClaimStatus.APPROVED);

        claimRepository.save(claim);
        return "Claim Approved: Severe weather detected in " + city + " on " + date;
    }
}
