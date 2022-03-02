import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux"
import AuthForm from '../../component/auth/AuthForm';
import { changeField, initializeForm, login } from '../../module/auth/auth';

const LoginForm = () => {
    const dispatch = useDispatch();
    const { form, auth, authError } = useSelector(({ auth }) => ({
        form: auth.login,
        auth: auth.auth,
        authError: auth.authError
    }));

    const onChange = (e) => {
        const { value, name } = e.target;
        
        dispatch(
            changeField({
                form: 'login',
                key : name,
                value
            })
        )
    }

    const onSubmit = (e) => {
        e.preventDefault();
        const { username, password } = form;
        dispatch(login({ username, password }));
    }

    useEffect(() => {
        dispatch(initializeForm('login'));
    },[dispatch]);

    useEffect(() => {
        if(authError) {
            console.log('오류');
            console.log(authError);
            return;
        }

        if(auth) {
            console.log('로그인 성공');
            console.log(auth);
        }
    },[authError, auth, dispatch]);

    return(
        <AuthForm
            type="login"
            form={form}
            onChange={onChange}
            onSubmit={onSubmit}
        />
    );
};

export default LoginForm;