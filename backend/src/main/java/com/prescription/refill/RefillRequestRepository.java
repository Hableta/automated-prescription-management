package com.prescription.refill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefillRequestRepository
        extends JpaRepository<RefillRequest, Long> {
}
