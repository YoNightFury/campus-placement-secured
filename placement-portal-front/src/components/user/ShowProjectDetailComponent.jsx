import React, { Component } from 'react'
import ApiService from '../../service/ApiService';

export default class ShowProjectDetailComponent extends Component {

    constructor(props) {
        super(props)
        if(props.location.state && props.location.state.student){
        this.state = {
            projects:[],
            message: null,
            student:props.location.state.student,
  
            
        }
    }
        
    }

    componentDidMount() {
        if(this.state){
        this.reloadProjectList();
        }
    }

    reloadProjectList() {
        ApiService.fetchProjectDetails(this.state.student.id)
            .then((resp) => {
                this.setState({projects: resp.data},()=>console.log(this.state.projects));
            }).catch((err)=>{
              alert(err);
              console.log(err);
            })
           
    }

    render() {

        if (!(this.state && this.state.student)) {
            this.props.history.push("/");
            return <></>;
          }

        let projects = this.state.projects.map(
            project =>
                        <tr key={project.id}>
                            
                            <td>{project.projectName}</td>
                            <td>{project.projectDescription}</td>
                            <td>{project.projectGitLink}</td>
                            
                            
                            
                            
                        </tr>
                );
    



        return (
            <div>
            <h2 className="text-center">Questions</h2>
            
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th className="hidden">Id</th>
                        <th>Project Name</th>
                        <th>Project Description</th>
                        <th>Project Git Link</th>
                       
                        
                       
                        
                        
                    </tr>
                </thead>
                <tbody>
                    {
                      projects  
                    }
                </tbody>
            </table>

        </div>
        )
    }
}
