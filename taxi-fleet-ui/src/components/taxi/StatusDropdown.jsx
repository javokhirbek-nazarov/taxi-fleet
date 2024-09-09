import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {changeTaxiStatus} from "../../integration/api";
import {Dropdown, DropdownButton} from "react-bootstrap";

function StatusDropdown({id, status}) {

  const getVariant = (s) => s === 'AVAILABLE' ? 'success' : 'secondary';
  const [currentStatus, setCurrentStatus] = useState(status);

  const handleSelect = async (eventKey) => {
    try {
      const data = await changeTaxiStatus(id, eventKey)
      setCurrentStatus(data.status);
    } catch (error) {
      console.log("Could not change status");
    }
  };

  return (
      <DropdownButton id="dropdown-basic-button" title={currentStatus}
                      variant={getVariant(currentStatus)}
                      onSelect={handleSelect}>
        <Dropdown.Item eventKey="AVAILABLE">AVAILABLE</Dropdown.Item>
        <Dropdown.Item eventKey="BOOKED">BOOKED</Dropdown.Item>
      </DropdownButton>
  );
}

export default StatusDropdown;
