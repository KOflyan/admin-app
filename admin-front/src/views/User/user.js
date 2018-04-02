import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Row, Col, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import ApiConnection from './../../utils/ApiConnection';

class User extends React.Component {
  constructor() {
    super();
    this.state = {
      data: [],
    };
  }


  render() {
    return (
      <div>
        <h1 className="text-center">User information</h1>
        <br></br>
        <div className="card">
          <div className="row">
            <div className="col-6">
              <Form>
                <FormGroup row>
                  <Label for="userId" sm={2}>User ID</Label>
                  <Col sm={8}>
                    <Input type="text" id="userId"/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="accountId" sm={2}>Account ID</Label>
                  <Col sm={8}>
                    <Input type="text" id="accountId"/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="name" sm={2}>Name</Label>
                  <Col sm={8}>
                    <Input type="text" id="name"/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="surname" sm={2}>Surname</Label>
                  <Col sm={8}>
                    <Input type="text" id="surname"/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="username" sm={2}>Username</Label>
                  <Col sm={8}>
                    <Input type="text" id="username"/>
                  </Col>
                </FormGroup>
               </Form>
            </div>
            <div className="col-6">
            <Form>
              <FormGroup row>
                <Label for="email" sm={2}>Email</Label>
                <Col sm={8}>
                  <Input type="text" id="email"/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="country" sm={2}>Country</Label>
                <Col sm={8}>
                  <Input type="text" id="country"/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="language" sm={2}>Language</Label>
                <Col sm={8}>
                  <Input type="text" id="language"/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="active" sm={2}>Active</Label>
                <Col sm={8}>
                  <Input type="text" id="active"/>
                </Col>
              </FormGroup>
              <Row>
                <Col sm="12" md={{ size: 8, offset: 2 }}>
                  <Button color="danger" size="lg" block>Submit</Button>
                </Col>
              </Row>
            </Form>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default User;
