import React, { useState } from 'react';
import api from '../services/api';

function Transfer({ auth }) {
  const [fromBaseId, setFromBaseId] = useState('');
  const [toBaseId, setToBaseId] = useState('');
  const [assetId, setAssetId] = useState('');
  const [quantity, setQuantity] = useState('');
  const [timestamp, setTimestamp] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      fromBaseId: parseInt(fromBaseId),
      toBaseId: parseInt(toBaseId),
      assetId: parseInt(assetId),
      quantity: parseInt(quantity),
      timestamp: new Date(timestamp).toISOString(),
    };

    try {
      await api.post('/transfers', payload, auth);
      alert('Transfer recorded successfully');
    } catch (err) {
      console.error(err);
      alert('Failed to record transfer');
    }
  };

  return (
    <div className="form-container">
      <h2>Record Transfer</h2>
      <form onSubmit={handleSubmit}>
        <input type="number" value={fromBaseId} onChange={e => setFromBaseId(e.target.value)} placeholder="From Base ID" required />
        <input type="number" value={toBaseId} onChange={e => setToBaseId(e.target.value)} placeholder="To Base ID" required />
        <input type="number" value={assetId} onChange={e => setAssetId(e.target.value)} placeholder="Asset ID" required />
        <input type="number" value={quantity} onChange={e => setQuantity(e.target.value)} placeholder="Quantity" required />
        <input type="datetime-local" value={timestamp} onChange={e => setTimestamp(e.target.value)} required />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Transfer;
