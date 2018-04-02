import React from 'react';
import { Button, Row, Col, Form, FormGroup, Label, Input } from 'reactstrap';
import UserApi from './../../utils/UserApi';

class User extends React.Component {
  constructor() {
    super();
    this.state = {
      data: []
    };

    this.handleInputChange = this.handleInputChange.bind(this);
  }

  getDataOnLoad = () => {

    const userId = this.props.match.params.id;

    UserApi.get(userId, apiData => {

      this.setState({
        data: apiData
      });
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  handleInputChange(event) {
    const value = event.target.value;
    const id = event.target.id;

    this.setState({ data: { ...this.state.data, [id]: value } });

    console.log(this.state.data);
  }


  render() {
    return (
      <div>
        <h1 className="text-center">User information</h1>
        <br></br>
        <div className="card">
          <div className="row">
            <div className="col-6">
              <Form onSubmit={this.handleSubmit}>
                <FormGroup row>
                  <Label for="id" sm={2}>User ID</Label>
                  <Col sm={8}>
                    <Input type="text" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="accountId" sm={2}>Account ID</Label>
                  <Col sm={8}>
                    <Input type="text" id="accountId" value={this.state.data.accountId || ''} onChange={this.handleInputChange}/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="name" sm={2}>Name</Label>
                  <Col sm={8}>
                    <Input type="text" id="name" value={this.state.data.name || ''} onChange={this.handleInputChange}/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="surname" sm={2}>Surname</Label>
                  <Col sm={8}>
                    <Input type="text" id="surname" value={this.state.data.surname || ''} onChange={this.handleInputChange}/>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label for="username" sm={2}>Username</Label>
                  <Col sm={8}>
                    <Input type="text" id="username" value={this.state.data.username || ''} onChange={this.handleInputChange}/>
                  </Col>
                </FormGroup>
              </Form>
            </div>
            <div className="col-6">
            <Form>
              <FormGroup row>
                <Label for="email" sm={2}>Email</Label>
                <Col sm={8}>
                  <Input type="text" id="email" value={this.state.data.email || ''} onChange={this.handleInputChange}/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="country" sm={2}>Country</Label>
                <Col sm={8}>
                  <Input type="text" id="country" value={this.state.data.country || ''} onChange={this.handleInputChange}/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="language" sm={2}>Language</Label>
                <Col sm={8}>
                  <Input type="text" id="language" value={this.state.data.language || ''} onChange={this.handleInputChange}/>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Label for="active" sm={2}>Active</Label>
                <Col sm={8}>
                  <Input type="text" id="active" value={this.state.data.active || ''} onChange={this.handleInputChange}/>
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
