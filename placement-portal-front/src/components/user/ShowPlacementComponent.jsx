import React from "react";
import ApiService from "../../service/ApiService";


  class ShowPlacementComponent extends React.Component{

  
    constructor(props) {
      super(props)
      if(this.props.location.state && props.location.state.student){
      this.state = {
          placement: [],
          message: null,
          student:props.location.state.student,

          
      }

      this.showAllQuestions = this.showAllQuestions.bind(this);
    }
  }

    componentDidMount() {
      if(this.state){
        this.reloadUserList();
      }
    }


   



    reloadUserList() {
      ApiService.fetchPlacement(this.state.student.id)
          .then((resp) => {
              this.setState({placement: resp.data},()=>console.log(this.state.placement));
          }).catch((err)=>{
            alert(err);
            console.log(err);
          })
         
  }


  showAllQuestions(id) {
    // fetch the student using id 
    ApiService.fetchQuestions(id).then((resp)=>{
      
      console.log("found questions ",resp.data);
      // fetch the questions 
      this.props.history.push({
        pathname:"/show-question",
        state : {questions : resp.data}
      });
     
  
    }).catch((err)=>{
      console.log("std not found err ", err);
    });
  
  }

    render() {

      if (!(this.state && this.state.student)) {
        this.props.history.push("/");
        return <></>;
      }



      let companyDetails = this.state.placement.map(
        placement =>
                    <tr key={placement.cid}>
                        <td>
                            {placement.companyName}
                        </td>
                        <td>{placement.round}</td>
                        <td>
                            {placement.isSelected}
                        </td>
                        
                        
                        <td><button className='btn' name={placement.cid} id={placement.cid} onClick={e => this.showAllQuestions(e.target.id)}>Show Questions</button></td>
                        
                    </tr>
            );



            
        
            return (
              <div>
                <h2 className="text-center">Placement Details</h2>
                
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th className="hidden">CId</th>
                            <th>Company Name</th>
                            <th>Round</th>
                            <th>Selected</th>
                            
                            <th></th>
                            
                            
                        </tr>
                    </thead>
                    <tbody>
                        {
                          companyDetails  
                        }
                    </tbody>
                </table>

            </div>
        );
    }

}

export default ShowPlacementComponent