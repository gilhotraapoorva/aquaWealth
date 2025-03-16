
package com.aquawealth.service;

import com.aquawealth.model.InsurancePolicy;
import com.aquawealth.model.User;
import com.aquawealth.repository.InsurancePolicyRepository;
import com.aquawealth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InsurancePolicyService {

//    private static final Logger logger = LoggerFactory.getLogger(InsurancePolicyService.class);

    private final InsurancePolicyRepository policyRepository;
    private final UserRepository userRepository;


    public InsurancePolicyService(InsurancePolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }

    public InsurancePolicy savePolicy(InsurancePolicy policy) {
//        logger.info(" Received request to save insurance policy: {}", policy);

        // Ensure `governmentId` is provided
        if (policy.getGovernmentId() == null || policy.getGovernmentId().isEmpty()) {
            throw new IllegalArgumentException("governmentId cannot be null");
        }

        //  Check if a policy already exists for this governmentId
        Optional<InsurancePolicy> existingPolicy = policyRepository.findByGovernmentId(policy.getGovernmentId());
        if (existingPolicy.isPresent()) {
            throw new IllegalArgumentException("Insurance already exists for this Government ID.");
        }

        if (policy.getUser() == null || policy.getUser().getEmail() == null) {
            throw new RuntimeException("User email is required to save an insurance policy.");
        }

        // Check if user exists
        Optional<User> existingUser = userRepository.findByEmail(policy.getUser().getEmail());

        User user = existingUser.orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(policy.getUser().getEmail());
            newUser.setName(policy.getUser().getName());
            newUser.setGovernmentId(policy.getGovernmentId()); //  Assign the policy's governmentId to the user
            return userRepository.save(newUser);
        });

        policy.setUser(user);

        // Ensure `governmentId` is NOT LOST when setting user
        if (policy.getGovernmentId() == null) {
            policy.setGovernmentId(user.getGovernmentId());
        }

        // Ensure coverage amount is valid
        if (policy.getCoverageAmount() == null || policy.getCoverageAmount() <= 0) {
            throw new IllegalArgumentException("Coverage amount must be provided and greater than 0.");
        }

        // Auto-calculate premium amount
        policy.setPremiumAmount(policy.getCoverageAmount() / 30);

        //  Set start date if not provided
        if (policy.getStartDate() == null) {
            policy.setStartDate(LocalDate.now());
        }

        //  Set end date as 1 year from start date
        policy.setEndDate(policy.getStartDate().plusYears(1));

        if (policy.getCoverageType() == null || policy.getCoverageType().isEmpty()) {
            policy.setCoverageType("BASIC"); //  Set a valid default coverage type
        }

        // Set policy status as ACTIVE
        policy.setStatus("ACTIVE");

//        logger.info("Saving policy to database: {}", policy);

        InsurancePolicy savedPolicy = policyRepository.save(policy);

//        logger.info("Policy saved successfully with ID: {}", savedPolicy.getPolicyId());

        return savedPolicy;
    }



    public List<InsurancePolicy> getAllPolicies() {
        return policyRepository.findAll();
    }
}
