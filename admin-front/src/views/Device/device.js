import React from 'react';
import Constants from './../../utils/Constants';
import { NavLink } from 'reactstrap';
import ApiConnection from './../../utils/ApiConnection';

class Device extends React.Component {
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

    const deviceId = this.props.match.params.id;

    ApiConnection.get(Constants.deviceApiUrl, deviceId)
    .then(response => {
      this.setState({
        data: response.data
      })
    })

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
    ApiConnection.save(Constants.deviceApiUrl, this.state.data);
    event.preventDefault();
  }

  deleteById() {
    ApiConnection.delete(Constants.deviceApiUrl, this.state.data.id);
  }


  render() {
    return (
      <div className="row">
        <div className="col">
          <div className="card border-info mb-3">
            <div className="card-header">
              Device information
              <button type="button" className="btn btn-danger float-right" onClick={this.deleteById}>Delete</button>
            </div>
            <div className="card-body">
              <form onSubmit={this.handleSubmit}>
                  <div className="form-group">
                    <label htmlFor="id">Device ID</label>
                    <input disabled type="text" className="form-control" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                  </div>
                  <div className="form-group">
                    <label htmlFor="name">Device name</label>
                    <input type="text" className="form-control" id="deviceName" value={this.state.data.deviceName || ''} onChange={this.handleInputChange}/>
                  </div>
                  <div className="form-group">
                    <label htmlFor="username">Device family</label>
                      <input type="text" className="form-control" id="family" value={this.state.data.family || ''} onChange={this.handleInputChange}/>
                  </div>
                  <div className="form-group">
                    <label htmlFor="email">OS version</label>
                    <input type="text" className="form-control" id="osVersion" value={this.state.data.osVersion || ''} onChange={this.handleInputChange}/>
                  </div>
                  <br></br>
                  <div className="form-group">
                    <button type="submit" className="btn btn-danger btn-block"  onClick={this.handleSubmit}>Submit</button>
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
          <div className="card">
            <NavLink href={`/user/${this.state.data.userId}`}>
              <button type="button" className="btn btn-dark btn-block btn-lg">Go to user</button>
            </NavLink>
          </div>
        </div>
      </div>
    )
  }
}

export default Device;
