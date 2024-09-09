import React, {useEffect, useState} from 'react';
import {Col, Row} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {getDashboardStatisticsData} from "../../util/integration";
import TaxiStatusStatisticsCard from "./TaxiStatusStatisticsCard";
import BookingStatusStatisticsCard from "./BookingStatusStatisticsCard";
import TotalCountCard from "./TotalCountCard";

const Dashboard = ({clientId}) => {
  const [data, setData] = useState(
      {taxiStatistics: null, bookingStatistics: null});

  useEffect(() => {
    const fetchData = async (immediate) => {
      try {
        const data = await getDashboardStatisticsData(clientId, immediate);
        setData(data);
        await fetchData(false);
      } catch (err) {
        console.log(err);
        if (err.code === 'ECONNABORTED') {
          await fetchData(false);
        }
      }
    };

    fetchData(true);
  }, [clientId]);

  return (
      <div className="container mt-4">
        <h2 className="text-center display-4">Fleet Dashboard</h2>
        <Row className="mt-4">
          <Col md={6}>
            <TaxiStatusStatisticsCard taxiStatistics={data.taxiStatistics}/>
          </Col>
          <Col md={6}>
            <BookingStatusStatisticsCard
                bookingStatistics={data.bookingStatistics}/>
          </Col>

        </Row>

        <Row className="mt-4">
          <Col>
            <TotalCountCard title={"Total Taxis"} stats={data.taxiStatistics}/>
          </Col>
          <Col>
            <TotalCountCard title={"Total Bookings"}
                            stats={data.bookingStatistics}/>
          </Col>
        </Row>
      </div>
  );
}

export default Dashboard;
