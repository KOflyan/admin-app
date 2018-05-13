import React from 'react';
import ApiConnection from './../../utils/ApiConnection';

class RegisterAdmin extends React.Component {
  render() {
    return (
      <div>
        <div className="panel panel-login">
          <div className="panel-heading">
      			<div className="row">
      				<div className="center">
      					<h5>Register admin</h5>
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
    this.state = this.initialState;

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  initialState = {
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      role: 'admin',
      type: 'admin',
      error: ''
  }

  handleInputChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    delete this.state['error'];
    ApiConnection.registerAdmin(this.state)
    .then( response => {
      this.setState(this.initialState)
      this.setState({error: false})
    }).catch(error => this.setState({error: true}))

    event.preventDefault();
  }

  showError() {
    if (this.state.error === '') {
      return <div></div>
    } else if (!this.state.error) {
      return <div className="alert alert-success">New corporate user created successfully!</div>
    } else if (this.state.error) {
      return <div className="alert alert-danger">Something went wrong! Try again!</div>
    }
  }

  render() {
    return (
      <div>
        <form id="register-form" onSubmit={this.handleSubmit}>
          <div className="form-row">
            <div className="col-md-6">
              <input type="text" name="name" value={this.state.name || ''} onChange={this.handleInputChange} className="form-control" placeholder="Name"/>
            </div>
            <div className="col-md-6">
              <input type="text" name="surname" value={this.state.surname || ''} onChange={this.handleInputChange} className="form-control" placeholder="Surname"/>
            </div>
          </div>
          <div className="form-group">
            <input type="text" name="username" value={this.state.username || ''} onChange={this.handleInputChange} className="form-control" placeholder="Username (min 3 characters)"/>
          </div>
          <div className="form-group">
            <input type="email" name="email" value={this.state.email || ''} onChange={this.handleInputChange} className="form-control" placeholder="Email Address"/>
          </div>
          <div className="form-group">
            <input type="password" name="password" value={this.state.password || ''} onChange={this.handleInputChange} className="form-control" placeholder="Password (min 5 characters)"/>
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
        <div>
          { this.showError() }
        </div>
      </div>
    );
  }
}

export default RegisterAdmin;
