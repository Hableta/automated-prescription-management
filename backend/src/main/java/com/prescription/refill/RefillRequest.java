package com.prescription.refill;

import com.prescription.prescription.Prescription;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RefillRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refillId;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    private LocalDateTime requestedAt;

    private String status;

    public RefillRequest() {
    }

    public Long getRefillId() {
        return refillId;
    }

    public void setRefillId(Long refillId) {
        this.refillId = refillId;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
