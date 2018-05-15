import React from 'react';
import ApiConnection from './../../utils/ApiConnection';
import Constants from './../../utils/Constants';

class RegisterAccount extends React.Component {
  render() {
    return (
      <div className="row justify-content-center">
        <div className="col-5">
          <div className="card border-info">
            <div className="card-header">
              Create new account
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
    this.setActive = this.setActive.bind(this);
  }

  initialState = {
      name: '',
      accountType: 'free',
      active: 'true',
      error: ''
  }

  handleInputChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    delete this.state['error'];

    ApiConnection.save(Constants.accountApiUrl, this.state)
    .then(response => {
      if (!response) {
        this.setState(this.initialState)
        this.setState({error: true})
      } else {
        this.setState(this.initialState)
        this.setState({error: false})
      }
    }).catch(error => {
      this.setState({error:true}); console.log(error)
    })
    event.preventDefault();
  }

  setActive() {
    this.setState({active: !this.state.active});
  }

  showError() {
    if (this.state.error === '') {
      return <div></div>
    } else if (!this.state.error) {
      return <div className="alert alert-success">New account created successfully!</div>
    } else if (this.state.error) {
      return <div className="alert alert-danger">Something went wrong! Try again!</div>
    }
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input type="text" className="form-control" name="name" value={this.state.name || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="type">Type</label>
            <select id="accountType" name="accountType" value={this.accountType} onChange={this.handleInputChange} className="form-control">
              <option value="free">Free</option>
              <option value="premium">Premium</option>
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="active">Active</label>
            { this.state.active ? (
              <button type="button" className="btn btn-outline-success btn-block" onClick={this.setActive}>Active</button>
            ) : (
              <button type="button" className="btn btn-outline-danger btn-block" onClick={this.setActive}>Inactive</button>
            )}
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

export default RegisterAccount;
