import BookingList from "../components/booking/BookingList";
import NewBooking from "../components/booking/NewBooking";

const BookingsPage = () => {

  return (
      <div className="container">
        <BookingList/>
        <NewBooking/>
      </div>
  )
}

export default BookingsPage;