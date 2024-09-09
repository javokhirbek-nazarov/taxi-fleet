import React from 'react';
import StatusDropdown from "./StatusDropdown";

function TaxiListItem({id, number, status, location}) {

  return (
      <tr>
        <th scope="row">{id}</th>
        <td>{number}</td>
        <td>{`${location.lng} : ${location.lat}`}</td>
        <td><StatusDropdown id={id} status={status}/></td>
      </tr>
  );
}

export default TaxiListItem;
