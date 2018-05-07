import React from 'react';
import { Link, Redirect } from 'react-router-dom';

class Login extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      authError: false
    }

    this.handleInputChange = this.handleInputChange.bind(this);
    this.validateLogin = this.validateLogin.bind(this);
  }

  handleInputChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

  validateLogin(event) {
    fetch('/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: this.state.username,
        password: this.state.password
      })
    }).then( response => {
      console.log(response.status)
      // if (response.status === 200 || response.status === 302) {
      // } else {
      //   this.setState({authError: true})
      // }
    })
    event.preventDefault();
    return <Redirect to='/home' />;

  }

  showAuthError() {
    if (this.state.authError) {
      return <div className="alert alert-danger">Something went wrong! Try again!</div>;
    } else {
      return;
      // return <Redirect to='/home' />;
    }
  }

  render() {
    return (
      <div>
        <div className="panel panel-login">
          <div className="panel-heading">
      			<div className="row">
      				<div className="center">
      					<h2>Login</h2>
      				</div>
            </div>
          </div>
        <br></br>
          <div className="panel-body">
            <form>
              <div className="form-group">
        				<input type="text" name="username" value={this.state.username} onChange={this.handleInputChange} className="form-control" placeholder="Username"/>
        			</div>
        			<div className="form-group">
        				<input type="password" name="password" value={this.state.password} onChange={this.handleInputChange} className="form-control" placeholder="Password"/>
        			</div>
              <br></br>
        			<div className="form-group">
        				<div className="row">
        					<div className="center">
                    <input type="submit" className="btn btn-info btn-lg" onClick={this.validateLogin} value="Log in"/>
        					</div>
        				</div>
        			</div>
            </form>
          </div>
          <div>
            { this.showAuthError() }
          </div>
        </div>
      </div>
    )
  }
}

export default Login;
