import {userReducer as user} from "./UserManagement"
import {tokenReducer as token} from "./Login";
import {titleReducer as title} from "./Header";

import { routerReducer as router } from 'react-router-redux'

import {combineReducers} from 'redux'

export default combineReducers({
    user,
    title,
    token,
    router
})