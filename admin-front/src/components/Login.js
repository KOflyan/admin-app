import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import './../css/App.css';

class Login extends React.Component {
  render() {
    return (
      <div>
        <div className="panel panel-login">
          <div class="panel-heading">
      			<div class="row">
      				<div class="center">
      					<h2>Login</h2>
      				</div>
            </div>
          </div>
          <div class="panel-body">
            <form>
              <div class="form-group">
        				<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value=""/>
        			</div>
        			<div class="form-group">
        				<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password"/>
        			</div>
        			<div class="form-group">
        				<div class="row">
        					<div class="col-sm-6 col-sm-offset-3">
                    <Link to="/home">
                      <button class="form-control btn btn-login" tabindex="4"> Log in </button>
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
