import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function StateBadge({state}) {
  const variant = state === 'NEW' ? 'success' : 'secondary';

  return (
      <span className={`badge bg-${variant} px-4`}>{state}</span>
  );
}

export default StateBadge;