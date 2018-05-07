import React from 'react';
import { NavLink, Table } from 'reactstrap';
import Constants from './../../utils/Constants';
import ApiConnection from './../../utils/ApiConnection';

class User extends React.Component {
  constructor() {
    super();
    this.state = {
      data: []
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.deleteById = this.deleteById.bind(this);
  }

  getDataOnLoad = () => {

    const userId = this.props.match.params.id;

    ApiConnection.get(Constants.userApiUrl, userId, apiData => {

      this.setState({
        data: apiData
      });
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  handleInputChange(event) {
    const value = event.target.value;
    const id = event.target.id;

    this.setState({ data: { ...this.state.data, [id]: value } });

  }

  handleSubmit(event) {
    ApiConnection.update(Constants.userApiUrl, this.state.data);
    event.preventDefault();
  }

  deleteById() {
    ApiConnection.delete(Constants.userApiUrl, this.state.data.id);
  }

  render() {
    return (
      <div>
        <div className="row">
          <div className="col">
            <div className="card border-info mb-3">
              <div className="card-header">
                User information
                <button type="button" className="btn btn-danger float-right" onClick={this.deleteById}>Delete</button>
              </div>
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <div className="form-row">
                    <div className="form-group col-md-2">
                      <label htmlFor="id">User ID</label>
                      <input disabled type="text" className="form-control" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col-md-2">
                      <label htmlFor="accountId">Account ID</label>
                      <input disabled type="text" className="form-control" id="accountId" value={this.state.data.accountId || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col">
                      <label htmlFor="name">Name</label>
                      <input type="text" className="form-control" id="name" value={this.state.data.name || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col">
                      <label htmlFor="surname">Surname</label>
                      <input type="text" className="form-control" id="surname" value={this.state.data.surname || ''} onChange={this.handleInputChange}/>
                    </div>
                  </div>
                  <div className="form-row">
                    <div className="form-group col">
                      <label htmlFor="username">Username</label>
                        <input type="text" className="form-control" id="username" value={this.state.data.username || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col">
                      <label htmlFor="email">Email</label>
                      <input type="text" className="form-control" id="email" value={this.state.data.email || ''} onChange={this.handleInputChange}/>
                    </div>
                  </div>
                  <div className="form-row">
                    <div className="form-group col">
                      <label htmlFor="country">Country</label>
                      <input type="text" className="form-control" id="country" value={this.state.data.country || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col">
                      <label htmlFor="language">Language</label>
                      <input type="text" className="form-control" id="language" value={this.state.data.language || ''} onChange={this.handleInputChange}/>
                    </div>
                  </div>
                  <div className="form-row">
                    <div className="form-group col">
                      <label htmlFor="active">Active</label>
                      <input type="text" className="form-control" id="active" value={this.state.data.active || ''} onChange={this.handleInputChange}/>
                    </div>
                    <div className="form-group col">
                      <button type="submit" className="btn btn-danger btn-block" style={{position:'absolute', bottom: '0'}}  onClick={this.handleSubmit}>Submit</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div className="col">
            <div className="card">
              <NavLink href={`/account/${this.state.data.accountId}`}>
                <button type="button" className="btn btn-dark btn-block btn-lg">Go to account</button>
              </NavLink>
            </div>
            {this.state.data.devices ? <DevicesForAccount devices={this.state.data.devices} /> : null}
          </div>
        </div>
      </div>
    )
  }
}

class DevicesForAccount extends React.Component {
  render() {
    return (
      <div>
        <div className="card">
          <div className="card-header">
            All devices for this user
          </div>
          <div className="card-body">
            <Table striped>
              <thead className="thead-dark">
                <tr>
                  <th>#</th>
                  <th>Device name</th>
                  <th>Device family</th>
                  <th className="text-center">Go to device</th>
                </tr>
              </thead>
              <tbody>
                {this.props.devices.map(device => {
                    return ( <tr key={ device.id }>
                                <td><div>ID { device.id } </div></td>
                                <td><div>{ device.deviceName }</div></td>
                                <td>
                                  <div>{ device.family }</div>
                                  <div className="small text-muted">OS version {device.osVersion}</div>
                                </td>
                                <td className="text-center">
                                  <NavLink href={`/device/${device.id}`}>
                                    <button type="button" className="btn btn-dark"></button>
                                  </NavLink>
                                </td>
                              </tr>
                            )
                          })
                        }
              </tbody>
            </Table>
          </div>
        </div>
      </div>
    )
  }
}


export default User;
