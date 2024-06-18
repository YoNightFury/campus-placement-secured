import React from "react";

import './ShowProfileComponent.css'
import img2 from '../../images/avatar-370-456322.png'
import ApiService from '../../service/ApiService'
import './ShowProfileComponent.css'







class ShowProfileComponent extends React.Component {

    constructor(props) {
        super(props);
    
        if (props.location.state && props.location.state.student) {
          this.state = {
            student: props.location.state.student,
            message: null,
          };
    
          ApiService.fetchPhoto(this.state.student.id)
            .then((resp) => {
              // set the photo as the property of student
              console.log("found photo id ", resp.data.id);
              this.state.student.photo = resp.data;
              document.getElementsByTagName("img")[0].src =
                "data:image/png;base64," + this.state.student.photo.photo;
            })
            .catch((err) => {
              console.log("err in finding photo", err);
              this.state.student.photo = null;
            });
        }
      }

    uploadResume() {
        this.props.history.push({
            pathname: '/upload-resume',

            state: { student: this.state.student }

        })
    }

    addPlacementDetails() {
        ApiService.fetchCompanies().then((resp) => {
            console.log(resp.data);
            this.props.history.push({
                pathname: '/add-placement-details',
                state: { companyNames: resp.data }

            });

        }).catch((err) => {
            console.log(err);

        }
        );


    }
    showResume(id){
        ApiService.fetchResume(id).then((resp)=>{
            const url = URL.createObjectURL(resp.data); // Create object URL from blob
            // Open the URL in a new tab
            window.open(url, '_blank');      
        });
    }

    addQuestions() {

        ApiService.fetchPlacement(this.state.student.id)
            .then((resp) => {
                console.log("placement details " + resp.data);
                if (resp.data[0]) {
                    this.props.history.push({
                        pathname: '/add-questions',
                        state: { placements: resp.data }
                    });
                } else {
                    alert("please add placement details first");
                }

            }).catch((err) => {
                alert(err);
                console.log(err);
            });


    }

    // downloadResume(){
    //     ApiService.
    // }

    logout() {
        sessionStorage.removeItem('studentid');
        sessionStorage.removeItem('token');
        this.state.student = null;
        this.props.history.push('/');
        window.location.reload();
    }

    addProject() {
        this.props.history.push({
            pathname: '/add-project',

            state: { student: this.state.student }

        })
    }
    changePhoto() {
        this.props.history.push({
            pathname: '/change-photo',

            state: { student: this.state.student }

        })
    }


    editProfile() {
        this.props.history.push({
            pathname: "/edit-profile",

            state: { student: this.state.student },
        });
    }

    showPlacementDetails() {
        this.props.history.push({
            pathname: "/placement-details",
            state: { student: this.state.student }
        })
    }

    showProjectDetails() {
        this.props.history.push({
            pathname: "/project-details",
            state: { student: this.state.student }
        })
    }

    deleteStudent(){
       
        if(window.confirm("Delete Profile??")){
           ApiService.deleteStudent().then(resp=>{
               alert(resp.data.message);
               sessionStorage.removeItem('token');
               sessionStorage.removeItem('studentid');
               this.state=null;
               this.props.history.push('/');
               window.location.reload();
           }).catch(err=>{
               alert(err.data.message);
           })
        }
    }


