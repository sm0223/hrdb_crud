// @flow
import * as React from 'react';


export function ConstraintViolation(props) {
  console.log("s",props)
  if(props.violation===false) return null;

  return (
      <div className="invalid-feedback">
        <p>{props.violationMessage}</p>
      </div>
  );
};