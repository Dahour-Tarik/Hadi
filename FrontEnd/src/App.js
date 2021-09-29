import {
  BrowserRouter as Router, Redirect, Route,
  Switch
} from 'react-router-dom';
import './App.css';
import Home from "./pages/Home";
import SignIn from './pages/SignIn';
import localStorageService from './services/localStorageService';
import Dashboard from './components/Dashboard';
import KeoBox from './pages/KeoBox';
import KeoFac from './pages/KeoFac';
import Profile from './pages/Profile';
import History from './pages/History';


function App() {
  return (
    <div className="App">
      
      <Router>
      {/* { localStorageService.getItem('authenticated') ?  */}
        <Route render={(props)=>(
              
                <Dashboard {...props}>
                    <Switch>
                        {/* <Route path="/" exact component={Dashboard}/>
                        <Route path="/home" exact component={Dashboard}/> */}
                         <Route path="/Home" exact component={Home}/>
                        <Route path='/keobox' exact>
                          {localStorageService.getItem('authenticated') ? <KeoBox/>  : <Redirect to={{
                            pathname: '/signin'
                          }} />}</Route>

                            {/* <Route path="/Home" exact component={Home}/> */}
                        <Route path='/keofac' exact>
                          {localStorageService.getItem('authenticated') ? <KeoFac/>  : <Redirect to={{
                            pathname: '/signin'
                          }} />}</Route>
                          <Route path='/profile' exact>
                          {localStorageService.getItem('authenticated') ? <Profile/>  : <Redirect to={{
                            pathname: '/signin'
                          }} />}</Route>
                          <Route path='/history' exact>
                          {localStorageService.getItem('authenticated') ? <History/>  : <Redirect to={{
                            pathname: '/signin'
                          }} />}</Route>
                       
                        {/* <Route path="/archive" exact component={ArchiveList}/> */}
                        {/* <Route path="/page-3" component={Page3}/> */}
                        {/* <Route component={NotFound}/> */}
                    </Switch>
                </Dashboard>
            )}/>
            <Switch>
            <Route path='/' exact>
          {localStorageService.getItem('authenticated') ? <Home/>  : <Redirect to={{
            pathname: '/signin'
          }} />}
        </Route>
            
            <Route path="/signin" exact component={SignIn}/>
          {/* <Route path="/signin" ><SignIn/></Route>
          <Route path="/home" ><Dashboard/></Route> */}
        </Switch>
        {/* } */}
      </Router>
      
      

    </div>
  );
}

export default App;

