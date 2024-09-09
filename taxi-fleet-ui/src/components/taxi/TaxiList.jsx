import React, {useEffect, useState} from 'react';
import TaxiListItem from './TaxiListItem';
import {getTaxis} from "../../integration/api";

function TaxiList() {
  const [taxis, setTaxis] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTaxis = async () => {
      try {
        const data = await getTaxis();
        setTaxis(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTaxis();
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error: {error}</p>;
  }

  return (

      <div className="container my-5">
        <table className="table table-hover">
          <thead className="table-dark">
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Number</th>
            <th scope="col">Location</th>
            <th scope="col">Status</th>
          </tr>
          </thead>
          <tbody>
          {
            taxis.map((taxi) => (
                <TaxiListItem key={taxi.id}{...taxi}/>
            ))
          }
          </tbody>
        </table>
      </div>
  );
}

export default TaxiList;
