import React, { useState } from 'react';
import { SigninButton } from './SigninButton';
import { Link } from 'react-router-dom';
import './Navbar.css';
import { ProfileButton } from './ProfileButton';




function Navbar() {
  const [click, setClick] = useState(false);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);
  const studentId= sessionStorage.getItem('studentid');
 

 

  

  return (
    <>
      <nav className='navbar'>
        <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
        <span className='logo-text'>Placement Portal</span>
          <i className='fab fa-firstdraft' />
        </Link>
        <div className='menu-icon' onClick={handleClick}>
          <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
        </div>
        <ul className={click ? 'nav-menu active' : 'nav-menu'}>
          <li className='nav-item'>
            <Link to='/' className='nav-links' onClick={closeMobileMenu}>
              Home
            </Link>
          </li>
          <li
            className='nav-item'
           
          >
            <Link
              to='/student'
              className='nav-links'
              onClick={closeMobileMenu}
            >
              Students
            </Link>
            {/*{dropdown && <Dropdown />}*/}
          </li>
          <li className='nav-item'>
            <Link
              to='/about'
              className='nav-links'
              onClick={closeMobileMenu}
            >
              About
            </Link>
          </li>
          <li className='nav-item'>
            <Link
              to='/contact-us'
              className='nav-links'
              onClick={closeMobileMenu}
            >
              Contact Us
            </Link>
          </li>
          <li className='nav-item nav-links' onClick={closeMobileMenu}>
          {!studentId?<SigninButton />:<ProfileButton/>}
          </li>
        </ul>
      
      </nav>
    </>
  );
}

export default Navbar;
