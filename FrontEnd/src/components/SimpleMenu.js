import React from 'react';
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import localStorageService from '../services/localStorageService';
import { Redirect, useHistory } from 'react-router-dom';


export default function SimpleMenu() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const history= useHistory();


  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  const logOutrouteChange = () =>{ 
    localStorageService.removeIteam('user');
    localStorageService.removeIteam("username");
    localStorageService.removeIteam("password");
    localStorageService.removeIteam("role");
    localStorageService.removeIteam('authenticated');
    let path = `signin`; 
    history.push(path);
  }

  const profileOutrouteChange = () =>{ 
    let path = `profile`; 
    history.push(path);
  }

  return (
    <div>
      <Button aria-controls="simple-menu"  aria-haspopup="true" onClick={handleClick}>
        <AccountCircleIcon></AccountCircleIcon>
      </Button>
      <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={profileOutrouteChange}>Profile</MenuItem>
        <MenuItem onClick={logOutrouteChange}>Logout</MenuItem>
      </Menu>
    </div>
  );
}
