import AppBar from '@material-ui/core/AppBar';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import Divider from '@material-ui/core/Divider';
import Drawer from '@material-ui/core/Drawer';
import IconButton from '@material-ui/core/IconButton';
import List from '@material-ui/core/List';
import { makeStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import MenuIcon from '@material-ui/icons/Menu';
import clsx from 'clsx';
import React from 'react';
import { Redirect, useHistory } from 'react-router-dom';
import localStorageService from '../services/localStorageService';
import Sidebar from "./Sidebar";
import SimpleMenu from './SimpleMenu';
import FolderIcon from '@material-ui/icons/Folder';
import HistoryIcon from '@material-ui/icons/History';
import SearchIcon from '@material-ui/icons/Search';
import PostAddIcon from '@material-ui/icons/PostAdd';


function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      
        Trainee Management System
      {' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 240,
  },
}));

export default function Dashboard(props) {

  const history= useHistory();
  const classes = useStyles();
  const [open, setOpen] = React.useState(true);
  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
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
  const historyPath = () =>{ 
    let path = `history`; 
    history.push(path);
  }

  const  userRole= (role)=>{
    switch(role) {
        case 'ROLE_admin':
          return 'Administator';
        case 'ROLE_hr':
            return 'HR';
        case 'ROLE_supervisor':
            return 'Supervisor';
        case 'ROLE_secretary':
            return 'Secretary';
        case 'ROLE_director':
          return 'Director';
        default:
          return '';
      }
}
  const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

  return (
    
    <div className={classes.root}>
       { localStorageService.getItem('authenticated') ? 

      <CssBaseline />
      :
      <Redirect to={{
        pathname: '/signin'
      }} />}
        { localStorageService.getItem('authenticated') ? 
      <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
        <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
          >
            <MenuIcon />
          </IconButton>
          <Typography component="h1" variant="h4" color="inherit" noWrap  className={classes.title}>
            Accueil 
            {/* Trainee Management System - {userRole(localStorageService.getItem('role'))} Portal */}
          </Typography>
          <IconButton>
            <FolderIcon/>
          </IconButton>

          <IconButton onClick={historyPath}>
            <HistoryIcon/>
          </IconButton>

          <IconButton>
            <SearchIcon/>
          </IconButton>

          <IconButton>
            <PostAddIcon/>
          </IconButton>
          

          <SimpleMenu/>
          {/* <Button color="inherit" onClick={logOutrouteChange}>Logout</Button>  */}

        </Toolbar>
      </AppBar>
       :
       <></>}
       { localStorageService.getItem('authenticated') ? 
      <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open}
      >
        <div className={classes.toolbarIcon}>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <Divider />
        <List>
        <Sidebar history={props.history}/>
        </List>
      </Drawer>
      :
      <></>}
      { localStorageService.getItem('authenticated') ? 

      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        <Container maxWidth="lg" className={classes.container}>
          {props.children}
          {/* <Box pt={4}>
            <Copyright />
          </Box> */}
        </Container>
      </main>
       :
       <></>}
    </div>
   
  );
}
