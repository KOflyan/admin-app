import React, { Component } from 'react';
import Content from './components/Content';
import Header from './components/Header';
import Login from './components/Login';
import { isLoggedIn } from './utils/Auth';

class App extends Component {
  render() {
    return (
      <div>
        { !isLoggedIn() ?
          <div><Login /></div> :
          <div>
            <div>
              <Header />
            </div>
            <div>
              <Content />
            </div>
          </div>
        }
      </div>
    );
  }
}

export default App;
