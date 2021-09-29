import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import React, { useEffect, useState } from "react";
import { useHistory } from 'react-router-dom';
import localStorageService from '../services/localStorageService';
import {Sidebars,SidebarSectrySup} from "./SidebarItems";

function Sidebar(props, {defaultActive,}) {
    const location = props.history.location;
    const lastActiveIndexString = localStorage.getItem("lastActiveIndex");
    const lastActiveIndex = Number(lastActiveIndexString);
    const [activeIndex, setActiveIndex] = useState(lastActiveIndex || defaultActive);

    function changeActiveIndex(newIndex) {
        localStorage.setItem("lastActiveIndex", newIndex)
        setActiveIndex(newIndex)
    }

    function getPath(path) {
        if (path.charAt(0) !== "/") {
            return  "/" + path;
        }
        return path;
    }

    
        const history = useHistory();
      
        function handleClick(link) {
          history.push(link);
        }
    

    // useEffect(()=> {
    //     console.log(localStorageService.getItem('role'));
    //     if(localStorageService.getItem('role') === 'ROLE_admin' ||  localStorageService.getItem('role') === 'ROLE_hr'){
            
    //         const activeItem = SidebarAdminHR.findIndex(item=> getPath(item.route) === getPath(location.pathname))
    //     changeActiveIndex(activeItem);
    //     }else{
    //         const activeItem = SidebarSectrySup.findIndex(item=> getPath(item.route) === getPath(location.pathname))
    //     changeActiveIndex(activeItem);
    //     }
        
    // }, [location])

    return (
        <>
                <div >
                    {
                       
                       Sidebars.map((item, index)=> {
                            return (
                                // <Link to={item.route}>
                                    
                                      <ListItem button onClick={()=>handleClick(item.route)}>
                                        <ListItemIcon >
                                          {item.icon}
                                        </ListItemIcon>
                                        <ListItemText primary={item.name} />
                                      </ListItem>
                                      
                                    
                                // </Link>
                            );
                        })
                    
                 }

                
                </div>
                
        
        </>
    );
}

export default Sidebar;

