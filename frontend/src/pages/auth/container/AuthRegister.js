import React, { userEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

const AuthRegister = () => {
    const dispatch = useDispatch();
    const { type, auth, authError } = useSelector(({ auth }) => {
        type : auth.register;
        auth : auth.auth;
        authErorr : auth.authErorr;
    });

    // TODO 여기부터
    const onChange = (e) => {

    }
}