import React, { useState } from 'react';
import api from '../services/api';

function Expenditure({ auth }) {
  const [assetId, setAssetId] = useState('');
  const [baseId, setBaseId] = useState('');
  const [quantity, setQuantity] = useState('');
  const [reason, setReason] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const localDateTime = `${date}T${time}`; // format: 'YYYY-MM-DDTHH:mm:ss'

    const payload = {
      assetId: parseInt(assetId),
      baseId: parseInt(baseId),
      quantity: parseInt(quantity),
      reason,
      date: localDateTime
    };

    try {
      await api.post('/expenditures', payload, auth);
      alert('Expenditure recorded successfully');
    } catch (err) {
      console.error(err);
      alert('Failed to record expenditure');
    }
  };

  return (
    <div className="form-container">
      <h2>Record Expenditure</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          value={assetId}
          onChange={(e) => setAssetId(e.target.value)}
          placeholder="Asset ID"
          required
        />
        <input
          type="number"
          value={baseId}
          onChange={(e) => setBaseId(e.target.value)}
          placeholder="Base ID"
          required
        />
        <input
          type="number"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          placeholder="Quantity"
          required
        />
        <input
          type="text"
          value={reason}
          onChange={(e) => setReason(e.target.value)}
          placeholder="Reason"
          required
        />
        <label>
          Date:
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
            required
          />
        </label>
        <label>
          Time:
          <input
            type="time"
            value={time}
            onChange={(e) => setTime(e.target.value)}
            required
          />
        </label>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Expenditure;