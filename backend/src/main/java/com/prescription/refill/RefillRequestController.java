package com.prescription.refill;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refills")
public class RefillRequestController {

    private final RefillRequestRepository refillRequestRepository;

    public RefillRequestController(
            RefillRequestRepository refillRequestRepository) {
        this.refillRequestRepository = refillRequestRepository;
    }

    @GetMapping
    public List<RefillRequest> getAllRefillRequests() {
        return refillRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefillRequest> getRefillRequestById(
            @PathVariable Long id) {

        return refillRequestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RefillRequest createRefillRequest(
            @RequestBody RefillRequest refillRequest) {

        if (refillRequest.getRequestedAt() == null) {
            refillRequest.setRequestedAt(LocalDateTime.now());
        }

        if (refillRequest.getStatus() == null ||
                refillRequest.getStatus().isBlank()) {
            refillRequest.setStatus("Pending");
        }

        return refillRequestRepository.save(refillRequest);
    }
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteRefillRequest(
        @PathVariable Long id) {

    if (!refillRequestRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }

    refillRequestRepository.deleteById(id);

    return ResponseEntity.noContent().build();
}
    @PutMapping("/{id}/status")
    public ResponseEntity<RefillRequest> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return refillRequestRepository.findById(id)
                .map(refill -> {
                    refill.setStatus(status);
                    return ResponseEntity.ok(
                            refillRequestRepository.save(refill));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
