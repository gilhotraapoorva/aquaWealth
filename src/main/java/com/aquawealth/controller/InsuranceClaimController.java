//package com.aquawealth.controller;
//
//import com.aquawealth.model.ClaimRequest;
//import com.aquawealth.service.InsuranceClaimService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/insurance")
//public class InsuranceClaimController {
//    private static final Logger log = LoggerFactory.getLogger(InsuranceClaimController.class);
//
//    @Autowired
//    private InsuranceClaimService claimService;
//
//    @GetMapping
//    public String testController() {
//        return "Insurance Controller is working!";
//    }
//
//    @PostMapping(value = "/claim", consumes = "application/json", produces = "application/json") // ✅ Ensure JSON response
//    public ResponseEntity<Map<String, String>> processClaim(@RequestBody ClaimRequest claimRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            log.info("Received Claim Request: {}", claimRequest);
//
//            String result = claimService.processClaim(
//                    claimRequest.getGovernmentId(),
//                    claimRequest.getCity(),
//                    claimRequest.getDate(),
//                    claimRequest.getClaimAmount()
//            );
//
//            response.put("message", result); // ✅ Always return JSON format
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//            response.put("error", "Error Processing Claim: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//}
package com.aquawealth.controller;

import com.aquawealth.model.ClaimRequest;
import com.aquawealth.service.InsuranceClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/insurance")
public class InsuranceClaimController {
    private static final Logger log = LoggerFactory.getLogger(InsuranceClaimController.class);

    @Autowired
    private InsuranceClaimService claimService;

    @GetMapping
    public String testController() {
        return "Insurance Controller is working!";
    }

    /**
     * Process an insurance claim.
     *
     * @param claimRequest The claim request payload.
     * @return JSON response indicating success or failure.
     */
    @PostMapping(value = "/claim", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> processClaim(@RequestBody ClaimRequest claimRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            log.info("🔹 Received Claim Request: {}", claimRequest);
            log.info("➡ Government ID: {}", claimRequest.getGovernmentId());
            log.info("➡ City: {}", claimRequest.getCity());
            log.info("➡ Date: {}", claimRequest.getDate());
            log.info("➡ Claim Amount: {}", claimRequest.getClaimAmount());

            // Process the claim
            String result = claimService.processClaim(
                    claimRequest.getGovernmentId(),
                    claimRequest.getCity(),
                    claimRequest.getDate(),
                    claimRequest.getClaimAmount()
            );

            // ✅ If claim is rejected, return HTTP 400 (Bad Request)
            if (result.startsWith("Claim Rejected")) {
                response.put("error", result);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // ✅ If claim is approved, return HTTP 200 (OK)
            response.put("message", result);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("❌ Error Processing Claim: {}", e.getMessage());
            response.put("error", "Error Processing Claim: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
