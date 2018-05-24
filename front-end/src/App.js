import React from 'react';
import './App.css';
import {Route} from 'react-router-dom'
import Login from './Login'
import Header from './Header'

export const HOME_URL = '/';
export const LOGIN_URL = '/login';

const App = () => {
    return (
      <div className="App">
        <Header/>
        <div className="App-intro">
            <Route path="/login" component={Login} />
        </div>
      </div>
    );
};

export default App;
