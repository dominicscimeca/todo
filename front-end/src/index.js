import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {applyMiddleware, createStore} from 'redux';
import thunk from 'redux-thunk'
import rootReducer from './rootReducer'
import initialState from './initialState'
import {Provider} from 'react-redux'
import createHistory from 'history/createBrowserHistory'
import {routerMiddleware} from 'react-router-redux'
import {BrowserRouter as Router} from "react-router-dom";

const history = createHistory();
const middleware = routerMiddleware(history);
const store = createStore(rootReducer, initialState, applyMiddleware(thunk, middleware));

ReactDOM.render(
    <Provider store={store}>
        <Router>
           <App/>
        </Router>
    </Provider>, document.getElementById('root'));
registerServiceWorker();
