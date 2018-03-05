import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import logo from './../../logo.svg';


import './style.css'

class Header extends React.Component {
  render() {
    return (
      <nav className='nav-container container-fluid'>
          <div className='row'>
           <ul className='custom-nav col-12 col-lg-8'>
             <li>
              <NavLink to='/home' activeClassName='active'>Home</NavLink>
             </li>
             <li>
              <NavLink to='/register' activeClassName='active'>Register</NavLink>
             </li>
           </ul>

           <form class="form-inline my-2 my-lg-0">
             <input class="form-control mr-sm-2" type="text" placeholder="Search"/>
             <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
           </form>
          <img src={logo} className="App-logo" alt="logo" />
       </div>
     </nav>
    )
  }
}
export default Header;
