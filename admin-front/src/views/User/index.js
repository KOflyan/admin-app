import React from 'react';
import { NavLink } from 'reactstrap';
import logo from './../../img/logo.svg';
import { Table, Badge } from 'reactstrap';
import UserApi from './../../utils/UserApi';

class UserTable extends React.Component {
  constructor() {
    super();

    this.state = {
      data: [],
    };
  }

  getDataOnLoad = () => {
    UserApi.all(apiData => {
      this.setState({
        data: apiData
      });
    });
  }
  componentDidMount() {
    this.getDataOnLoad();
  }

  render() {
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
              <th>User</th>
              <th>Username</th>
              <th>Language</th>
              <th>Country</th>
              <th>Email</th>
              <th className="text-center">Active</th>
              <th className="text-center">Modify</th>
            </tr>
          </thead>
          <tbody>
            {this.state.data.map(user => {
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
                              {/*<i className="h4 mb-0 flag-icon flag-icon-{{ user.language }}"></i>*/}
                              <strong>{ user.language }</strong>
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
      </div>
    )
  }
}

export default UserTable;
