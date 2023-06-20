import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import rootReducer from './module/auth';
import createSagaMiddleware from 'redux-saga';
import ErrorBoundary from './component/ErrorBoundary';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(sagaMiddleware)),
);

//sagaMiddleware.run(rootSaga);
ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App/>
     {//TODO 얘가 필요할까? axios response error로 처리하면 <ErrorBoundary>
        //<App />
      //</ErrorBoundary>
    }
    </BrowserRouter>
  </Provider>,
  document.getElementById('root'),
);