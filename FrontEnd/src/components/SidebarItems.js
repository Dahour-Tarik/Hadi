import PeopleIcon from '@material-ui/icons/People';
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import ArchiveIcon from '@material-ui/icons/Archive';
import HomeIcon from '@material-ui/icons/Home';
import DescriptionIcon from '@material-ui/icons/Description';
import AllInboxIcon from '@material-ui/icons/AllInbox';
import { deepOrange, deepPurple, blue } from '@material-ui/core/colors';

export const Sidebars = [
    
    {
        name: "Home",
        route: '/home',
        icon:<HomeIcon style={{color: blue[500]}}/>
    },
    {
        name: "KeoBox",
        route: '/keobox',
        icon:<AllInboxIcon style={{color: deepOrange[500]}}/>
    },
    {
        name: "KeoFac",
        route: '/keofac',
        icon:<DescriptionIcon style={{color: deepPurple[500]}}/>
    }
];



