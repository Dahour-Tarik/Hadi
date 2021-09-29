import Avatar from '@material-ui/core/Avatar';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Checkbox from '@material-ui/core/Checkbox';
import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Alert from '@material-ui/lab/Alert';
import { Formik } from 'formik';
import React from 'react';
import { useHistory } from 'react-router-dom';
import * as Yup from 'yup';
import { getAuthonticated } from '../services/ApiService';
import localStorageService from '../services/localStorageService';

// function Copyright() {
//   return (
//     <Typography variant="body2" color="textSecondary" align="center">
//       {'Copyright © '}
//       <Link color="inherit" href="https://material-ui.com/">
//         Your Website
//       </Link>{' '}
//       {new Date().getFullYear()}
//       {'.'}
//     </Typography>
//   );
// }



const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


export default function SignIn(props) {
  const history= useHistory();
  const classes = useStyles();

  const[alert, setAlert]=React.useState(false);
  const[loading, setLoading]=React.useState(false);

  const[alertMessage, setAlertMessage]=React.useState('');
  const[severity, setSeverity]=React.useState('');

   const userPagerouteChange =()=>{ 
    
      if(loading){
        let path = `home`; 
        history.push(path);
      }
      
    
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        {  alert && <Alert severity={severity}>{alertMessage}</Alert>}
        <Formik
            initialValues={{
              username: '',
              password: ''
            }}
            validationSchema={Yup.object().shape({
              username: Yup.string().max(255).required('User Name is required'),
              password: Yup.string().max(255).required('Password is required')
            })}
            asnyc onSubmit={ async values => { // url/me end point return the user details, once receive the user details stored it in localstorage, redirect the respetive page according to role 
              // var promise = new Promise
               await getAuthonticated(values.username,values.password).then(data=>{
                
                  localStorageService.setItem("user", data.data);
                  localStorageService.setItem("role", data.data.role);
                  localStorageService.setItem("username", values.username);
                  localStorageService.setItem("password", values.password);
                  localStorageService.setItem('authenticated',true);
                  setLoading(true);
               
                  // <Redirect to='/home'/>
                  
                  history.push('/home')
                  
              }).catch(function (error){
                setAlert(true)
                setAlertMessage("Incorrect user name or password ")
                setSeverity('error')
              })
              // userPagerouteChange();
            }}
          >
            {({
              errors,
              handleBlur,
              handleChange,
              handleSubmit,
              isSubmitting,
              touched,
              values
            }) => (

        <form className={classes.form} onSubmit={handleSubmit} >
          <TextField
            variant="outlined"
            margin="normal"
            error={Boolean(touched.username && errors.username)}
            fullWidth
            helperText={touched.username && errors.username}
            id="username"
            label="User Name"
            name="username"
            onChange={handleChange}
            type="text"
            value={values.username}
            autoComplete="username"
            autoFocus
            
          />
          <TextField
            variant="outlined"
            margin="normal"
            fullWidth
            error={Boolean(touched.password && errors.password)}
            helperText={touched.password && errors.password}
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            onChange={handleChange}
            type="password"
            value={values.password}
          />
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            disabled={isSubmitting}
            className={classes.submit}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="/signup" variant="body2">
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </form>)}
        </Formik>
      </div>
      <Box mt={8}>
        
      </Box>
    </Container>
  );
}

function timeout(delay) {
  return new Promise( res => setTimeout(res, delay) );
}
