import React from 'react';
import './App.css';
import {connect} from 'react-redux'
import {Route} from 'react-router-dom'
import Login from './Login'
import Header from './Header'

export const HOME_URL = '/';
export const LOGIN_URL = '/login';

function App(props){
    return (
      <div className="App">
        <Header/>
        <div className="App-intro">
            <Route exact path="/login" component={Login} />
        </div>
      </div>
    );
}

const mapStateToProps = (state) => {
  return { title: state.title }
};
export default connect(mapStateToProps)(App);
