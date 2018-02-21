import React, { Component } from 'react';
import logo from './../logo.svg';
import Content from './Content';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to Admin-App in development mode</h1>
        </header>
        <Content />
      </div>
    );
  }
}

export default App;
