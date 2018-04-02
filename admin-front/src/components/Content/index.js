import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './../../views/Home'
import Login from './../../views/Login'
import Register from './../../views/Register'
import RegisterAdmin from './../../views/RegisterAdmin'
import UserTable from './../../views/User'
import User from './../../views/User/user'


const Content = () => (
  <main>
    <Switch>
      <Route exact path='/' component={ Home }/>
      <Route path='/home' component={ Home }/>
      <Route path='/login' component={ Login }/>
      <Route path='/register' component={ Register }/>
      <Route path='/registerAdmin' component={ RegisterAdmin }/>
      <Route exact path='/user' component={ UserTable }/>
      <Route path='/user/:id' component={ User }/>
    </Switch>
  </main>
)

export default Content
