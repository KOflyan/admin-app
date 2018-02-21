import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './../css/home.css'

class Home extends React.Component {
  render() {
    return (
      <div>
        <Link to="/register">
          <button class="btn btn-outline-primary"> Register New User </button>
        </Link>
        <br/>
        <h2>Hello, I am Home page</h2>
        <br></br>
        <br></br>
        <h1>Here will be user information</h1>
      </div>
    )
  }
}

export default Home;
