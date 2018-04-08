import React from 'react';
import { NavLink } from 'reactstrap';
import logo from './../../img/logo.svg';
import { Table, Badge } from 'reactstrap';
import AccountApi from './../../utils/AccountApi';
import Pagination from './../../utils/Pagination';


class AccountTable extends React.Component {
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
    AccountApi.all(apiData => {
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
              <th>Name</th>
              <th>Type</th>
              <th className="text-center">Status</th>
              <th className="text-center">Modify</th>
            </tr>
          </thead>
          <tbody>
            {renderedData.map(account => {
                return ( <tr key={ account.id }>
                            <td>
                              <div>ID { account.id } </div>
                            </td>
                            <td>
                              <div>{ account.name }</div>
                            </td>
                            <td>
                              <div>{ account.type }</div>
                            </td>
                            <td className="text-center">
                              <NavLink>
                                { account.active ? (
                                    <Badge color="success">Active</Badge>
                                  ) : (
                                    <Badge color="danger">Inactive</Badge>
                                  )}
                              </NavLink>
                            </td>
                            <td className="text-center">
                              <NavLink href={`/account/${account.id}`}>
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

export default AccountTable;
