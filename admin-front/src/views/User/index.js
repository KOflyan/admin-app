import React from 'react';
import { NavLink } from 'reactstrap';
import logo from './../../img/logo.svg';
import { Table, Badge } from 'reactstrap';
import Constants from './../../utils/Constants';
import ApiConnection from './../../utils/ApiConnection';
import Pagination from './../../utils/Pagination';
import FlagIcon from './../../utils/FlagIcon';


class UserTable extends React.Component {
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
    ApiConnection.all(Constants.userApiUrl, (page - 1) * Constants.tablePageSize, Constants.tablePageSize)
    .then(response => {
      this.setState({
        data: response[0].data,
        total: response[1].data,
        page: page
      });
    })

  }

  getDataOnLoad = () => {
    ApiConnection.all(Constants.userApiUrl, 0, Constants.tablePageSize)
    .then(response => {
      this.setState({
        data: response[0].data,
        total: response[1].data,
      });
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
      ApiConnection.getSearch(Constants.userApiUrl, this.state.searchText)
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
           <input className="form-control mr-sm-2" style={{width: '400px'}} type="text" placeholder="Search"
             value={this.state.searchText || ''} onChange={this.handleSearchChange} />
           <button className="btn btn-outline-info my-2 my-sm-0" type="submit" onClick={this.search}>Search</button>
         </form>
         <img src={logo} className="App-logo" alt="logo"/>
       </div>
        <Table striped>
          <thead className="thead-dark">
            <tr>
              <th>#</th>
              <th>User</th>
              <th>Username</th>
              <th className="text-center">Language</th>
              <th>Country</th>
              <th>Email</th>
              <th className="text-center">Status</th>
              <th className="text-center">Modify</th>
            </tr>
          </thead>
          <tbody>
            {data.map(user => {
                return ( <tr key={user.id}>
                            <td>
                              <div>ID { user.id } </div>
                              <div className="small text-muted">
                                Account ID {user.accountId}
                              </div>
                            </td>
                            <td>
                              <div>{ user.name + " " + user.surname }</div>
                            </td>
                            <td>
                              <div>{ user.username }</div>
                            </td>
                            <td>
                              <div className="text-center">
                                <FlagIcon code={user.language} size={'2x'} />
                              </div>
                            </td>
                            <td>
                              <strong>{ user.country }</strong>
                            </td>
                            <td>
                              <div className="float-left">
                                <div>{ user.email }</div>
                              </div>
                            </td>
                            <td className="text-center">
                              <NavLink>
                                { user.active ? (
                                    <Badge color="success">Active</Badge>
                                  ) : (
                                    <Badge color="danger">Inactive</Badge>
                                  )}
                              </NavLink>
                            </td>
                            <td className="text-center">
                              <NavLink href={`/user/${user.id}`}>
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

export default UserTable;
