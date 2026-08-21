package com.prescription.patient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
   @PutMapping("/{id}")
public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
    return patientRepository.findById(id)
            .map(patient -> {
                patient.setFirstName(updatedPatient.getFirstName());
                patient.setLastName(updatedPatient.getLastName());
                patient.setDateOfBirth(updatedPatient.getDateOfBirth());
                return ResponseEntity.ok(patientRepository.save(patient));
            })
            .orElse(ResponseEntity.notFound().build());
}    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (!patientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
