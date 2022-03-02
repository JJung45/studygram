import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';
import createRequestSaga, { createRequestActionTypes } from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';
import * as authAPI from '../../lib/api/auth';

const CHANGE_FIELD = 'auth/CHANGE_FIELD';
const INITALIZE_FORM = 'auth/INITALIZE_FORM';

const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE]
= createRequestActionTypes('auth/REGISTER');

const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE]
= createRequestActionTypes('auth/LOGIN');

export const changeField = createAction(
    CHANGE_FIELD,
    ({ form, key, value }) => ({
        form,
        key,
        value
    })
)

export const initializeForm = createAction(INITALIZE_FORM, (form) => form);

export const register = createAction(REGISTER, ({ username, fullname, password }) => ({
    username,
    fullname,
    password
}))

export const login = createAction(LOGIN, ({ username, password }) => ({
    username,
    password
}))

const registerSaga = createRequestSaga(REGISTER, authAPI.register)

const loginSaga = createRequestSaga(LOGIN, authAPI.login)

export function* authSaga() {
    yield takeLatest(REGISTER, registerSaga);
    yield takeLatest(LOGIN, loginSaga);
}

const init = {
    register: {
        username: '',
        fullname: '',
        password: '',
    },
    login: {
        username: '',
        password: '',
    },
    auth: null,
    authError: null
}

const auth = handleActions(
    {
        [CHANGE_FIELD]: (state, { payload: { form, key, value }}) =>
            produce(state, draft => {
                draft[form][key] = value;
            }),
        [INITALIZE_FORM]: (state, { payload: form }) => ({
            ...state,
            [form] : init[form],
        }),
        [REGISTER_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth,
        }),
        [REGISTER_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error
        }),
        [LOGIN_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth,
        }),
        [LOGIN_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error,
        })
    },
    init,
)

export default auth;