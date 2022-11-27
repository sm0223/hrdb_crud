// @flow
import * as React from 'react';
export function Spinners(props) {
  if(props.show == false) return null;

  return (
      <div>
        <div className="spinner-grow text-primary" role="status">
          <span className="sr-only"></span>
        </div>
      </div>
  );
};