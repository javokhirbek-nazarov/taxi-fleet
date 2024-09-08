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
  const response = await backendClient.put(`/taxis/${id}/status`, {status: status});
  if (response.status !== 200) {
    throw new Error('Failed to change status');
  }
  return await response.data;
}