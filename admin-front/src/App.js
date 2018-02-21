import React, { Component } from 'react';
import logo from './logo.svg';
import Content from './components/Content';

import './App.css';

class App extends Component {
  render() {
    return (
      <div>
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to Admin-App in development mode</h1>
          </header>
        </div>
        <div>
          <Content />
        </div>
      </div>
    );
  }
}

export default App;
