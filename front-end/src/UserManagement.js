import React from "react"
import {connect} from 'react-redux'

function UserManagement(props){
    let input;
    return (
        <div>
            <form action="">
                <div>Find User</div>
                <input type="text" ref={input}/>
            </form>
            <button onClick={props.getUser}>Click to get User</button>
            { props.user.firstname ? (
                <div>User: {props.user.firstname} {props.user.lastname}</div>
            ) : (
                <div>Load user</div>
            ) }
        </div>
    );
}

const GET_USER_REQUEST = "GET_USER_REQUEST";
const GET_USER_SUCCESS = "GET_USER_SUCCESS";
const GET_USER_FAILURE = "GET_USER_FAILURE";

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

const getUser = () => {
    return (dispatch) => {
        fetch("http://localhost:8080/users/1")
            .then(user => dispatch(userSuccess(user)))
            .catch(err => userFailure(err))
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
        getUser: () => dispatch(getUser())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserManagement)