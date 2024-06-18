import React from "react";
import ApiService from "../../service/ApiService";
import './ListUserComponent.css'

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
    {
      label: "ALL",
      value: "ALL",
    }
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
      {
        label: "ALL",
        value: "ALL",
      }
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
    {
      label: "ALL",
      value: "ALL",
    }
  ];

  class ListUserComponent extends React.Component{

  
    constructor(props) {
        super(props)
        this.state = {
            students: [],
            message: null,

            year: "ALL",
            batch: "ALL",
            courseName: "ALL",
            filterStr:""
        }
    }

    componentDidMount() {
        this.reloadUserList();
    }


   



    reloadUserList() {
        ApiService.fetchStudent()
            .then((resp) => {
                this.setState({students: resp.data})
                console.log(this.state.students);
            });
           
    }
// showProfile(id){
 

//  console.log(id);
//     ApiService.fetchStudentById(id).then(resp=>{
//         console.log(resp);
//         console.log(resp.data);
//         ApiService.fetchPhoto(id).then(resp1=>{
//             console.log(resp1);
//             console.log(resp1.data);
//             resp.data.photo=resp1.data;
//         }).catch(err=>{
//           console.log('photo not found');
//             resp.data.photo= null;
//         })
//         this.props.history.push({
//             pathname: '/profile',
           
//             state: { student: resp.data }
            
//           })
//     }).catch(err=>{
//         alert('profile not available');
//         console.log(err);
//         this.props.history.push('/student')
//     })

// }

showProfile(id) {
  // fetch the student using id 
  ApiService.fetchStudentById(id).then((resp)=>{
    
    console.log("found student with id ",resp.data.id);
    // fetch the image of the student 
    this.props.history.push({
      pathname:"/profile",
      state : {student : resp.data}
    });
   

  }).catch((err)=>{
    console.log("std not found err ", err);
  });

}


    render() {



      let filteredStudents = this.state.students
      .filter(s=>this.state.batch==s.course.batch || this.state.batch=='ALL')
      .filter(s=>this.state.courseName==s.course.courseName || this.state.courseName=='ALL')
      .filter(s=>this.state.year==s.course.year || this.state.year=='ALL')
      .filter(
        (s) =>
          s.firstName.toUpperCase().includes(this.state.filterStr.toUpperCase()) ||
          s.lastName.toUpperCase().includes(this.state.filterStr.toUpperCase())
      )
      .map(
        student =>
                    <tr key={student.id}>
                        <td>{student.firstName}</td>
                        <td>{student.lastName}</td>
                        <td>
                            {student.markCCEE}
                        </td>
                        <td>
                            {student.course.courseName+' '+student.course.batch+' '+(student.course.year+'').substring(2)}
                        </td>
                        <td><button className='btn' name={student.id} id={student.id} onClick={e => this.showProfile(e.target.id)}>View Profile</button></td>
                        
                    </tr>
            );



            
            /** reconfigure the style of input text box which filters the names */
            return (
              <div>
                <div className='text-center'>
                Search: <input type='text' onChange={e=>this.setState({filterStr:e.target.value})} />
                <fieldset className='batch-details'>
                  
          <legend>Select Course </legend>
          <div className='flex-orient'>
            <div className="select-container flex-item">
            
              
              <span>Year</span>
              <select name='year' value={this.state.year}
                onChange={e=>this.setState({year:e.target.value})}>
                {year.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>
              
            <div className="select-container flex-item">
            <span>Batch</span>
              <select name='batch' value={this.state.batch}
              onChange={e=>this.setState({batch:e.target.value})}>
                {batch.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>

            <div className="select-container flex-item">
            <span>Course</span>
              <select name='courseName' value={this.state.courseName} 
              onChange={e=>this.setState({courseName:e.target.value})}>
                {course.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>
            </div>
            
            </fieldset>


                </div>

                <h2 className="text-center">Student Details</h2>
                
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th className="hidden">Id</th>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>CCEE Marks</th>
                            <th>Course </th>
                            <th></th>
                            
                            
                        </tr>
                    </thead>
                    <tbody>
                        {
                          filteredStudents  
                        }
                    </tbody>
                </table>

            </div>
        );
    }

}

export default ListUserComponent