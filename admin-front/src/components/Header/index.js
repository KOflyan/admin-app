import React from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import { logout } from './../../utils/Auth';

class Header extends React.Component {

  constructor(props) {
    super(props);

    this.toggleNavbar = this.toggleNavbar.bind(this);
    this.state = {
      collapsed: true
    };
  }

  toggleNavbar() {
    this.setState({
      collapsed: !this.state.collapsed
    });
  }

  render() {
    return (
      <div>
        <Navbar color="faded" light>
          <NavbarBrand href="/home" className="mr-auto">Home</NavbarBrand>
          <NavbarToggler onClick={this.toggleNavbar} className="mr-2" />
          <Collapse isOpen={!this.state.collapsed} navbar>
            <Nav navbar>
              <NavItem>
                <NavLink href="/users">Users</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/accounts">Accounts</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/devices">Devices</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/"><div onClick={ logout }>Logout</div></NavLink>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
     </div>
    );
  }
}
export default Header;
