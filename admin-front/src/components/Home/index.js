import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './style.css'

import ApiConnection from './../ApiConnection';

class Home extends React.Component {
  constructor() {
    super();
    this.state = {
      data: [],
    };
  }

  getDataOnLoad = () => {
    ApiConnection.search(apiData => {
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
      <div>
        <Link to="/register">
          <button className="btn btn-outline-primary"> Register New User </button>
        </Link>
        <button className="btn btn-outline-dark" onClick={this.getDataOnLoad}> Refresh </button>
        <center>

    <table class="table table-hover table-outline mb-0 d-md-down-none">
      <thead class="thead-dark">
        <tr>
          <th class="text-center"><i class="icon-people"></i></th>
          <th>User</th>
          <th>Username</th>
          <th>Language</th>
          <th>Email</th>
          <th class="text-center">Active</th>
          <th class="text-center"><i class="icon-settings"></i></th>
        </tr>
      </thead>
    <tbody>
    {this.state.data.map(user => {
        return ( <tr key={user.id}>
                    <td>
                      <div>ID { user.id } </div>
                    </td>
                    <td>
                      <div>{ user.name + " " + user.surname }</div>
                      <div class="small text-muted">
                        Registered: { user.registrationDate }</div>
                    </td>
                    <td>
                      <div>{ user.username }</div>
                    </td>
                    <td>
                      <i class="h4 mb-0 flag-icon flag-icon-{{ user.language }}"></i>
                    </td>
                    <td>
                      <div class="float-left">
                        <strong>{ user.email }</strong>
                      </div>
                    </td>
                    <td class="text-center">
                          <button type="button" class="btn btn-success"><em class="fa fa-check"></em></button>
                    </td>
                    <td class="text-center">
                      <button type="button" class="btn btn-dark"><em class="fa fa-pencil"></em></button>
                    </td>
                  </tr>
                )
              })
            }

            </tbody>
          </table>
        </center>
      </div>
    )
  }
}
{/*this.state.data.map(user => {
    return ( <div key={user.name}>
                <dt>{user.name}</dt>
                <dd>{user.surname}</dd>
                <hr></hr>
              </div>
            )
          })
        */}

export default Home;
