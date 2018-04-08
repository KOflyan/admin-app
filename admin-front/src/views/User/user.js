import React from 'react';
import UserApi from './../../utils/UserApi';

class User extends React.Component {
  constructor() {
    super();
    this.state = {
      data: []
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  getDataOnLoad = () => {

    const userId = this.props.match.params.id;

    UserApi.get(userId, apiData => {

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
    UserApi.update(this.state.data);
    event.preventDefault();
  }


  render() {
    return (
      <div>
        <div className="card border-info mb-3">
          <div className="card-header">User information</div>
            <div className="card-body">
              <form onSubmit={this.handleSubmit}>
                <div className="form-row">
                  <div className="form-group col-md-2">
                    <label htmlFor="id">User ID</label>
                    <input type="text" className="form-control" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                  </div>
                  <div className="form-group col-md-2">
                    <label htmlFor="accountId">Account ID</label>
                    <input type="text" className="form-control" id="accountId" value={this.state.data.accountId || ''} onChange={this.handleInputChange}/>
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
    )
  }
}


export default User;
