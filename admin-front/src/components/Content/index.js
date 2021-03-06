import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './../../views/Home'
import RegisterUser from './../../views/Register/registerUser'
import RegisterAccount from './../../views/Register/registerAccount'
import RegisterDevice from './../../views/Register/registerDevice'
import RegisterAdmin from './../../views/RegisterAdmin'
import UserTable from './../../views/User'
import User from './../../views/User/user'
import AccountTable from './../../views/Account'
import Account from './../../views/Account/account'
import DeviceTable from './../../views/Device'
import Device from './../../views/Device/device'
import Statistics from './../../views/Statistics'


const Content = () => (
  <main>
    <Switch>
      <Route exact path='/' component={ Home }/>
      <Route path='/home' component={ Home }/>
      <Route path='/stats' component={ Statistics }/>
      <Route path='/registerUser' component={ RegisterUser }/>
      <Route path='/registerAccount' component={ RegisterAccount }/>
      <Route path='/registerDevice' component={ RegisterDevice }/>
      <Route path='/registerAdmin' component={ RegisterAdmin }/>
      <Route exact path='/users' component={ UserTable }/>
      <Route path='/user/:id' component={ User }/>
      <Route exact path='/accounts' component={ AccountTable }/>
      <Route path='/account/:id' component={ Account }/>
      <Route exact path='/devices' component={ DeviceTable }/>
      <Route path='/device/:id' component={ Device }/>
    </Switch>
  </main>
)

export default Content
