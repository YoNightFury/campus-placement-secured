import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import './RegisterUserComponent.css'
const year = [
  {
    label: "2019",
    value: "2019",
  },
  {
    label: "2020",
    value: "2020",
  },
  {
    label: "2021",
    value: "2021",
  },
  {
    label: "2022",
    value: "2022",
  },
];

const course = [
  {
    label: "DAC",
    value: "DAC",
  },
  {
    label: "DBDA",
    value: "DBDA",
  },
  {
    label: "DESD",
    value: "DESD",
  },
  {
    label: "DITISS",
    value: "DITISS",
  },
  {
    label: "DAI",
    value: "DAI",
  },
  {
    label: "DVLSI",
    value: "DVLSI",
  },
  {
    label: "DMC",
    value: "DMC",
  },
  {
    label: "DASSD",
    value: "DASSD",
  },
  {
    label: "DGI",
    value: "DGI",
  },
  {
    label: "DRAT",
    value: "DRAT",
  },
];



const batch = [
  {
    label: "JAN",
    value: "JAN",
  },
  {
    label: "JULY",
    value: "JULY",
  },
];

class RegisterUserComponent extends Component {

  constructor(props) {
    super(props);
    this.state = {
      firstName: "",
      lastName: "",
      prn: "",
      dob: "",
      email: "",
      mobNo: "",
      address: "",
      gitLink: "",
      linkedIn: "",
      mark10th: "",
      mark12th: "",
      markDiploma: "",
      markGrad: "",
      markPostGrad: "",
      markCCEE: "",
      passingYear10th: "",
      passingYear12th: "",
      passingYearDiploma: "",
      passingYearGrad: "",
      passingYearPostGrad: "",

      year: "2019",
      batch: "JAN",
      courseName: "DAC",

      userName: "",
      password: "",
      confirmPassword: "",
      role: "",

      studentId: '1',
      message: null
    }
    this.saveStudent = this.saveStudent.bind(this);

  }



