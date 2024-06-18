import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import './AddUserComponent.css'


class AddProjectDetailComponent extends Component{

    constructor(props) {
        super(props);
        if (props.location.state && props.location.state.student) {
          this.state = {
            student: props.location.state.student,
    
            projectName: "",
            projectDescription: "",
            projectGitLink: "",
            message: null,
          };
          this.saveDetails = this.saveDetails.bind(this);
        }
      }

    saveDetails = (e) => {
        e.preventDefault();
        let project = {projectName: this.state.projectName, projectDescription: this.state.projectDescription,projectGitLink: this.state.projectGitLink};
        let valid=true;
    for(var i in project){
      if(!project[i]){
        valid=false;
        alert("please add "+i);
      }
      
    }
       
       if(valid){
        ApiService.addProjectDetails(project)
        .then(resp => {
            console.log(resp.data);//actual response data sent by back end
            this.setState({message : 'Project Details Added Successfully.'});
            alert('Project details added!!')
            document.getElementById('123').click();
            this.props.history.push('/showProfile');
        }).catch( err=>{
          //  console.error(err);
            console.error("in err ",err);
            //err.response.data => DTO on the server side : ErrorResponse
            alert(err.response.data.message);             
            this.props.history.push('/');
        })
       }     
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        if(!this.state){
            this.props.history.push('/');
            return <></>
          }
        return(
            <div className='signupScreen'>
                <form>
                <h1>Project Details</h1>
                <div className="form-group">
                    <label>Project Name :</label>
                    <input type="text" placeholder="projectName" name="projectName" className="form-control" value={this.state.projectName} onChange={this.onChange}/>
                </div>
                <div className="form-group">
                    <label>Project Description : </label>
                    <input type="text" placeholder="projectDescription" name="projectDescription" className="form-control" value={this.state.projectDescription} onChange={this.onChange}/>
                </div>
                <div className="form-group">
                    <label>Project git Link :</label>
                    <input type="text" placeholder="gitLink" name="projectGitLink" className="form-control" value={this.state.projectGitLink} onChange={this.onChange}/>
                </div>
                <button className="btn btn-success" onClick={this.saveDetails}> Add </button>
               </form>
            </div>
        );
    }
}

export default AddProjectDetailComponent;