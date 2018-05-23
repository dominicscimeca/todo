import {userReducer} from "./UserManagement"
import {titleReducer} from "./TitleManagement"

import {combineReducers} from 'redux'
export default combineReducers({
    user: userReducer,
    title: titleReducer
})