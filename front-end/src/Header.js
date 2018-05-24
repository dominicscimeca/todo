import React from 'react'
import {connect} from 'react-redux'
import logo from './logo.svg';
import UserManagement from "./UserManagement"

const TITLE_BASE = "Great Redux Title!";
let counter = 0;

const Header = ({title, updateTitle}) => {
    return (
        <header className="App-header">
            <UserManagement/>
            <img src={logo} className="App-logo" alt="logo" onClick={updateTitle} />
            <h1 className="App-title">{title}</h1>
        </header>
    );
};

export const updateTitle = (title) => {
    return {
        type: UPDATE_TITLE,
        title
    }
};

export const UPDATE_TITLE = "UPDATE_TITLE";

export const titleReducer = (title = "", action) => {
    switch(action.type){
        case UPDATE_TITLE:
            return action.title;
        default:
            return title;
    }
};

const getNextTitle = () => {
    return TITLE_BASE + " #" + counter++;
};

const mapStateToProps = state => {
  return {
      title: state.title
  }
};

const mapDispatchToProps = dispatch => {
  return {
      updateTitle: () => dispatch(updateTitle(getNextTitle()))
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Header)