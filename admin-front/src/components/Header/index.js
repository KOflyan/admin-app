import React from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';

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
                <NavLink href="/register">Register New User</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/registerAdmin">Add New Corporate User</NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/devices">Devices</NavLink>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
     </div>
    );
  }
}
export default Header;
