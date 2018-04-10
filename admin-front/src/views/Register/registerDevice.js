import React from 'react';
import ApiConnection from './../../utils/ApiConnection';

class RegisterDevice extends React.Component {
  render() {
    return (
      <div>
        <div className="panel panel-login">
          <div className="panel-heading">
      			<div className="row">
      				<div className="center">
      					<h5>Register new device</h5>
      				</div>
            </div>
          </div>
          <div className="panel-body">
            <form id="register-form" action="#" method="post">
      				<div className="form-group">
      					<input type="text" name="username" id="username" tabindex="1" className="form-control" placeholder="Username"/>
      				</div>
      				<div className="form-group">
      					<input type="email" name="email" id="email" tabindex="1" className="form-control" placeholder="Email Address"/>
      				</div>
      				<div className="form-group">
      					<input type="password" name="password" id="password" tabindex="2" className="form-control" placeholder="Password"/>
      				</div>
      				<div className="form-group">
      					<input type="password" name="confirm-password" id="confirm-password" tabindex="2" className="form-control" placeholder="Confirm Password"/>
      				</div>
      				<div className="form-group">
      					<div className="row">
      						<div className="center">
      							<input type="submit" name="register-submit" id="register-submit" tabindex="4" className="form-control btn btn-register" value="Register Now"/>
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
export default RegisterDevice;
