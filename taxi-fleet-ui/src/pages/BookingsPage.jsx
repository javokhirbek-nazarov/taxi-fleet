import BookingList from "../components/booking/BookingList";
import NewBooking from "../components/booking/NewBooking";

const BookingsPage = () => {

  return (
      <div className="container">
        <NewBooking/>
        <BookingList/>
      </div>
  )
}

export default BookingsPage;