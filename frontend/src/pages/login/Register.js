import React, { Component, Fragment } from 'react';

class Register extends Component {
    render() {
        return (
            <Fragment>
                <div>
                    <Form name='register'>
                        <input name='emailorphone' />
                        <input name='fullname' />
                        <input name='username' />
                        <input name='password' />
                        <input type='submit' />
                    </Form>
                </div>
            </Fragment>
        );
    }
}

export default Register;