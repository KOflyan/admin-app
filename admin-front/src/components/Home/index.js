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
    ApiConnection.getAllUsers(apiData => {
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
        <center>
          <table className="table table-hover table-outline mb-0 d-md-down-none">
            <thead className="thead-dark">
              <tr>
                <th className="text-center"><i className="icon-people"></i></th>
                <th>User</th>
                <th>Username</th>
                <th>Language</th>
                <th>Email</th>
                <th className="text-center">Active</th>
                <th className="text-center"><i className="icon-settings"></i></th>
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
                                <div className="small text-muted">
                                  Registered: { user.registrationDate }</div>
                              </td>
                              <td>
                                <div>{ user.username }</div>
                              </td>
                              <td>
                                <i className="h4 mb-0 flag-icon flag-icon-{{ user.language }}"></i>
                              </td>
                              <td>
                                <div className="float-left">
                                  <strong>{ user.email }</strong>
                                </div>
                              </td>
                              <td className="text-center">
                                    <button type="button" className="btn btn-success"><em className="fa fa-check"></em></button>
                              </td>
                              <td className="text-center">
                                <button type="button" className="btn btn-dark"><em className="fa fa-pencil"></em></button>
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

export default Home;
