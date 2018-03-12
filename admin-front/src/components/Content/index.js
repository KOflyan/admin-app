import React from 'react'
import { Switch, Route } from 'react-router-dom'
import Home from './../Home'
import Login from './../Login'
import Register from './../Register'
import RegisterAdmin from './../RegisterAdmin'


const Content = () => (
  <main>
    <Switch>
      <Route exact path='/' component={ Home }/>
      <Route path='/home' component={ Home }/>
      <Route path='/login' component={ Login }/>
      <Route path='/register' component={ Register }/>
      <Route path='/registerAdmin' component={ RegisterAdmin}/>
    </Switch>
  </main>
)

export default Content
