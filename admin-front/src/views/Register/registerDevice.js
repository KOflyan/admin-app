import React from 'react';
import ApiConnection from './../../utils/ApiConnection';
import Constants from './../../utils/Constants';

class RegisterDevice extends React.Component {
  render() {
    return (
      <div className="row justify-content-center">
        <div className="col-5">
          <div className="card border-info">
            <div className="card-header">
              Create new device and assign it to user and account
            </div>
            <div className="card-body">
              <RegistrationForm />
            </div>
          </div>
        </div>
      </div>
    )
  }
}

class RegistrationForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  initialState = {
      userId: '',
      accountId: '',
      deviceName: '',
      family: 'iOS',
      osVersion: '',
      error: ''
  }

  handleInputChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    delete this.state['error'];
    console.log(this.state)
    ApiConnection.save(Constants.deviceApiUrl, this.state, (response) => {
      if (response.status === 200) {
        this.setState(this.initialState)
        this.setState({error: false})
      } else {
        this.setState({error: true})
      }
    });
    event.preventDefault();
  }

  showError() {
    if (this.state.error === '') {
      return <div></div>
    } else if (!this.state.error) {
      return <div className="alert alert-success">New device created successfully!</div>
    } else if (this.state.error) {
      return <div className="alert alert-danger">Something went wrong! Try again!</div>
    }
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">User ID</label>
            <input type="text" className="form-control" name="userId" value={this.state.userId || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Account ID</label>
            <input type="text" className="form-control" name="accountId" value={this.state.accountId || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Device name</label>
            <input type="text" className="form-control" name="deviceName" value={this.state.deviceName || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="type">Device family</label>
            <select id="accountType" name="accountType" value={this.family} onChange={this.handleInputChange} className="form-control">
              <option value="iOS">iOS</option>
              <option value="Android OS">Android</option>
              <option value="Windows Phone">Windows Phone</option>
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="name">OS version</label>
            <input type="text" className="form-control" name="osVersion" value={this.state.osVersion || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <br></br>
            <button type="submit" className="btn btn-danger btn-block" onClick={this.handleSubmit}>Submit</button>
          </div>
        </form>
        <div>
          { this.showError() }
        </div>
      </div>
    );
  }
}

export default RegisterDevice;
