import Dashboard from "../components/dashboard/Dashboard";
import {v4 as uuid} from "uuid";

const DashboardPage = () => {

  const clientId = uuid();

  return (
      <>
        <Dashboard clientId={clientId}/>
      </>
  );

}

export default DashboardPage;