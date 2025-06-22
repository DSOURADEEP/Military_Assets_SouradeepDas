import React, { useState } from 'react';
import api from '../services/api';

function Assignment({ auth }) {
  const [assetId, setAssetId] = useState('');
  const [baseId, setBaseId] = useState('');
  const [personnelName, setPersonnelName] = useState('');
  const [quantity, setQuantity] = useState('');
  const [date, setDate] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      assetId: parseInt(assetId),
      baseId: parseInt(baseId),
      personnelName,
      quantity: parseInt(quantity),
      date: new Date(date).toISOString(),
    };

    try {
      await api.post('/assignments', payload, auth);
      alert('Assignment recorded successfully');
    } catch (err) {
      console.error(err);
      alert('Failed to record assignment');
    }
  };

  return (
    <div className="form-container">
      <h2>Record Assignment</h2>
      <form onSubmit={handleSubmit}>
        <input type="number" value={assetId} onChange={e => setAssetId(e.target.value)} placeholder="Asset ID" required />
        <input type="number" value={baseId} onChange={e => setBaseId(e.target.value)} placeholder="Base ID" required />
        <input value={personnelName} onChange={e => setPersonnelName(e.target.value)} placeholder="Personnel Name" required />
        <input type="number" value={quantity} onChange={e => setQuantity(e.target.value)} placeholder="Quantity" required />
        <input type="datetime-local" value={date} onChange={e => setDate(e.target.value)} required />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Assignment;



