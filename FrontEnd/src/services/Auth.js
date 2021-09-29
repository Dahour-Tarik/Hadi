import localStorageService from './localStorageService';

class Auth{
    constructor(){
        this.authenticated =false
    }
 
    login(cb){
        this.authenticated=true
        localStorageService.setItem('authenticated',true);
        cb();
    }

    logout(cb){
        this.authenticated=false
        localStorageService.setItem('authenticated',false);
        cb();
    }

    isAuthonticated(){
        return this.authenticated;
    }

}

export default new Auth()