import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './../Home'
import Login from './../Login'
import Register from './../Register'


const Content = () => (
  <main>
    <Switch>
      <Route exact path='/' component={ Home }/>
      <Route path='/home' component={ Home }/>
      <Route path='/login' component={ Login }/>
      <Route path='/register' component={ Register }/>
    </Switch>
  </main>
)

export default Content
