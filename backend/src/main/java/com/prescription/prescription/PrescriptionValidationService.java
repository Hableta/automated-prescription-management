package com.prescription.prescription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PrescriptionValidationService {

    public List<String> validate(Prescription prescription) {

        List<String> errors = new ArrayList<>();

        if (prescription.getPatient() == null) {
            errors.add("Patient information is required.");
        }

        if (isBlank(prescription.getMedicationName())) {
            errors.add("Medication name is required.");
        }

        if (isBlank(prescription.getDosage())) {
            errors.add("Dosage is required.");
        }

        if (prescription.getQuantity() == null || prescription.getQuantity() <= 0) {
            errors.add("Quantity must be greater than zero.");
        }

        if (prescription.getRepeats() != null && prescription.getRepeats() < 0) {
            errors.add("Repeats cannot be negative.");
        }

        if (isBlank(prescription.getDirections())) {
            errors.add("Directions are required.");
        }

        return errors;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
