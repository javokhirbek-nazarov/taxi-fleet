import {createBrowserRouter} from "react-router-dom";
import BookingsPage from "../pages/BookingsPage";
import TaxisPage from "../pages/TaxisPage";
import DashboardPage from "../pages/DashboardPage";

const createRouter = () =>
    createBrowserRouter([
      {
        path: "/",
        element: <DashboardPage/>
      },
      {
        path: "/taxis",
        element: <TaxisPage/>
      },
      {
        path: "/bookings",
        element: <BookingsPage/>
      },
    ]);

export default createRouter;