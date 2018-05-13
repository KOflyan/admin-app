import 'bootstrap/dist/css/bootstrap.css';
//import 'bootstrap/dist/css/bootstrap-theme.css';
import './css/forms.css'
import './css/home.css'

import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import App from './App';

// //REDUX
// import { Provider } from "react-redux";
// import store from "./redux";

import registerServiceWorker from './registerServiceWorker';

// Log changes in the store
// store.subscribe(() => {
//     console.log(store.getState());
// })


ReactDOM.render(
    // <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    // </Provider>
    ,
    document.getElementById('root')
);

registerServiceWorker();
