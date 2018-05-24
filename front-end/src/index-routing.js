import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';


import BasicExample from './RoutingTest'
import rootReducer from "./rootReducer";
import {createStore} from "redux";
import {Provider} from 'react-redux'
import {BrowserRouter as Router} from "react-router-dom";


const store = createStore(rootReducer);

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <BasicExample/>
        </Router>
    </Provider>
    , document.getElementById('root'));
registerServiceWorker();
