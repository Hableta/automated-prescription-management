package com.prescription.prescription;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionValidationService validationService;

    public PrescriptionController(
            PrescriptionRepository prescriptionRepository,
            PrescriptionValidationService validationService) {

        this.prescriptionRepository = prescriptionRepository;
        this.validationService = validationService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(
            @PathVariable Long id) {

        return prescriptionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestBody Prescription prescription) {

        List<String> errors = validationService.validate(prescription);

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Prescription savedPrescription =
                prescriptionRepository.save(prescription);

        return ResponseEntity.ok(savedPrescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(
            @PathVariable Long id) {

        if (!prescriptionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        prescriptionRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
