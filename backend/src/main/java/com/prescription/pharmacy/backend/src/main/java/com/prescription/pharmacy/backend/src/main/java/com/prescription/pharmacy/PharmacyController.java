package com.prescription.pharmacy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyController(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @GetMapping
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(
            @PathVariable Long id) {

        return pharmacyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pharmacy createPharmacy(
            @RequestBody Pharmacy pharmacy) {

        return pharmacyRepository.save(pharmacy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacy(
            @PathVariable Long id) {

        if (!pharmacyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pharmacyRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
