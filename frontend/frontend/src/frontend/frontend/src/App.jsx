import { useState } from "react";
import "./App.css";

function App() {
  const [activePage, setActivePage] = useState("Dashboard");

  const menuItems = [
    "Dashboard",
    "Patient Profile",
    "Create Prescription",
    "Prescription History",
    "Pharmacies",
    "Refill Requests",
  ];

  return (
    <div className="app">
      <aside className="sidebar">
        <h1>Prescription Management</h1>

        <nav>
          {menuItems.map((item) => (
            <button
              key={item}
              className={activePage === item ? "active" : ""}
              onClick={() => setActivePage(item)}
            >
              {item}
            </button>
          ))}
        </nav>
      </aside>

      <main className="main-content">
        <header className="header">
          <div>
            <h2>{activePage}</h2>
            <p>Automated Prescription Management System</p>
          </div>

          <div className="user">
            <span>Physician</span>
          </div>
        </header>

        {activePage === "Dashboard" && (
          <section className="dashboard">
            <div className="welcome">
              <h2>Welcome to the Physician Dashboard</h2>
              <p>
                Manage patients, prescriptions, pharmacies, and refill
                requests from one place.
              </p>
            </div>

            <div className="cards">
              <div className="card">
                <h3>Patients Today</h3>
                <strong>12</strong>
                <p>Scheduled patients</p>
              </div>

              <div className="card">
                <h3>Active Prescriptions</h3>
                <strong>24</strong>
                <p>Current prescriptions</p>
              </div>

              <div className="card">
                <h3>Refill Requests</h3>
                <strong>5</strong>
                <p>Requests awaiting review</p>
              </div>

              <div className="card">
                <h3>Pharmacies</h3>
                <strong>8</strong>
                <p>Available pharmacies</p>
              </div>
            </div>

            <div className="quick-actions">
              <h3>Quick Actions</h3>

              <button onClick={() => setActivePage("Patient Profile")}>
                Patient Profile
              </button>

              <button onClick={() => setActivePage("Create Prescription")}>
                Create Prescription
              </button>

              <button onClick={() => setActivePage("Pharmacies")}>
                Pharmacies
              </button>

              <button onClick={() => setActivePage("Refill Requests")}>
                Refill Requests
              </button>
            </div>
          </section>
        )}

        {activePage !== "Dashboard" && (
          <section className="page-placeholder">
            <h2>{activePage}</h2>
            <p>
              This section will be connected to the Spring Boot backend as the
              project develops.
            </p>
          </section>
        )}
      </main>
    </div>
  );
}

export default App;
