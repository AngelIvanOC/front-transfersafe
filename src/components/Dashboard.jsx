import React, { useState } from "react";
import DashboardNav from "./DashboardNav";
import NewTransaction from "./NewTransaction";
import MyTransactions from "./MyTransactions";
import "../styles/Dashboard.css";

function Dashboard() {
  const [activeSection, setActiveSection] = useState("new");

  return (
    <div className="dashboard">
      <DashboardNav
        activeSection={activeSection}
        onSectionChange={setActiveSection}
      />

      <div className="dashboard-content">
        <div className="dashboard-container">
          {activeSection === "new" ? <NewTransaction /> : <MyTransactions />}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
