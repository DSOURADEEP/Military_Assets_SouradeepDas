import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Dashboard.css';

function Dashboard() {
  const [baseId, setBaseId] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [data, setData] = useState(null);
  const [error, setError] = useState('');

  const fetchDashboard = async () => {
    try {
      const params = {
        baseId,
        startDate,
        endDate,
      };

      const response = await api.get('/dashboard', { params });
      setData(response.data);
      setError('');
    } catch (error) {
      console.error('Failed to fetch dashboard data:', error.response?.data || error.message);
      setError('Failed to fetch dashboard data');
    }
  };

  useEffect(() => {
    if (baseId && startDate && endDate) {
      fetchDashboard();
    }
  }, [baseId, startDate, endDate]);

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Asset Dashboard</h2>
      <div className="dashboard-filters">
        <input
          type="number"
          placeholder="Base ID"
          value={baseId}
          onChange={(e) => setBaseId(e.target.value)}
        />
        <input
          type="datetime-local"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
        />
        <input
          type="datetime-local"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
        />
        <button onClick={fetchDashboard}>Fetch</button>
      </div>

      {error && <p className="error">{error}</p>}

      {data && data.length > 0 && (
        <div className="dashboard-table">
          <table>
            <thead>
              <tr>
                <th>Asset Name</th>
                <th>Type</th>
                <th>Opening</th>
                <th>Purchases</th>
                <th>Transfers In</th>
                <th>Transfers Out</th>
                <th>Assigned</th>
                <th>Expended</th>
                <th>Closing</th>
              </tr>
            </thead>
            <tbody>
              {data.map((asset, index) => (
                <tr key={index}>
                  <td>{asset.assetName}</td>
                  <td>{asset.assetType}</td>
                  <td>{asset.openingBalance}</td>
                  <td>{asset.purchases}</td>
                  <td>{asset.transferIn}</td>
                  <td>{asset.transferOut}</td>
                  <td>{asset.assignments}</td>
                  <td>{asset.expenditures}</td>
                  <td>{asset.closingBalance}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {data && data.length === 0 && (
        <p style={{ textAlign: 'center', marginTop: '20px' }}>No data available for the selected filters.</p>
      )}
    </div>
  );
}

export default Dashboard;
