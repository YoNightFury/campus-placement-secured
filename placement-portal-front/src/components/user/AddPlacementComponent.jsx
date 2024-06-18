import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import './AddUserComponent.css'

let companyName = [];

  const round = [
    {
      label: "CODING",
      value: "CODING",
    },
    {
      label: "APTITUDE",
      value: "APTITUDE",
    },
    {
      label: "TECHNICAL",
      value: "TECHNICAL",
    },
    {
      label: "HR",
      value: "HR",
    }
  ];

  const isSelected = [
    {
      label: "YES",
      value: "YES",
    },
    {
      label: "NO",
      value: "NO",
    }
  ];

class AddPlacementComponent extends Component{

  constructor(props){
    super(props);
    if(this.props.location.state&&this.props.location.state.companyNames){
    this.state ={
      companyNames: this.props.location.state.companyNames,
     
      round:"HR",
      isSelected:"NO",
      companyName:props.location.state.companyNames[0],
        message: null
    }
    this.savePlacememtDetails = this.savePlacememtDetails.bind(this);
    companyName = this.state.companyNames.map((cname)=>{return {label : cname, value : cname}});
    console.log(companyName);
  }
}
    
    savePlacememtDetails=(e)=>{
      e.preventDefault();
      let placementDetails={ round:this.state.round, isSelected:this.state.isSelected, companyName:this.state.companyName }
      console.log("this is place"+placementDetails.round,placementDetails.isSelected,placementDetails.companyName);
      ApiService.addPlacementDetail(placementDetails).then(resp=>{
        console.log(resp.data);
        this.setState({message : 'Placement Detail added successfully.'});
        alert('Placement details added!');
        document.getElementById('123').click();
      }).catch(err=>{
        console.error("in err ",err.response.data);
                //err.response.data => DTO on the server side : ErrorResponse
                alert(err.response.data.message);           
      })
      
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    

          

    render() {
      if(!this.state){
        this.props.history.push('/');
        return <></>
      }
        return(
            <div className='signupScreen custom-signup'>
              <h1 style={{
            'color': 'white'
          }}>Add Placement Details!</h1>
                

                
            <form >
              <div className='body-batch-details'>
              <fieldset className='batch-details'>

            <div className="form-group">
              <label>Company</label>
              <select name='companyName' className='form-control' value={this.state.companyName} onChange={this.onChange}>
                {companyName.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label>Round</label>
              <select name='round' className='form-control' value={this.state.round} onChange={this.onChange}>
                {round.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label>Were you selected?</label>
              <select name='isSelected' className='form-control' value={this.state.isSelected} onChange={this.onChange}>
                {isSelected.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>
            </fieldset>
            </div> 
            <button className="btn btn-success" onClick={this.savePlacememtDetails} >Add details!</button>           
            </form>
          

                

                
          


            
    </div>
        );
    }
}

export default AddPlacementComponent;