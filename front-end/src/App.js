import React from 'react';
import logo from './logo.svg';
import './App.css';
import {connect} from 'react-redux'
import UserManagement from './UserManagement'

function App(props){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">{props.title}</h1>
        </header>
        <div className="App-intro">
          Get User from backend
            <UserManagement></UserManagement>
        </div>
      </div>
    );
}

const mapStateToProps = (state) => {
  return { title: state.title }
};
export default connect(mapStateToProps)(App);
