import React, { Component } from 'react';

class RegisterAdmin extends React.Component {
  render() {
    return (
      <div>
        <div className="panel panel-login">
          <div className="panel-heading">
      			<div className="row">
      				<div className="center">
      					<h5>Register</h5>
      				</div>
            </div>
          </div>
      <div className="panel-body">
        <RegistrationForm/>
      </div>
      </div>
    </div>
    )
  }
}

class RegistrationForm extends React.Component {
  constructor(props) {
    super(props);
    this.state =
    {name: '',
    surname: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    role: 'admin'};

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleInputChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({[name]: value});
  }

  handleSubmit(event) {
    console.log(this.state)
    fetch('/admin/create', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        data: this.state
      })
    })
    event.preventDefault();
  }

  render() {
    return (
      <form id="register-form" onSubmit={this.handleSubmit}>
        <div className="form-row">
          <div className="col-md-6">
            <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange} className="form-control" placeholder="Name"/>
          </div>
          <div className="col-md-6">
            <input type="text" name="surname" value={this.state.surname} onChange={this.handleInputChange} className="form-control" placeholder="Surname"/>
          </div>
        </div>
        <div className="form-group">
          <input type="text" name="username" value={this.state.username} onChange={this.handleInputChange} className="form-control" placeholder="Username"/>
        </div>
        <div className="form-group">
          <input type="email" name="email" value={this.state.email} onChange={this.handleInputChange} className="form-control" placeholder="Email Address"/>
        </div>
        <div className="form-group">
          <input type="password" name="password" value={this.state.password} onChange={this.handleInputChange} className="form-control" placeholder="Password"/>
        </div>
        <div className="form-group">
          <input type="password" name="confirmPassword" value={this.state.confirmPassword} onChange={this.handleInputChange} className="form-control" placeholder="Confirm Password"/>
        </div>
        <div className="form-group">
          <select name="role" value={this.state.role} onChange={this.handleInputChange} className="form-control">
            <option value="admin">Admin</option>
            <option value="tester">Tester</option>
          </select>
        </div>
        <div className="form-group">
          <div className="row">
            <div className="center">
              <input type="submit" onClick={this.handleSubmit} className="btn btn-outline-success" value="Register Now"/>
            </div>
          </div>
        </div>
      </form>
    );
  }
}

export default RegisterAdmin;
