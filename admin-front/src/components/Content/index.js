import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './../../views/Home'
import Login from './../../views/Login'
import RegisterUser from './../../views/RegisterUser'
import RegisterAdmin from './../../views/RegisterAdmin'
import UserTable from './../../views/User'
import User from './../../views/User/user'


const Content = () => (
  <main>
    <Switch>
      <Route exact path='/' component={ Home }/>
      <Route path='/home' component={ Home }/>
      <Route path='/login' component={ Login }/>
      <Route path='/registerUser' component={ RegisterUser }/>
      <Route path='/registerAdmin' component={ RegisterAdmin }/>
      <Route exact path='/users' component={ UserTable }/>
      <Route path='/user/:id' component={ User }/>
      <Route exact path='/accounts' component={ AccountTable }/>
      <Route path='/account/:id' component={ Account }/>
    </Switch>
  </main>
)

export default Content
