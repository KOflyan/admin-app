import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Login extends React.Component {
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
          <div className="panel-body">
            <form>
              <div className="form-group">
        				<input type="text" name="username" id="username" tabindex="1" className="form-control" placeholder="Username"/>
        			</div>
        			<div className="form-group">
        				<input type="password" name="password" id="password" tabindex="2" className="form-control" placeholder="Password"/>
        			</div>
              <div className="form-group">
                <select className="form-control" id="formSelectRole">
                  <option>Admin</option>
                  <option>Tester</option>
                </select>
              </div>
        			<div className="form-group">
        				<div className="row">
        					<div className="center">
                    <Link to="/home">
                      <button className="btn btn-info btn-lg" tabindex="4"> Log in </button>
                    </Link>
        					</div>
        				</div>
        			</div>
            </form>
          </div>
        </div>
      </div>
    )
  }
}

export default Login;