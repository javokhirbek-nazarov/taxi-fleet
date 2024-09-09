import {Badge, Card, Col, Row} from "react-bootstrap";

const BookingStatusStatisticsCard = ({bookingStatistics}) => {
  if (bookingStatistics == null) {
    return '';
  }

  return (
      <Card className="mb-4">
        <Card.Body>
          <Card.Title>Booking Status</Card.Title>
          <Row>
            <Col>
              <h4>
                New <Badge bg="primary">{bookingStatistics.newCount}</Badge>
              </h4>
            </Col>
            <Col>
              <h4>
                Taken <Badge
                  bg="warning">{bookingStatistics.takenCount}</Badge>
              </h4>
            </Col>
          </Row>
        </Card.Body>
      </Card>
  );
}

export default BookingStatusStatisticsCard;