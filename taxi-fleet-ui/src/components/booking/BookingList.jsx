import React, {useEffect, useState} from 'react';
import {getBookings} from "../../integration/api";
import BookingListItem from "./BookingListItem";
import {useLocation} from "react-router-dom";

const BookingList = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const location = useLocation();

  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const data = await getBookings();
        setBookings(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchBookings();
  }, [location]);

  if (loading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error: {error}</p>;
  }
  if (bookings?.length <= 0) {
    return <></>
  }

  return (

      <div className=" my-5">
        <table className="table table-hover">
          <thead className="table-dark">
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Client Name</th>
            <th scope="col">Address</th>
            <th scope="col">Created Time</th>
            <th scope="col">State</th>
          </tr>
          </thead>
          <tbody>
          {
            bookings.map((booking) => (
                <BookingListItem key={booking.id} {...booking}/>
            ))
          }
          </tbody>
        </table>
      </div>
  );
}

export default BookingList;
