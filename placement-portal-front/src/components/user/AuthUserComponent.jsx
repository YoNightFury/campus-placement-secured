import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import './AddUserComponent.css'

class AuthUserComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
            userName: '',
            password: '',
            
            message: null
        }
        this.authStudent = this.authStudent.bind(this);
    }

    authStudent = (e) => {
        // prevent the default action
        e.preventDefault();
        // send the login request
        let cred = { userName: this.state.userName, password: this.state.password };
        ApiService.loginUser(cred)
          .then((resp) => {
            // got the user with jwt
            console.log(resp.data);
            sessionStorage.setItem("token", resp.data.jwt);
            sessionStorage.setItem("studentid", resp.data.user.id);
            if (resp.data.user.prn) {
              this.props.history.push({
                pathname: "/profile",
                state: { student: resp.data.user, loginUser: true },
              });
              window.location.reload();
            } else {
              this.props.history.push("/");
            }
          })
          .catch((err) => {
            console.log("cannot log in err: " + err);
            alert(err.response.data.message);
            this.props.history.push("/sign-in");
          });
      };

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });
    
        sendToRegister(){
            
            this.props.history.push('/register-user');
        }

    render() {
        return(
            <div className='signupScreen margin-top-1'>
                <form>
                <h1>Sign in!</h1>
                <div className="form-group">
                    <label>User Name:</label>
                    <input type="text" placeholder="userName" name="userName" className="form-control" value={this.state.userName} onChange={this.onChange}/>
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input type="password" placeholder="password" name="password" className="form-control" value={this.state.password} onChange={this.onChange}/>
                </div>
                
                <button className="btn btn-success" onClick={this.authStudent}>Sign In</button>
                <h4><span className="signupScreen_gray">Not a member? </span>
                <span className="signupScreen_link" onClick={() => this.sendToRegister()}>Register Now.</span>
                </h4>
            </form>
    </div>
        );
    }
}

export default AuthUserComponent;