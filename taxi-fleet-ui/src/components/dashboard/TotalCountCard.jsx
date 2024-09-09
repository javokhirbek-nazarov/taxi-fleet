import {Card} from "react-bootstrap";
import React from "react";

const TotalCountCard = ({stats, title}) => {

  if (stats == null) {
    return '';
  }

  return (
      <Card>
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <h2>{stats.totalCount}</h2>
        </Card.Body>
      </Card>
  );
}

export default TotalCountCard;