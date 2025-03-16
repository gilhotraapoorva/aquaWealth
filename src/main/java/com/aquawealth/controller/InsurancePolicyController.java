
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
        System.out.println("📩 Received Insurance Policy Request: " + policy);

        if (policy.getGovernmentId() == null || policy.getGovernmentId().isEmpty()) {
            System.out.println("❌ ERROR: governmentId is missing!");
            return ResponseEntity.badRequest().body("Government ID is required");
        }

        try {
            InsurancePolicy savedPolicy = policyService.savePolicy(policy);
            System.out.println("✅ Policy Saved Successfully: " + savedPolicy);
            return ResponseEntity.ok(savedPolicy);
        } catch (Exception e) {
            System.out.println("🔥 ERROR SAVING POLICY: " + e.getMessage());
            e.printStackTrace(); // Log full error
            return ResponseEntity.status(500).body("Failed to save policy: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InsurancePolicy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }
}
