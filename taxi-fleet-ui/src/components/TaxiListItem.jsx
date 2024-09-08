import React from 'react';
import StatusBadge from "./StatusBadge";
import {useNavigate} from "react-router-dom";

function TaxiListItem({id, number, status, location}) {

  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/taxis/${id}`);
  };

  return (
      <tr>
        <th scope="row">{id}</th>
        <td>{number}</td>
        <td>{`${location.lng} : ${location.lat}`}</td>
        <td><StatusBadge id={id} status={status}/></td>
      </tr>
  );
}

export default TaxiListItem;
