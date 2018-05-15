import React from 'react';
import { NavLink, Table, Badge } from 'reactstrap';
import { Redirect } from 'react-router-dom';
import ApiConnection from './../../utils/ApiConnection';
import Constants from './../../utils/Constants';
import { isAdmin } from './../../utils/Auth';

class Account extends React.Component {
  constructor() {
    super();
    this.state = {
      data: [],
      error: '',
      admin: false
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.deleteById = this.deleteById.bind(this);
    this.setActive = this.setActive.bind(this);
  }

  getDataOnLoad = () => {

    const accountId = this.props.match.params.id;

    ApiConnection.get(Constants.accountApiUrl, accountId)
    .then(response => {
      this.setState({
        data: response.data
      });
    })

  }

  componentDidMount() {
    isAdmin(bool => {
      this.setState({admin: bool})
    })
    this.getDataOnLoad();
  }

  handleInputChange(event) {
    const value = event.target.value;
    const id = event.target.id;

    this.setState({ data: { ...this.state.data, [id]: value } });
  }

  handleSubmit(event) {
    ApiConnection.save(Constants.accountApiUrl, this.state.data)
    .then(response => {
      this.setState({error: false})
    }).catch(error => {
      this.setState({error: true})
      console.log(error)
    })
    event.preventDefault();
  }

  deleteById() {
    ApiConnection.delete(Constants.accountApiUrl, this.state.data.id);
    this.setState({error: 'redirect'});
  }

  setActive() {
    this.setState({ data: { ...this.state.data, active: !this.state.data.active } });
  }

  showError() {
    if (this.state.error === '') {
      return <div></div>
    } else if (this.state.error === 'redirect') {
      return <Redirect to='/users' />;
    } else if (!this.state.error) {
      return <div className="alert alert-success">Changes saved successfully!</div>
    } else if (this.state.error) {
      return <div className="alert alert-danger">Something went wrong! Try again!</div>
    }
  }

  render() {
    return (
      <div>
        <div className="row">
          <div className="col">
            <div className="card border-info mb-3">
              <div className="card-header">
                Account information
                { this.state.admin ? (<button type="button" className="btn btn-danger float-right" onClick={this.deleteById}>Delete</button>)
                : <a type="button" className="btn btn-danger float-right disabled">Delete</a> }
              </div>
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>

                <div className="form-group">
                  <label htmlFor="id">Account ID</label>
                  <input disabled type="text" className="form-control" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                </div>
                <div className="form-group">
                  <label htmlFor="name">Name</label>
                  <input type="text" className="form-control" id="name" value={this.state.data.name || ''} onChange={this.handleInputChange}/>
                </div>
                <div className="form-group">
                  <label htmlFor="username">Type</label>
                    <input type="text" className="form-control" id="username" value={this.state.data.accountType || ''} onChange={this.handleInputChange}/>
                </div>
                <div className="form-group">
                  <label htmlFor="email">Active</label>
                  { this.state.data.active ? (
                    <button type="button" className="btn btn-outline-success btn-block" onClick={this.setActive}>Active</button>
                  ) : (
                    <button type="button" className="btn btn-outline-danger btn-block" onClick={this.setActive}>Inactive</button>
                  ) }
                </div>
                <div className="form-group">
                  <br></br>
                  { this.state.admin ? (<button type="submit" className="btn btn-danger btn-block" onClick={this.handleSubmit}>Submit</button>)
                    : (<a type="submit" className="btn btn-danger btn-block disabled">Submit</a>) }
                </div>
                <div>
                  { this.showError() }
                </div>
              </form>
            </div>
          </div>
        </div>
        <div className="col">
          {this.state.data.users ? <UsersForAccount users={this.state.data.users} /> : null}
          {this.state.data.devices ? <DevicesForAccount devices={this.state.data.devices} /> : null}
        </div>
      </div>
    </div>
    )
  }
}

class UsersForAccount extends React.Component {
  render() {
    return (
      <div>
        <div className="card">
          <div className="card-header">
            All users for this account
          </div>
          <div className="card-body">
            <Table striped>
              <thead className="thead-dark">
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Username</th>
                  <th>Email</th>
                  <th className="text-center">Status</th>
                  <th className="text-center">View</th>
                </tr>
              </thead>
              <tbody>
                {this.props.users.map(user => {
                    return ( <tr key={ user.id }>
                                <td>
                                  <div>ID { user.id } </div>
                                  <div className="small text-muted">Account ID {user.accountId}</div>
                                </td>
                                <td><div>{ user.name + ' ' + user.surname }</div></td>
                                <td><div>{ user.username }</div></td>
                                <td><div>{ user.email }</div></td>
                                <td className="text-center">
                                  <NavLink>
                                    { user.active ? (
                                        <Badge color="success">Active</Badge>
                                      ) : (
                                        <Badge color="danger">Inactive</Badge>
                                      )}
                                  </NavLink>
                                </td>
                                <td className="text-center">
                                  <NavLink href={`/user/${user.id}`}>
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

class DevicesForAccount extends React.Component {
  render() {
    return (
      <div>
        <div className="card">
          <div className="card-header">
            All devices for this account
          </div>
          <div className="card-body">
            <Table striped>
              <thead className="thead-dark">
                <tr>
                  <th>#</th>
                  <th>Device name</th>
                  <th>Device family</th>
                  <th className="text-center">Go to user</th>
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
                                  <NavLink href={`/user/${device.userId}`}>
                                    <button type="button" className="btn btn-dark"></button>
                                  </NavLink>
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

export default Account;
