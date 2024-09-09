import {Badge, Card, Col, Row} from "react-bootstrap";
import React from "react";

const TaxiStatusStatisticsCard = ({taxiStatistics}) => {
  if (taxiStatistics == null) {
    return '';
  }

  return (
      <Card className="mb-4">
        <Card.Body>
          <Card.Title>Taxi Status</Card.Title>
          <Row>
            <Col>
              <h4>
                Available <Badge
                  bg="success">{taxiStatistics.availableCount}</Badge>
              </h4>
            </Col>
            <Col>
              <h4>
                Booked <Badge
                  bg="danger">{taxiStatistics.bookedCount}</Badge>
              </h4>
            </Col>
          </Row>
        </Card.Body>
      </Card>
  );
}

export default TaxiStatusStatisticsCard;