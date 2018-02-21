import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './Home'
import Login from './Login'
import Register from './Register'


const Main = () => (
  <main>
    <Switch>
      <Route path='/home' component={ Home }/>
      <Route path='/Login' component={ Login }/>
      <Route path='/register' component={ Register }/>
    </Switch>
  </main>
)

export default Main
