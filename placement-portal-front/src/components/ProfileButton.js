import React from 'react';
import './Button.css';
import { Link, useHistory } from 'react-router-dom';
import ApiService from '../service/ApiService';

export function ProfileButton(props) {
  let history = useHistory();
  let handle = ()=>{
    let id = sessionStorage.getItem("studentid");
    if(!id)
      history.push('/');
    ApiService.fetchStudentById(id).then((resp)=>{
      history.push({pathname : '/profile', state : {student :resp.data}});

    }).catch((err)=>{
      console.log("err"+err);
      history.push('/');
    });
  }
console.log('in profile-button');
  return (
      <button className='btn' id='123' onClick={handle} >Profile</button>
  );
}