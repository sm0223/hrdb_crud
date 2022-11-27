import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function Example({show, handleClose}) {
  return (
      <>
        <Modal show={show} onHide={()=>handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Success</Modal.Title>
          </Modal.Header>
          <Modal.Body>Data Inserted Successfully</Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={()=>handleClose}>
              Close
            </Button>
            <Button variant="primary" onClick={()=>handleClose}>
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>
      </>
  );
};
export default Example