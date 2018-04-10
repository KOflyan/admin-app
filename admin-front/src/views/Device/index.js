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
      renderedData: [],
      page: 1,
      total: 0,
    };

    this.handlePageChange = this.handlePageChange.bind(this);

  }

  handlePageChange(page) {
    const renderedData = this.state.data.slice((page - 1) * 10, (page - 1) * 10 + 10);

    this.setState({ page, renderedData });
  }

  getDataOnLoad = () => {
    ApiConnection.all(Constants.deviceApiUrl, apiData => {
      this.setState({
        data: apiData,
        renderedData: apiData.slice(0, 10),
        total: apiData.length
      });
    });
  }

  componentDidMount() {
    this.getDataOnLoad();
  }


  render() {
    const { page, total, renderedData} = this.state;
    return (
      <div>
       <div className ='form-container'>
         <form className="form-inline my-2 my-lg-0">
           <input className="form-control mr-sm-2" style={{width: '400px'}} type="text" placeholder="Search"/>
           <button className="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
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
            {renderedData.map(device => {
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
