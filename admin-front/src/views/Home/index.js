import React from 'react';
import { NavLink } from 'reactstrap';
import logo from './../../img/logo.svg';
import { Table, Badge } from 'reactstrap';
import UserApi from './../../utils/UserApi';

class Home extends React.Component {
  constructor() {
    super();

    this.state = {
      data: [],
    };
  }

  getDataOnLoad = () => {
    UserApi.all(apiData => {
      this.setState({
        data: apiData
      });
    });
  }
  componentDidMount() {
    this.getDataOnLoad();
  }

  render() {
    return (
      <div className="card" style={{'padding-top': '10px'}}>
          <div className="row">
            <div className="col">
              <label for="user-card" style={{'margin-left':'2%'}}><strong>User management</strong></label>
              <div className="card border-danger mb-3" id="user-card">
                <div className="card-header">
                  List all users or view user information.
                </div>
                <div className="card-body">
                  <div className="card border-primary mb-3">
                    <a href="/users" className="btn btn-fix text-left">
                      {/* <img className="card-img-top" src="..." alt="Card image cap"/> */}
                      <div className="card-block">
                        <h4 className="card-title text-dark">List all</h4>
                        <p className="card-text"><small class="text-muted">List all registered users</small></p>
                      </div>
                    </a>
                  </div>
                  <div className="card border-primary mb-3">
                    <a href="/registerUser" class="btn btn-fix text-left">
                      {/* <img class="card-img-top " src="..." alt="Card image cap"/> */}
                      <div class="card-block ">
                        <h4 class="card-title text-dark ">Create new user</h4>
                        <p class="card-text "><small class="text-muted">Register new user manually</small></p>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
              <label for="device-card" style={{'margin-left':'2%'}}><strong>Device management</strong></label>
              <div className="card border-danger mb-3" id="device-card">
                <div className="card-header">
                  List all devices or view device information.
                </div>
                <div className="card-body">
                  <div className="card border-primary mb-3">
                    <a href="/users" className="btn btn-fix text-left">
                      {/* <img className="card-img-top" src="..." alt="Card image cap"/> */}
                      <div className="card-block">
                        <h4 className="card-title text-dark">List all</h4>
                        <p className="card-text"><small class="text-muted">List all devices</small></p>
                      </div>
                    </a>
                  </div>
                  <div className="card border-primary mb-3">
                    <a href="/register" class="btn btn-fix text-left">
                      {/* <img class="card-img-top " src="..." alt="Card image cap"/> */}
                      <div class="card-block ">
                        <h4 class="card-title text-dark ">Soon come</h4>
                        <p class="card-text "><small class="text-muted">Register new user manually</small></p>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
            </div>

            <div className="col">
              <label for="account-card" style={{'margin-left':'2%'}}><strong>Account management</strong></label>
              <div className="card border-danger mb-3" id="account-card">
                <div className="card-header">
                  List all account or view account information.
                </div>
                <div className="card-body">
                  <div className="card border-primary mb-3">
                    <a href="/accounts" className="btn btn-fix text-left">
                        {/* <img className="card-img-top " src="..." alt="Card image cap"/> */}
                      <div className="card-block">
                        <h4 className="card-title text-dark ">List all</h4>
                        <p className="card-text">
                          <small class="text-muted">List all accounts and their status</small>
                        </p>
                      </div>
                    </a>
                  </div>
                  <div className="card border-primary mb-3">
                    <a href="#" class="btn btn-fix text-left">
                      {/* <img class="card-img-top " src="..." alt="Card image cap"/> */}
                      <div class="card-block ">
                        <h4 class="card-title text-dark ">Create new account</h4>
                        <p class="card-text "><small class="text-muted">Register new user manually</small></p>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
              <label for="corporate-card" style={{'margin-left':'2%'}}><strong>Corporate user management</strong></label>
              <div className="card border-danger mb-3" id="corporate-card">
                <div className="card-header">
                  List all corporate users that have access to admin application.
                </div>
                <div className="card-body">
                  {/* <div className="card border-primary mb-3">
                    <a href="/users" className="btn btn-fix text-left">
                      <img className="card-img-top" src="..." alt="Card image cap"/>
                      <div className="card-block">
                        <h4 className="card-title text-dark">List all</h4>
                        <p className="card-text"><small class="text-muted">List all devices</small></p>
                      </div>
                    </a>
                  </div> */}
                  <div className="card border-primary mb-3">
                    <a href="/register" class="btn btn-fix text-left">
                      {/* <img class="card-img-top " src="..." alt="Card image cap"/> */}
                      <div class="card-block ">
                        <h4 class="card-title text-dark ">Register user</h4>
                        <p class="card-text "><small class="text-muted">Register new corporate user</small></p>
                      </div>
                    </a>
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
