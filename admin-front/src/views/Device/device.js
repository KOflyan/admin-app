import React from 'react';
import AccountApi from './../../utils/AccountApi';

class Device extends React.Component {
  constructor() {
    super();
    this.state = {
      data: []
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  getDataOnLoad = () => {

    const accountId = this.props.match.params.id;

    AccountApi.get(accountId, apiData => {

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
    AccountApi.update(this.state.data);
    event.preventDefault();
  }


  render() {
    return (
      <div>
        <div className="card border-info mb-3">
          <div className="card-header">Account information</div>
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
