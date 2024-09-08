import TaxiList from "../components/TaxiList";
import {createBrowserRouter} from "react-router-dom";

const createRouter = () =>
    createBrowserRouter([
      {
        path: "/taxis",
        element: <TaxiList/>
      },
      // {
      //   path: "/bookinghistory/:phoneId",
      //   element: <PhoneBookingHistory />
      // },
      // {
      //   path: "/bookingform/:phoneId",
      //   element: <BookReturnForm />
      // },
      // {
      //   path: "/error",
      //   element: <ErrorPage />
      // },
    ]);

export default createRouter;