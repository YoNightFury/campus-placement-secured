import React, { Component } from "react";
import ApiService from "../../service/ApiService";
import "./AddUserComponent.css";

class EditProfileComponent extends Component {
  constructor(props) {
    super(props);
    if (props.location.state && props.location.state.student) {
      this.state = {
        firstName: props.location.state.student.firstName,
        lastName: props.location.state.student.lastName,
        dob: props.location.state.student.dob,
        email: props.location.state.student.email,
        mobNo: props.location.state.student.mobNo,
        address: props.location.state.student.address,
        gitLink: props.location.state.student.gitLink,
        linkedIn: props.location.state.student.linkedIn,
        mark10th: props.location.state.student.mark10th,
        mark12th: props.location.state.student.mark12th,
        markDiploma: props.location.state.student.markDiploma,
        markGrad: props.location.state.student.markGrad,
        markPostGrad: props.location.state.student.markPostGrad,
        markCCEE: props.location.state.student.markCCEE,
        passingYear10th: props.location.state.student.passingYear10th,
        passingYear12th: props.location.state.student.passingYear12th,
        passingYearDiploma: props.location.state.student.passingYearDiploma,
        passingYearGrad: props.location.state.student.passingYearGrad,
        passingYearPostGrad: props.location.state.student.passingYearPostGrad,
      };

      this.saveStudent = this.saveStudent.bind(this);
      this.onChange = this.onChange.bind(this);
    }
  }

  saveStudent(e) {
    e.preventDefault();
    let student = {
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      dob: this.state.dob,
      email: this.state.email,
      mobNo: this.state.mobNo,
      address: this.state.address,
      gitLink: this.state.gitLink,
      linkedIn: this.state.linkedIn,
      mark10th: this.state.mark10th,
      mark12th: this.state.mark12th,
      markDiploma: this.state.markDiploma,
      markGrad: this.state.markGrad,
      markPostGrad: this.state.markPostGrad,
      markCCEE: this.state.markCCEE,
      passingYear10th: this.state.passingYear10th,
      passingYear12th: this.state.passingYear12th,
      passingYearDiploma: this.state.passingYearDiploma,
      passingYearGrad: this.state.passingYearGrad,
      passingYearPostGrad: this.state.passingYearPostGrad,
    };
    ApiService.updateStudent(student).then((resp)=>{
        alert('Profile updated!');
        
        document.getElementById('123').click();
    }).catch((err)=>{
      alert(err);
    })
}

onChange=(e)=>this.setState({[e.target.name]:e.target.value});


  render() {
    if(!this.state){
      this.props.history.push('/');
      return <></>;
    }
    return (
      <div>
        <h2 className="text-center">Edit Profile!</h2>

        <form>
        <div className='edit-grid'>
          <div>
          <fieldset>
          
            <legend className="increase-font-2">Basic Details!</legend>
            
            <div className="form-group">
              <span className="increase-font">First Name:</span>
              <input
                placeholder="First Name"
                name="firstName"
                className="form-control"
                value={this.state.firstName}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Last Name:</span>
              <input
                placeholder="Last name"
                name="lastName"
                className="form-control"
                value={this.state.lastName}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Date of Birth:</span>
              <input
                type="date"
                name="dob"
                className="form-control"
                value={this.state.dob}
                onChange={this.onChange}
              />
            </div>
          </fieldset>
          </div>
        <div>
          <fieldset>
            <legend className="increase-font-2">Academic Details!</legend>

            <div className="form-group">
              <span className="increase-font">Class 10th marks:</span>
              <input
                type="number"
                placeholder="10th marks"
                name="mark10th"
                className="form-control"
                value={this.state.mark10th}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Class 10th passing date:</span>
              <input
                type="date"
                placeholder="year of class 10th"
                name="passingYear10th"
                className="form-control"
                value={this.state.passingYear10th}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Class 12th marks:</span>
              <input
                type="number"
                placeholder="12th marks"
                name="mark12th"
                className="form-control"
                value={this.state.mark12th}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Class 12th passing date:</span>
              <input
                type="date"
                placeholder="year of class 12th"
                name="passingYear12th"
                className="form-control"
                value={this.state.passingYear12th}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Diploma marks(if any):</span>
              <input
                type="number"
                placeholder="diploma marks"
                name="markDiploma"
                className="form-control"
                value={this.state.markDiploma}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Diploma passing date:</span>
              <input
                type="date"
                placeholder="year of passing diploma"
                name="passingYearDiploma"
                className="form-control"
                value={this.state.passingYearDiploma}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Graduation marks:</span>
              <input
                type="number"
                placeholder="graduation marks"
                name="markGrad"
                className="form-control"
                value={this.state.markGrad}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Graduation date:</span>
              <input
                type="date"
                placeholder="year of graduation"
                name="passingYearGrad"
                className="form-control"
                value={this.state.passingYearGrad}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Post Graduation Marks:</span>
              <input
                type="number"
                placeholder="post graduation marks"
                name="markPostGrad"
                className="form-control"
                value={this.state.markPostGrad}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Post Graduation date:</span>
              <input
                type="date"
                placeholder="year of post-graduation"
                name="passingYearPostGrad"
                className="form-control"
                value={this.state.passingYearPostGrad}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">CCEE marks:</span>
              <input
                type="number"
                placeholder="CCEE marks"
                name="markCCEE"
                className="form-control"
                value={this.state.markCCEE}
                onChange={this.onChange}
              />
            </div>
          </fieldset>
          </div>
      <div>
          <fieldset>
            <legend className="increase-font-2">Contact Details!</legend>

            <div className="form-group">
              <span className="increase-font">Email:</span>
              <input
                type="email"
                placeholder="email address"
                name="email"
                className="form-control"
                value={this.state.email}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Mobile Number:</span>
              <input
                type="number"
                placeholder="mobile number"
                name="mobNo"
                className="form-control"
                value={this.state.mobNo}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Address:</span>
              <input
                type="text"
                placeholder="Address"
                name="address"
                className="form-control"
                value={this.state.address}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">Git profile link:</span>
              <input
                type="text"
                placeholder="git-link"
                name="gitLink"
                className="form-control"
                value={this.state.gitLink}
                onChange={this.onChange}
              />
            </div>

            <div className="form-group">
              <span className="increase-font">LinkedIn profile Link:</span>
              <input
                type="text"
                placeholder="LinkedIn link"
                name="linkedIn"
                className="form-control"
                value={this.state.linkedIn}
                onChange={this.onChange}
              />
            </div>
          </fieldset>
          </div>

          
          </div>
          
          <button
            href="#sec"
            className="btn btn-success padd-center"
            onClick={this.saveStudent}
          >
            Update!
          </button>
        </form>
      </div>
    );
  }
}

export default EditProfileComponent;