  saveStudent = (e) => {
    e.preventDefault();
    const { userName, password, confirmPassword } = this.state;

    let student = {
      course: {
        year: this.state.year,
        batch: this.state.batch,
        courseName: this.state.courseName,
      },
      credential: {
        userName: this.state.userName,
        password: this.state.password,
      },
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      prn: this.state.prn,
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
    let valid = true;
    for (var i in student) {
      if (
        i == "markDiploma" ||
        i == "passingYearDiploma" ||
        i == "mark12th" ||
        i == "passingYear12th" ||
        i == "passingYearPostGrad" ||
        i == "markPostGrad"
      )
        continue;
      if ((i == 'mobNo' || i == 'prn') && ((student[i] + '').length != 10)) {
        valid = false;
        alert('mobile number and prn should be 10 length');

      }
      if ((i + '').includes('mark') && (i != 'mark12th' || i != 'markDiploma') && (student[i] < 33 || student[i] > 100)) {
        valid = false;
        alert('marks should be between 33 and 100');
      }
      if (!student[i]) {
        valid = false;
        alert("please add" + i);
        break;
      }
    }

    if (
      !(
        (student.markDiploma && student.markDiploma < 100 && student.markDiploma > 33 && student.passingYearDiploma) ||
        (student.mark12th && student.mark12th < 100 && student.mark12th > 33 && student.passingYear12th)
      )
    ) {
      valid = false;
      alert("add 12th or diploma details with marks between 33 and 100");
    }

    if (password !== confirmPassword) {
      valid = false;
      alert("Passwords don't match");
    }
    if (valid) {
      ApiService.addStudent(student)
        .then((resp) => {
          console.log(resp.data); //actual response data sent by back end
          this.setState({ message: "Student registered successfully." });
          alert("Successfully Registered");
          this.props.history.push({
            pathname: "/sign-in",
          });
        })
        .catch((err) => {
          //  console.error(err);
          console.error("in err ", err.response.data);
          //err.response.data => DTO on the server side : ErrorResponse
          alert(err.response.data.message);
        });
    }
  };

  onChange = (e) =>
    this.setState({ [e.target.name]: e.target.value });





  render() {
    return (
      <div className='text-center in-grid'>
        <h2 className="text-center">Register</h2>

        <div >
          <form >
            <div className='body-batch-details'>
              <fieldset className='batch-details'>
                <legend className='increase-font-2'>Batch Details!</legend>
                <div className='flex-orient'>
                  <div className="select-container">
                    <span className='increase-font'>Year</span>
                    <select className='width-control' name='year' value={this.state.year} onChange={this.onChange}>
                      {year.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                      ))}
                    </select>
                  </div>

                  <div className="select-container">
                    <span className='increase-font'>Batch</span>
                    <select className='width-control' name='batch' value={this.state.batch} onChange={this.onChange}>
                      {batch.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                      ))}
                    </select>
                  </div>

                  <div className="select-container">
                    <span className='increase-font'>Course</span>
                    <select className='width-control' name='courseName' value={this.state.courseName} onChange={this.onChange}>
                      {course.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                      ))}
                    </select>
                  </div>
                </div>
              </fieldset>
            </div>
          </form>
        </div>

        <form>

          <div className='edit-grid'>
            <div>
              <fieldset>
                <legend className='increase-font-2'>Basic Details!</legend>

                <div className="form-group change-to-grid">

                  <span className='increase-font'>First Name:</span>

                  <input placeholder="First Name" name="firstName" className="form-control width-control" value={this.state.firstName} onChange={this.onChange} />
                </div>


                <div className="form-group">
                  <span className='increase-font'>Last Name:</span>
                  <input placeholder="Last name" name="lastName" className="form-control width-control" value={this.state.lastName} onChange={this.onChange} />
                </div>

                <div className="form-group">

                  <span className='increase-font'>Date of Birth:</span>
                  <input type='date' name="dob" className="form-control width-control" value={this.state.dob} onChange={this.onChange} />
                </div>

                <div className="form-group">

                  <span className='increase-font'>PRN:</span>
                  <input type='number' placeholder='prn' name="prn" className="form-control width-control" min={1} maxLength={12} value={this.state.prn} onChange={this.onChange} />
                </div>


              </fieldset>
            </div>
            <div>
              <fieldset>
                <legend className='increase-font-2'>Academic Details!</legend>

                <div className="form-group">
                  <span className='increase-font'>Class 10th marks:</span>
                  <input type='number' placeholder='10th marks' name="mark10th" className="form-control width-control" min={33} max={100} value={this.state.mark10th} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Class 10th passing date:</span>
                  <input type='date' placeholder='year of class 10th' name="passingYear10th" className="form-control width-control" value={this.state.passingYear10th} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Class 12th marks:</span>
                  <input type='number' placeholder='12th marks' name="mark12th" className="form-control width-control" min={33} max={100} value={this.state.mark12th} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Class 12th passing date:</span>
                  <input type='date' placeholder='year of class 12th' name="passingYear12th" className="form-control width-control" value={this.state.passingYear12th} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Diploma marks(if any):</span>
                  <input type='number' placeholder='diploma marks' name="markDiploma" className="form-control width-control" min={33} max={100} value={this.state.markDiploma} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Diploma passing date:</span>
                  <input type='date' placeholder='year of passing diploma' name="passingYearDiploma" className="form-control width-control" value={this.state.passingYearDiploma} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Graduation marks:</span>
                  <input type='number' placeholder='graduation marks' name="markGrad" className="form-control width-control" min={33} max={100} value={this.state.markGrad} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Graduation date:</span>
                  <input type='date' placeholder='year of graduation' name="passingYearGrad" className="form-control width-control" value={this.state.passingYearGrad} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Post Graduation Marks:</span>
                  <input type='number' placeholder='post graduation marks' name="markPostGrad" className="form-control width-control" min={33} max={100} value={this.state.markPostGrad} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>Post Graduation date:</span>
                  <input type='date' placeholder='year of post-graduation' name="passingYearPostGrad" className="form-control width-control" value={this.state.passingYearPostGrad} onChange={this.onChange} />
                </div>

                <div className="form-group">
                  <span className='increase-font'>CCEE marks:</span>
                  <input type='number' placeholder='CCEE marks' name="markCCEE" className="form-control width-control" min={33} max={100} value={this.state.markCCEE} onChange={this.onChange} />
                </div>

              </fieldset>
            </div>
            <div>
              <div>
                <fieldset>
                  <legend className='increase-font-2'>Contact Details!</legend>

                  <div className="form-group">
                    <span className='increase-font'>Email:</span>
                    <input type='email' placeholder='email address' name="email" className="form-control width-control" value={this.state.email} onChange={this.onChange} />
                  </div>

                  <div className="form-group">
                    <span className='increase-font'>Mobile Number:</span>
                    <input type='number' placeholder='mobile number' name="mobNo" maxLength={1} className="form-control width-control" value={this.state.mobNo} onChange={this.onChange} />
                  </div>

                  <div className="form-group">
                    <span className='increase-font'>Address:</span>
                    <input type='text' placeholder='Address' name="address" className="form-control width-control" value={this.state.address} onChange={this.onChange} />
                  </div>

                  <div className="form-group">
                    <span className='increase-font'>Git profile link:</span>
                    <input type='text' placeholder='git-link' name="gitLink" className="form-control width-control" value={this.state.gitLink} onChange={this.onChange} />
                  </div>

                  <div className="form-group">
                    <span className='increase-font'>LinkedIn profile Link:</span>
                    <input type='text' placeholder='LinkedIn link' name="linkedIn" className="form-control width-control" value={this.state.linkedIn} onChange={this.onChange} />
                  </div>
                </fieldset>
              </div>

              <div>
                <div className='to-up'>
                  <fieldset>
                    <legend className='increase-font-2'>
                      Credential Details!
                    </legend>

                    <div className="form-group">
                      <span className='increase-font'>User Name:</span>
                      <input type='text' placeholder='User name' name="userName" className="form-control width-control" value={this.state.userName} onChange={this.onChange} />
                    </div>

                    <div className="form-group">
                      <span className='increase-font'>Password:</span>
                      <input type='password' placeholder='Password' name="password" className="form-control width-control" value={this.state.password} onChange={this.onChange} />
                    </div>

                    <div className="form-group">
                      <span className='increase-font'>Confirm Password:</span>
                      <input type='password' placeholder='password' name="confirmPassword" className="form-control width-control" value={this.state.confirmPassword} onChange={this.onChange} />
                    </div>


                  </fieldset>
                </div>
              </div>
            </div>


          </div>


          <button href='#sec' className="btn btn-success" onClick={this.saveStudent} >Register!</button>
        </form>



      </div>
    );
  }
}

export default RegisterUserComponent;