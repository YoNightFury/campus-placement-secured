import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import './AddUserComponent.css'

class ChangePhotoComponent extends Component {

    constructor(props){
        super(props);
        
        if(this.props.location.state &&  props.location.state.student){this.state ={
            
                student: props.location.state.student,
                
            
            photofile: '',
            
            message: null
        }
        this.uploadPhoto = this.uploadPhoto.bind(this);
        this.onChange=this.onChange.bind(this);
        }
    }

    onChange(e){
        this.state.photofile=e.target.files[0];
    }


    uploadPhoto= (e) =>{
        e.preventDefault();
        const formData = new FormData();
        if(this.state.photofile){
        formData.append("studentPhoto", this.state.photofile, this.state.photofile.name);
        ApiService.uploadPhoto(formData).then(resp=>{
            console.log(resp);
            console.log(resp.data);
            alert('Photo Changed!!');
            this.props.history.push({
                pathname: '/profile',
                state: { student: this.state.student }
                
            })
      }).catch(err=>{
              console.log(err);
              alert(err);
              return 'failed to upload';
      });}
      else{
          alert('Please select a photo!');
      }
          
      
  }

    render() {
        if(!this.state){
            this.props.history.push('/');
            return <></>;
          }
        return (

            <div className='signupScreen'>
            <form>
                <h1>Upload Photo!!</h1>
                <div className='form-group'>
                    
                    <input type='file' className='form-control custom-width' onChange={this.onChange} />
                    <input type='submit' className='btn btn-success custom-button' value='Upload' onClick={this.uploadPhoto} />
                    
                    </div>
                    </form>
                </div>
           


            
        );
    }
}

export default ChangePhotoComponent;