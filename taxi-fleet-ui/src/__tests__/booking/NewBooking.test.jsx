import React from 'react';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';
import NewBooking from '../../components/booking/NewBooking';
import {addBooking} from '../../integration/api';
import {BrowserRouter as Router} from 'react-router-dom';

jest.mock('../../integration/api', () => ({
  addBooking: jest.fn(),
}));

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => jest.fn(),
}));

describe('NewBooking Component', () => {
  test('renders the form correctly', () => {
    render(
        <Router>
          <NewBooking/>
        </Router>
    );

    expect(
        screen.getByPlaceholderText('Enter client name')).toBeInTheDocument();
    expect(screen.getByPlaceholderText(
        'Enter pickup location')).toBeInTheDocument();
    expect(screen.getByRole('button', {name: /create/i})).toBeInTheDocument();
  });

  test('submits the form successfully and shows success message', async () => {
    addBooking.mockResolvedValueOnce({});

    render(
        <Router>
          <NewBooking/>
        </Router>
    );

    fireEvent.change(screen.getByPlaceholderText('Enter client name'), {
      target: {value: 'John Doe', name: 'client'},
    });
    fireEvent.change(screen.getByPlaceholderText('Enter pickup location'), {
      target: {value: '123 Main St', name: 'address'},
    });

    fireEvent.click(screen.getByRole('button', {name: /create/i}));

    await waitFor(() => {
      expect(screen.getByText(
          'Booking successfully created!')).toBeInTheDocument();
    });
  });

  test('shows error message when submission fails', async () => {
    addBooking.mockRejectedValueOnce(new Error('Error creating booking'));

    render(
        <Router>
          <NewBooking/>
        </Router>
    );

    fireEvent.change(screen.getByPlaceholderText('Enter client name'), {
      target: {value: 'Jane Doe', name: 'client'},
    });
    fireEvent.change(screen.getByPlaceholderText('Enter pickup location'), {
      target: {value: '456 Another St', name: 'address'},
    });

    fireEvent.click(screen.getByRole('button', {name: /create/i}));

    await waitFor(() => {
      expect(screen.getByText(
          'Error creating booking. Please try again.')).toBeInTheDocument();
    });
  });
});
