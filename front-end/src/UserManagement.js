import React from "react"
import {connect} from 'react-redux'
import {Redirect, Link} from 'react-router-dom'
import {LOGIN_URL} from "./App";
import {logOut, LOGOUT} from "./Login";
import { push } from 'react-router-redux'

const UserManagement = ({user, getUser, isAuthenticated, logOut, token}) => {
    if(!isAuthenticated){
        return (
            <Redirect to={LOGIN_URL} />
        );
    }

    if(user.firstname){
        return (
            <div>
                Welcome {user.firstname} {user.lastname}! <button onClick={logOut}>Log out</button>
            </div>
        );
    }else if (user.err) {
        return (
            <div style={{color:"red"}}>
                Error User Not Found ...
            </div>
        );
    }else if(user.loading) {
        return (
            <div>
                Loading ...
            </div>
        );
    }else{
        if(token) {
            getUser(token);
        }
        return (
            <Link to={LOGIN_URL}>Login</Link>
        );
    }
};

const GET_USER_REQUEST = "GET_USER_REQUEST";
const GET_USER_SUCCESS = "GET_USER_SUCCESS";
const GET_USER_FAILURE = "GET_USER_FAILURE";

const userRequest = (userId) => {
    return {
        type: GET_USER_REQUEST,
        userId: userId
    }
};

const userSuccess = (user) => {
    return {
        type: GET_USER_SUCCESS,
        user: user
    }
};

const userFailure = (err) => {
    return {
        type: GET_USER_FAILURE,
        err: err
}
};
const handleErrors = (response) => {
    if (!response.ok) {
        throw new Error(response.statusText);
    }
    return response;
};

const getUser = (userId) => {
    return (dispatch) => {
        dispatch(userRequest(userId));
        fetch("http://localhost:8080/users/"+userId)
            .then(handleErrors)
            .then(data => data.json())
            .then(user => dispatch(userSuccess(user)))
            .catch(err => dispatch(userFailure(err)))
    }
};

export const userReducer = (user = {}, action) => {
    switch(action.type){
        case GET_USER_SUCCESS:
            return action.user;
        case GET_USER_FAILURE:
            return {err: action.err.message};
        case GET_USER_REQUEST:
            return {...user, loading: true};
        case LOGOUT:
            return {};
        default:
            return user;
    }
};

const mapStateToProps = state => {
    return {
        user: state.user,
        isAuthenticated: state.token,
        token: state.token
    }
};

const mapDispatchToProps = dispatch => {
    return {
        getUser: id => dispatch(getUser(id)),
        logOut: () => dispatch(logOut()),
        redirectToLogin: () => dispatch(push(LOGIN_URL))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserManagement)