import React from 'react';
import Navbar from './components/Navbar';

import './App.css';
import Home from './components/pages/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import AboutPage from './components/pages/AboutPage';
import ContactUs from './components/pages/ContactUs';

import AuthUserComponent from './components/user/AuthUserComponent'
import RegisterUserComponent from './components/user/RegisterUserComponent';
import ListUserComponent from './components/user/ListUserComponent';
import ShowProfileComponent from './components/user/ShowProfileComponent'
import UploadResumeComponent from './components/user/UploadResumeComponent';
import AddPlacementDetails from './components/user/AddPlacementComponent';
import AddQuestionsComponent from './components/user/AddQuestionsComponent';
import AddProjectDetailComponent from './components/user/AddProjectDetailComponent';
import ChangePhotoComponent from './components/user/ChangePhotoComponent';
import EditProfileComponent from './components/user/EditProfileComponent';
import ShowPlacementComponent from './components/user/ShowPlacementComponent'
import ShowQuestionsComponent from './components/user/ShowQuestionsComponent';
import ShowProjectDetailComponent from './components/user/ShowProjectDetailComponent';

function App() {
  return (
    <Router>
      <Navbar />
      
      
      <Switch>
        <Route path='/' exact component={Home} />
        <Route path='/student' component={ListUserComponent} />
        <Route path='/about' component={AboutPage} />
        <Route path='/contact-us' component={ContactUs} />
        <Route path='/sign-in' component={AuthUserComponent} />      
        <Route path='/register-user' component={RegisterUserComponent}/>
        <Route path='/profile' component={ShowProfileComponent}/>
        <Route path='/upload-resume' component={UploadResumeComponent}/>
        <Route path='/add-placement-details' component={AddPlacementDetails}/>
        <Route path='/add-questions' component={AddQuestionsComponent}/>
        <Route path='/add-project' component={AddProjectDetailComponent}/>
        <Route path='/change-photo' component={ChangePhotoComponent}/>
        <Route path='/edit-profile' component={EditProfileComponent}/>
        <Route path='/placement-details' component={ShowPlacementComponent}/>
        <Route path='/show-question' component={ShowQuestionsComponent}/>
        <Route path='/project-details' component={ShowProjectDetailComponent}/>
      </Switch>
    </Router>
    
  );
}

export default App;