    render() {
        
        if (!(this.state && this.state.student)) {
            this.props.history.push("/");
            return <></>;
          }
        let otherId = this.state.student.id;
        let resumelink =
            "/public/download/resume/" + otherId;
        let studentId = sessionStorage.getItem("studentid");

        return (
            <div className='card11 '>
                <div>
                    <div className='text-center'>
                            <button className='btn  btn-space' onClick={()=> this.showResume(otherId)}> Show Resume</button>
                            
                            

                        <button className='btn  btn-space' onClick={() => this.showPlacementDetails()}> Show Placement Details</button>
                        <button className='btn  btn-space' onClick={() => this.showProjectDetails()}> Show Project Details</button>
                        <button className={studentId == otherId ? 'btn  btn-space' : 'hidden'} onClick={() => this.uploadResume()}> Upload Resume</button>
                        <button className={studentId == otherId ? 'btn  btn-space' : 'hidden'} onClick={() => this.addPlacementDetails()}>Add Placement Details</button>
                        <button className={studentId == otherId ? 'btn  btn-space' : 'hidden'} onClick={() => this.addQuestions()}>Add Questions</button>
                        <button className={studentId == otherId ? 'btn  btn-space' : 'hidden'} onClick={() => this.addProject()}>Add Project details</button>
                        <button className={studentId == otherId ? 'btn btn-space' : 'hidden'} onClick={() => this.editProfile()}>edit profile</button>
                        <button className={studentId == otherId ? 'red-button btn-space' : 'hidden'} onClick={()=>this.deleteStudent()}>Delete Profile</button>
                        <button className={studentId == otherId ? 'btn  btn-space' : 'hidden'} onClick={() => this.logout()}> Logout</button>
                    </div>
                </div>



                <div className='profile-data'>
                    <div>
                        <img
                            src={
                                this.state.student.photo
                                    ? "data:image/png;base64," + this.state.student.photo.photo
                                    : img2
                            }
                            className="img-fluid img111"
                            alt="profile"
                        /><br />
                        <button className={studentId == otherId ? 'btn  btn-space-2' : 'hidden'} onClick={() => this.changePhoto()}>Change Photo</button>
                    </div>
                    <div>
                        <div className='data-columns font-css'>
                            {/**basic Details */}
                            <div >
                                <span className='box-head'>Basic Details</span>
                                <table>
                                    <thead><tr><th></th><th></th></tr></thead>
                                    <tbody><tr><td>first Name :</td><td>{this.state.student.firstName}</td></tr>
                                        <tr><td>last Name :</td><td>{this.state.student.lastName}</td></tr>
                                        <tr><td>prn :</td><td>{this.state.student.prn}</td></tr>
                                        <tr><td>date of birth :</td><td>{this.state.student.dob}</td></tr></tbody>
                                </table>
                            </div>


                            {/**Academic Details */}

                            <div>
                                <span className='box-head'>Academic Details</span>
                                <table>
                                    <thead><tr><th></th><th></th></tr></thead>
                                    <tbody>    <tr><td>marks of 10th std :</td><td>{this.state.student.mark10th}</td></tr>
                                        <tr><td>Passing year 10th :</td><td>{this.state.student.passingYear10th}</td></tr>
                                        <tr><td>marks of 12th Std :</td><td>{this.state.student.mark12th}</td></tr>
                                        <tr><td>Passing year 12th :</td><td>{this.state.student.passingYear12th}</td></tr>
                                        <tr><td>marks of diploma :</td><td>{this.state.student.markDiploma}</td></tr>
                                        <tr><td>Passing year of diploma :</td><td>{this.state.student.passingYearDiploma}</td></tr>
                                        <tr><td> marks of graduation :</td><td>{this.state.student.markGrad}</td></tr>
                                        <tr><td>Passing year graduation :</td><td>{this.state.student.passingYearGrad}</td></tr>
                                        <tr><td>marks of post graduation :</td><td> {this.state.student.markPostGrad}</td></tr>
                                        <tr><td> Passing year post graduation :</td><td>{this.state.student.passingYearPostGrad}</td></tr>
                                        <tr><td>marks of CCEE :</td><td>{this.state.student.markCCEE}</td></tr></tbody>
                                </table>

                            </div>



                            {/**Contact Details */}
                            <div >
                                <span className='box-head'>Contact Details</span>
                                <table>
                                    <thead><tr><th></th><th></th></tr></thead>
                                    <tbody><tr><td>mobile :</td><td> {this.state.student.mobNo}</td></tr>
                                        <tr><td>email :</td><td>{this.state.student.email}</td></tr>
                                        <tr><td>git profile link :</td><td>{this.state.student.gitLink}</td></tr>
                                        <tr><td>linkedIn profile link :</td><td>{this.state.student.linkedIn}</td></tr></tbody>
                                </table>


                            </div>



                            {/**Course Details */}
                            <div className='last-component'>
                                <span className='box-head'>Course Details</span>
                                <table>
                                    <thead><tr><th></th><th></th></tr></thead>
                                    <tbody>   <tr><td>course :</td><td> {this.state.student.course.courseName}</td></tr>
                                        <tr><td>batch :</td><td>{this.state.student.course.batch}</td></tr>
                                        <tr><td>year :</td><td>{this.state.student.course.year}</td></tr></tbody>
                                </table>

                            </div>

                            {/**Project Details */}
                        </div>
                    </div>
                </div>

            </div>

        )
    }

}

export default ShowProfileComponent

