import React from 'react';
import { isAdmin } from './../../utils/Auth';

class Home extends React.Component {
  constructor() {
    super();
    this.state = {admin: false};
  }

  componentDidMount() {
    isAdmin(bool => {
      this.setState({admin: bool})
    })
  }

  render() {
    return (
      <div className="card" style={{'paddingTop': '10px'}}>
        <div className="row">
          <div className="col">
            <label htmlFor="user-card" style={{'marginLeft':'2%'}}><strong>User management</strong></label>
            <div className="card border-info mb-3" id="user-card">
              <div className="card-header">
                List all users or view user information
              </div>
              <div className="card-body">
                <div className="card border-primary mb-3">
                  <a href="/users" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark">List all</h4>
                      <p className="card-text"><small className="text-muted">List all registered users</small></p>
                    </div>
                  </a>
                </div>
                <div className="card border-primary mb-3">
                  { this.state.admin ? (<a href="/registerUser" className="btn btn-fix text-left">
                    <div className="card-block ">
                      <h4 className="card-title text-dark ">Create new user</h4>
                      <p className="card-text "><small className="text-muted">Register new user manually</small></p>
                    </div>
                  </a>) :
                  (<a href="/registerUser" className="btn btn-fix text-left disabled">
                    <div className="card-block ">
                      <h4 className="card-title text-dark ">Create new user</h4>
                      <p className="card-text "><small className="text-muted">Register new user manually</small></p>
                    </div>
                  </a>) }
                </div>
              </div>
            </div>
            <label htmlFor="device-card" style={{'marginLeft':'2%'}}><strong>Device management</strong></label>
            <div className="card border-info mb-3" id="device-card">
              <div className="card-header">
                List all devices or view device information
              </div>
              <div className="card-body">
                <div className="card border-primary mb-3">
                  <a href="/devices" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark">List all</h4>
                      <p className="card-text"><small className="text-muted">List all devices</small></p>
                    </div>
                  </a>
                </div>
                <div className="card border-primary mb-3">
                  { this.state.admin ? (<a href="/registerDevice" className="btn btn-fix text-left">
                    <div className="card-block ">
                      <h4 className="card-title text-dark ">Create new device</h4>
                      <p className="card-text "><small className="text-muted">Register new device manually</small></p>
                    </div>
                  </a>) :
                  (<a href="/registerDevice" className="btn btn-fix text-left disabled">
                    <div className="card-block ">
                      <h4 className="card-title text-dark ">Create new device</h4>
                      <p className="card-text "><small className="text-muted">Register new device manually</small></p>
                    </div>
                  </a>) }
                </div>
              </div>
            </div>
          </div>

          <div className="col">
            <label htmlFor="account-card" style={{'marginLeft':'2%'}}><strong>Account management</strong></label>
            <div className="card border-info mb-3" id="account-card">
              <div className="card-header">
                List all account or view account information
              </div>
              <div className="card-body">
                <div className="card border-primary mb-3">
                  <a href="/accounts" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark ">List all</h4>
                      <p className="card-text">
                        <small className="text-muted">List all accounts and their statuses</small>
                      </p>
                    </div>
                  </a>
                </div>
                <div className="card border-primary mb-3">
                  { this.state.admin ? (<a href="registerAccount" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark">Create new account</h4>
                      <p className="card-text"><small className="text-muted">Register new account manually</small></p>
                    </div>
                  </a>) :
                  (<a href="registerAccount" className="btn btn-fix text-left disabled">
                    <div className="card-block">
                      <h4 className="card-title text-dark">Create new account</h4>
                      <p className="card-text"><small className="text-muted">Register new account manually</small></p>
                    </div>
                  </a>) }
                </div>
              </div>
            </div>
            <label htmlFor="corporate-card" style={{'marginLeft':'2%'}}><strong>Corporate management & Statistics</strong></label>
            <div className="card border-info mb-3" id="corporate-card">
              <div className="card-header">
                List all corporate users or show user statistics
              </div>
              <div className="card-body">
                <div className="card border-primary mb-3">
                  <a href="/stats" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark ">Statistics</h4>
                      <p className="card-text "><small className="text-muted">Show users statistics</small></p>
                    </div>
                  </a>
                </div>
                <div className="card border-primary mb-3">
                  { this.state.admin ? (<a href="/registerAdmin" className="btn btn-fix text-left">
                    <div className="card-block">
                      <h4 className="card-title text-dark ">Register user</h4>
                      <p className="card-text "><small className="text-muted">Register new corporate user</small></p>
                    </div>
                  </a>) :
                  (<a href="/registerAdmin" className="btn btn-fix text-left disabled">
                    <div className="card-block">
                      <h4 className="card-title text-dark ">Register user</h4>
                      <p className="card-text "><small className="text-muted">Register new corporate user</small></p>
                    </div>
                  </a>) }
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default Home;
