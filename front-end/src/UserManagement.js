import React from "react"
import {connect} from 'react-redux'
let input = React.createRef();

const UserManagement = ({user, getUser}) => {
    let userDescription = "";

    if(user.firstname){
        userDescription = `${user.firstname} ${user.lastname}`
    }else if (user.loading){
        userDescription = "Loading...."
    }else{
        userDescription = "Click to load"
    }

    return (
        <div>
            <form onSubmit={getUser}>
                <div>Find User</div>
                <input type="text" ref={input}/>
                <input type="submit"/>
            </form>
            <div>User: {userDescription}</div>
        </div>
    );
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

const getUser = (userId) => {
    return (dispatch) => {
        dispatch(userRequest(userId));
        fetch("http://localhost:8080/users/"+userId)
            .then(data => data.json())
            .then(user => dispatch(userSuccess(user)))
            .catch(err => dispatch(userFailure(err)))
    }
};

export const userReducer = (user = {}, action) => {
    if(action.type === GET_USER_SUCCESS){
        return action.user
    }else{
        return user;
    }
};

const mapStateToProps = state => {
    return {
        user: state.user
    }
};

const mapDispatchToProps = dispatch => {
    return {
        getUser: (e) => {
            e.preventDefault();
            dispatch(getUser(input.current.value))
        }
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserManagement)