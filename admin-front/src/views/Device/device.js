import React from 'react';
import Constants from './../../utils/Constants';
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

    ApiConnection.get(Constants.deviceApiUrl, deviceId, apiData => {

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
    ApiConnection.update(Constants.deviceApiUrl, this.state.data);
    event.preventDefault();
  }

  deleteById() {
    ApiConnection.delete(Constants.deviceApiUrl, this.state.data.id);
  }


  render() {
    return (
      <div>
        <div className="card border-info mb-3">
          <div className="card-header">
            Device information
            <button type="button" className="btn btn-danger float-right" onClick={this.deleteById}>Delete</button>
          </div>
          <div className="card-body">
            <form onSubmit={this.handleSubmit}>
              <div className="form-row">
                <div className="form-group col-md-2">
                  <label htmlFor="id">Account ID</label>
                  <input type="text" className="form-control" id="id" value={this.state.data.id || ''} onChange={this.handleInputChange}/>
                </div>
                <div className="form-group col">
                  <label htmlFor="name">Name</label>
                  <input type="text" className="form-control" id="name" value={this.state.data.name || ''} onChange={this.handleInputChange}/>
                </div>
                <div className="form-group col">
                  <label htmlFor="username">Type</label>
                    <input type="text" className="form-control" id="username" value={this.state.data.type || ''} onChange={this.handleInputChange}/>
                </div>
              </div>
              <div className="form-row">
                <div className="form-group col">
                  <label htmlFor="email">Status</label>
                  <input type="text" className="form-control" id="email" value={this.state.data.active || ''} onChange={this.handleInputChange}/>
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

export default Device;
