package com.aquawealth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClaimRequest {
    @JsonProperty("governmentId")
    private String governmentId;
    @JsonProperty("city")
    private String city;
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonProperty("claimAmount")
    private BigDecimal claimAmount;

    // Getters and Setters
    public String getGovernmentId() { return governmentId; }
    public void setGovernmentId(String governmentId) { this.governmentId = governmentId; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city.trim();  }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getClaimAmount() { return claimAmount; }
    public void setClaimAmount(BigDecimal claimAmount) { this.claimAmount = claimAmount; }
}
