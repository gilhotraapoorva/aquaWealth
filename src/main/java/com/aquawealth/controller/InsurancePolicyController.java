
package com.aquawealth.controller;

import com.aquawealth.model.InsurancePolicy;
import com.aquawealth.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService policyService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForInsurance(@RequestBody InsurancePolicy policy) {


        if (policy.getGovernmentId() == null || policy.getGovernmentId().isEmpty()) {

            return ResponseEntity.badRequest().body("Government ID is required");
        }

        try {
            InsurancePolicy savedPolicy = policyService.savePolicy(policy);
            return ResponseEntity.ok(savedPolicy);
        } catch (Exception e) {
            e.printStackTrace(); // Log full error
            return ResponseEntity.status(500).body("Failed to save policy: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InsurancePolicy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }
}
