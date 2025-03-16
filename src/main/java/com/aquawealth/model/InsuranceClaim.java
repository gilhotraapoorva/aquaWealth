//package com.aquawealth.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Entity
//@Data
//public class InsuranceClaim {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long claimId;
//
//    @ManyToOne
//    @JoinColumn(name = "policy_id")
//    private InsurancePolicy policy;
//
//    private BigDecimal claimAmount;
//    private Date incidentDate;
//    private Date claimDate;
//
//    @Enumerated(EnumType.STRING)
//    private ClaimStatus status;  // ✅ Now correctly references the separate file
//
//    private String verificationNotes;
//
//    public InsurancePolicy getPolicy() {
//        return policy;
//    }
//
//    public String getVerificationNotes() {
//        return verificationNotes;
//    }
//}
package com.aquawealth.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class InsuranceClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private InsurancePolicy policy;

    private BigDecimal claimAmount;
    private Date incidentDate;
    private Date claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private String verificationNotes;

    private String place;  //  New field added

    //  Getter and Setter for place
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public void setPolicy(InsurancePolicy policy) {
        this.policy = policy;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setVerificationNotes(String verificationNotes) {
        this.verificationNotes = verificationNotes;
    }

    // ✅ Ensuring proper getters
    public InsurancePolicy getPolicy() {
        return policy;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId=" + claimId +
                ", policy=" + policy +
                ", claimAmount=" + claimAmount +
                ", incidentDate=" + incidentDate +
                ", claimDate=" + claimDate +
                ", status=" + status +
                ", verificationNotes='" + verificationNotes + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    public String getVerificationNotes() {
        return verificationNotes;
    }
}


