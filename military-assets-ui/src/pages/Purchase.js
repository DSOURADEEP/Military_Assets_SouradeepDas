import React, { useState } from 'react';
import api from '../services/api';

function Purchase({ auth }) {
  const [baseId, setBaseId] = useState('');
  const [assetId, setAssetId] = useState('');
  const [quantity, setQuantity] = useState('');
  const [date, setDate] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      baseId: parseInt(baseId),
      assetId: parseInt(assetId),
      quantity: parseInt(quantity),
      date: new Date(date).toISOString(),
    };

    try {
      await api.post('/purchases', payload, auth);
      alert('Purchase recorded successfully');
    } catch (err) {
      console.error(err);
      alert('Failed to record purchase');
    }
  };

  return (
    <div className="form-container">
      <h2>Record Purchase</h2>
      <form onSubmit={handleSubmit}>
        <input type="number" value={baseId} onChange={e => setBaseId(e.target.value)} placeholder="Base ID" required />
        <input type="number" value={assetId} onChange={e => setAssetId(e.target.value)} placeholder="Asset ID" required />
        <input type="number" value={quantity} onChange={e => setQuantity(e.target.value)} placeholder="Quantity" required />
        <input type="datetime-local" value={date} onChange={e => setDate(e.target.value)} required />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Purchase;

