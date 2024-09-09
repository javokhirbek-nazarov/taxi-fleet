import React from 'react';
import StateBadge from "./StateBadge";

function BookingListItem({id, client, address, state, createdAt}) {

  return (
      <tr>
        <th scope="row">{id}</th>
        <td>{client}</td>
        <td>{address}</td>
        <td>{createdAt}</td>
        <td><StateBadge state={state}/></td>
      </tr>
  );
}

export default BookingListItem;
