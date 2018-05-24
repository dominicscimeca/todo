import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk'
import rootReducer from './rootReducer'
import initialState from './initialState'
import {Provider} from 'react-redux'
import createHistory from 'history/createBrowserHistory'
import { ConnectedRouter, routerMiddleware } from 'react-router-redux'

const history = createHistory();
const middleware = routerMiddleware(history);
const store = createStore(rootReducer, initialState, applyMiddleware(thunk, middleware));

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
           <App/>
        </ConnectedRouter>
    </Provider>, document.getElementById('root'));
registerServiceWorker();
