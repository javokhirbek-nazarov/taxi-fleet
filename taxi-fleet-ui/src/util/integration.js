import axios from "axios";

const backendClient = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getTaxis = async () => {
  const response = await backendClient.get("/taxis");
  if (response.status !== 200) {
    throw new Error('Failed to fetch taxis');
  }
  return await response.data;
}

export const changeTaxiStatus = async (id, status) => {
  const response = await backendClient.put(`/taxis/${id}/status`,
      {status: status});
  if (response.status !== 200) {
    throw new Error('Failed to change status');
  }
  return await response.data;
}

export const getBookings = async () => {
  const response = await backendClient.get("/bookings");
  if (response.status !== 200) {
    throw new Error('Failed to fetch bookings');
  }
  return await response.data;
}

export const addBooking = async (client, address) => {
  const response = await backendClient.post(`/bookings`,
      {client: client, address: address});
  if (response.status !== 200) {
    throw new Error('Failed to add booking');
  }
  return await response.data;
}