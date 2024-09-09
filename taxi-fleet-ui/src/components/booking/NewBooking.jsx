import React, {useState} from 'react';
import {Alert, Button, Form} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {addBooking} from "../../util/integration";
import {useNavigate} from "react-router-dom";

function NewBooking() {
  const [bookingData, setBookingData] = useState({
    client: '',
    address: '',
  });

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const {name, value} = e.target;
    setBookingData({...bookingData, [name]: value});
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const data = addBooking(bookingData.client, bookingData.address)
      setSuccessMessage('Booking successfully created!');
      setErrorMessage('');
    } catch (error) {
      setErrorMessage('Error creating booking. Please try again.');
      setSuccessMessage('');
    } finally {
      setTimeout(() => {
        navigate("/bookings")
      }, 500)
    }
  };

  return (
      <div className="my-5">
        {successMessage && <Alert variant="success">{successMessage}</Alert>}
        {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

        <Form className="d-flex align-items-center" onSubmit={handleSubmit}>
          <Form.Control
              type="text"
              placeholder="Enter client name"
              name="client"
              value={bookingData.client}
              onChange={handleChange}
              required
          />

          <Form.Control
              className="mx-4"
              type="text"
              placeholder="Enter pickup location"
              name="address"
              value={bookingData.address}
              onChange={handleChange}
              required
          />

          <Button variant="primary" type="submit">
            Create
          </Button>
        </Form>
      </div>
  );
}

export default NewBooking;
