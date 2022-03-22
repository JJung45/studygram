import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import RegisterComponent from '../../component/auth/RegisterComponent';
import { changeField, initializeForm, register } from '../../module/auth/auth';

const RegisterContainer = () => {
    const dispatch = useDispatch();
    const { form, auth, authError } = useSelector(({ auth }) => ({
        form : auth.register,
        auth : auth.auth,
        authErorr : auth.authErorr,
    }));

    const onChange = (e) => {
        const { value, name } = e.target;

        dispatch(
            changeField({
                form: 'register',
                key: name,
                value
            })
        )
    }

    const onSubmit = (e) => {
        const { username, password, fullname } = form;
        e.preventDefault();
        dispatch(register({ username, password, fullname }));
    }

    useEffect(() => {
        dispatch(initializeForm('register'));
    }, [dispatch]);

    useEffect(() => {
        if(authError) {
            console.log('오류');
            console.log(authError);
            return;
        }

        if(auth) {
            console.log('회원가입 성공');
            console.log(auth);
            return;
        }
    },[authError, auth]);

    return(
        <RegisterComponent
            type="register"
            form={form}
            onChange={onChange}
            onSubmit={onSubmit}
        />
    );
}

export default RegisterContainer;