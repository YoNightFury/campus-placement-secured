import React, { Component} from 'react'
import ApiService from "../../service/ApiService";
import './AddUserComponent.css'

let companyName = [
    {
      label: "TCS",
      value: "TCS",
    },
    {
      label: "INFOSYS",
      value: "INFOSYS",
    }
  ];

class AddQuestionsComponent extends Component {

  constructor(props){
    super(props);
    if(this.props.location.state && props.location.state.placements){
    
    this.state ={
        question: "",
        companyName : props.location.state.placements[0].companyName,
        placements : props.location.state.placements,
        message: null
    }
    
    companyName = [...(new Set(this.state.placements.map(p => p.companyName)))].map(c => {return {label : c,value : c}});
  
    this.saveQuestions = this.saveQuestions.bind(this);
  }
}

saveQuestions=(e)=>{
  e.preventDefault();
  let que={question:this.state.question, companyName : this.state.companyName};
  console.log(que.companyName);
  if(this.state.question){
  ApiService.addQuestion(que).then(resp=>{
    window.location.reload();
    console.log(resp.data);
    alert('Question added!');
    this.setState({message : 'Question added successfully.'});
  }).catch(err=>{
    console.error("in err ",err.response.data);
            //err.response.data => DTO on the server side : ErrorResponse
            alert(err.response.data.message);           
      });}
      else{
        alert('Empty field! Please add question.')
      }
  }
        


onChange = (e) =>{
    this.setState({ [e.target.name]: e.target.value });
  console.log(this.state[e.target.name]);
  }
         

    render() {

      if(!this.state){
        this.props.history.push('/');
        return <></>;
      }
         return (
        <div className='signupScreen'>
            <form>
       <h1>Add your Questions here</h1>
        

        <div className="form-group">
        <label>Company:</label>
              
              <select name='companyName' className='form-control' value={this.state.companyName} onChange={this.onChange}>
                {companyName.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
              </select>
            </div>
            <div className="form-group">
            <label>Add Question:</label>
                    
                    <input type="text" placeholder="question" name="question" className="form-control" value={this.state.question} onChange={this.onChange}/>
                </div>
                <button className="btn btn-success"  onClick={this.saveQuestions}> Add </button>
        </form>  
        
    </div> );
    }
}


export default AddQuestionsComponent ;