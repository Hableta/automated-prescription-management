CREATE TABLE patients (
    patient_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL
);

CREATE TABLE medications (
    medication_id SERIAL PRIMARY KEY,
    medication_name VARCHAR(255) NOT NULL
);

CREATE TABLE pharmacies (
    pharmacy_id SERIAL PRIMARY KEY,
    pharmacy_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50)
);

CREATE TABLE prescriptions (
    prescription_id SERIAL PRIMARY KEY,
    patient_id INT NOT NULL REFERENCES patients(patient_id),
    medication_id INT NOT NULL REFERENCES medications(medication_id),
    dosage VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    repeats INT DEFAULT 0,
    directions TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE refill_requests (
    refill_id SERIAL PRIMARY KEY,
    prescription_id INT NOT NULL REFERENCES prescriptions(prescription_id),
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'Pending'
);
