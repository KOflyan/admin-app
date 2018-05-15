import React from 'react';
import { NavLink } from 'reactstrap';
import logo from './../../img/logo.svg';
import { Table } from 'reactstrap';
import Constants from './../../utils/Constants';
import ApiConnection from './../../utils/ApiConnection';
import Pagination from './../../utils/Pagination';


class DeviceTable extends React.Component {
  constructor() {
    super();

    this.state = {
      data: [],
      page: 1,
      total: 0
    };

    this.handlePageChange = this.handlePageChange.bind(this);
    this.handleSearchChange = this.handleSearchChange.bind(this);
    this.search = this.search.bind(this);
  }

  componentDidMount() {
    this.getDataOnLoad();
  }

  handlePageChange(page) {
    ApiConnection.all(Constants.deviceApiUrl, (page - 1) * Constants.tablePageSize, Constants.tablePageSize)
    .then( response => {
      this.setState({
        data: response[0].data,
        total: response[1].data,
        page: page
      })
    })
  }

  getDataOnLoad = () => {
    ApiConnection.all(Constants.deviceApiUrl, 0, Constants.tablePageSize)
    .then(response => {
      this.setState({
        data: response[0].data,
        total: response[1].data
      })
    })
  }

  handleSearchChange(event) {
    const value = event.target.value;
    this.setState({searchText: value});
  }

  search(event) {
    if (!this.state.searchText) {
      this.getDataOnLoad();
    } else {
      ApiConnection.getSearch(Constants.deviceApiUrl, this.state.searchText)
      .then(response => {
        this.setState({data: response.data, page: 1, total: Constants.tablePageSize});
      })
      .catch(error => console.log(error))
    }
    event.preventDefault();
  }


  render() {
    const { page, data, total} = this.state;
    return (
      <div>
       <div className ='form-container'>
         <form className="form-inline my-2 my-lg-0">
           <input className="form-control mr-sm-2" style={{width: '400px'}} type="text"
             value={this.state.searchText || ''} onChange={this.handleSearchChange} />
           <button className="btn btn-outline-info my-2 my-sm-0" type="submit" onClick={this.search}>Search</button>
         </form>
         <img src={logo} className="App-logo" alt="logo"/>
       </div>
        <Table striped>
          <thead className="thead-dark">
            <tr>
              <th>#</th>
              <th>Foreign IDs</th>
              <th>Device name</th>
              <th>Family</th>
              <th className="text-center">OS version</th>
              <th className="text-center">Modify</th>
            </tr>
          </thead>
          <tbody>
            {data.map(device => {
                return ( <tr key={ device.id }>
                            <td>
                              <div>ID { device.id } </div>
                            </td>
                            <td>
                              <div> User ID { device.userId }</div>
                              <div className="small text-muted">
                                Account ID {device.accountId}
                              </div>
                            </td>
                            <td>
                              <div>{ device.deviceName }</div>
                            </td>
                            <td>
                              <div>{ device.family }</div>
                            </td>
                            <td>
                              <div className="text-center">{ device.osVersion }</div>
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
        <Pagination
          margin={10}
          page={page}
          count={Math.ceil(total / 10)}
          onPageChange={this.handlePageChange}
        />
      </div>
    )
  }
}

export default DeviceTable;
