import React, { Component } from 'react';
import logo from './logo.svg';
import Content from './components/Content';
import Header from './components/Header';

import './App.css';

class App extends Component {
  render() {
    return (
      <div>
        <div>
          <Header />
        </div>
        <div className="App-header"></div>
        <div>
          <Content />
        </div>
      </div>
    );
  }
}

export default App;
