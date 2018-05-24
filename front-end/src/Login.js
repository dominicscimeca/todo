import React from 'react'
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import {HOME_URL} from "./App";

let usernameInput = React.createRef();
let passwordInput = React.createRef();

const Login = ({submitLogin, isAuthenticated}) => {
    if(isAuthenticated){
        return (
            <Redirect to={HOME_URL} />
        );
    }

    return (

        <form onSubmit={submitLogin}>
            <div>
                <label htmlFor="username">Username</label>
                <input type="text" ref={usernameInput}/>
            </div>
            <div>
                <label htmlFor="password">Password</label>
                <input type="password" ref={passwordInput}/>
            </div>
            <div>
                <input type="submit"/>
            </div>
        </form>
    );
};

const submitLogin = (token) => {
    return {
        type: LOGIN_SUCCESS,
        token
    }
};

export const LOGIN_SUCCESS = "LOGIN_SUCCESS";
export const LOGOUT = "LOGOUT";

export const tokenReducer = (token = false, action) => {
    switch(action.type){
        case LOGIN_SUCCESS:
            return action.token;
        case LOGOUT:
            return false;
        default:
            return token;
    }
};

export const logOut = () => {
    return {
        type: LOGOUT
    }
};

const mapStateToProps = state => {
    return {
        err: false,
        isAuthenticated: state.token
    }
};

const mapDispatchToProps = dispatch => {
  return {
      submitLogin: (e) => {
          e.preventDefault();
          dispatch(submitLogin(usernameInput.current.value))
      }
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login)