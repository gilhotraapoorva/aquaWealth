
package com.aquawealth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "insurance_policy")
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //  Ensure proper linking to User table
    private User user;

//    @Column(name = "government_id", nullable = false)
    @JsonProperty("governmentId") // ✅ Ensure JSON maps correctly
    @Column(name = "government_id", nullable = false)
    private String governmentId;

    @Column(name = "coverage_type", nullable = false)
    private String coverageType;

    @Column(name = "coverage_amount", nullable = false)
    private Double coverageAmount;

    @Column(name = "premium_amount", nullable = false)
    private Double premiumAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "status", nullable = false)
    private String status;
//    @Column(name = "start_date", nullable = false)
//    private LocalDate startDate = LocalDate.now();  // Default to today
//
//    @Column(name = "end_date", nullable = false)
//    private LocalDate endDate = startDate.plusYears(1);  // Auto-calculate

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public Double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(Double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyId=" + policyId +
                ", user=" + user +
                ", governmentId='" + governmentId + '\'' +
                ", coverageType='" + coverageType + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premiumAmount=" + premiumAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
