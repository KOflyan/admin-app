import React from 'react';
import ApiConnection from './../../utils/ApiConnection';
import Constants from './../../utils/Constants';

class RegisterUser extends React.Component {
  render() {
    return (
      <div className="row justify-content-center">
        <div className="col-5">
          <div className="card border-info">
            <div className="card-header">
              Create new user and assign it to account
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
      accountId: '',
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      country: '',
      language: '',
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
    this.setState({registrationDate: new Date()});

    ApiConnection.save(Constants.userApiUrl, this.state)
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
      return <div className="alert alert-success">New user created successfully!</div>
    } else if (this.state.error) {
      return <div className="alert alert-danger">Something went wrong! Make sure that account with such ID exists!</div>
    }
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Account ID</label>
            <input type="text" className="form-control" name="accountId" value={this.state.accountId || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input type="text" className="form-control" name="name" value={this.state.name || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Surname</label>
            <input type="text" className="form-control" name="surname" value={this.state.surname || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Username</label>
            <input type="text" className="form-control" name="username" value={this.state.username || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Email</label>
            <input type="text" className="form-control" name="email" value={this.state.email || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Password</label>
            <input type="text" className="form-control" name="password" value={this.state.password || ''} onChange={this.handleInputChange} placeholder="Min 5 characters"/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Country</label>
            <input type="text" className="form-control" name="country" value={this.state.country || ''} onChange={this.handleInputChange}/>
          </div>
          <div className="form-group">
            <label htmlFor="name">Language</label>
            <input type="text" className="form-control" name="language" value={this.state.language || ''} onChange={this.handleInputChange}/>
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

export default RegisterUser;
