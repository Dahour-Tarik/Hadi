import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Avatar from '@material-ui/core/Avatar';
import { deepOrange, deepPurple } from '@material-ui/core/colors';
import Grid from '@material-ui/core/Grid';
import Container from '@material-ui/core/Container';
import ButtonBase from '@material-ui/core/ButtonBase';
import { useHistory } from 'react-router-dom';
import AllInboxIcon from '@material-ui/icons/AllInbox';
import DescriptionIcon from '@material-ui/icons/Description';


export default function Home() {
    const classes = useStyles();
    const history= useHistory();
    function handleClickKeoBox() {
        let path = `keobox`; 
        history.push(path);
        // console.log('in cardClick');
    } 
    function handleClickKeoFac() {
        let path = `keofac`; 
        history.push(path);
        // console.log('in cardClick');
    } 
    
    return (
        <Container>
        <div style={{marginTop:'20px', }} >
            <Grid container spacing={1}>
                <Grid container item xs={12} lg={6} spacing={3} >
                    <div className={classes.parent} onClick={handleClickKeoBox} style={{marginTop:'50px', }}>
                        <SimpleCard title="KeoBox" content="Type Your Content here " />
                        <Avatar style={{ backgroundColor: deepOrange[500] }} className={classes.child}>
                        <AllInboxIcon style={{ fontSize: 50 }}/>
                        </Avatar>
                    </div>
                </Grid>
                <Grid container item xs={12} lg={6} spacing={3}>
                    <div className={classes.parent} onClick={handleClickKeoFac} style={{marginTop:'50px', }}>
                        <SimpleCard title="KeoFac" content="Type Your Content here "/>
                        <Avatar style={{ backgroundColor: deepOrange[900] }} className={classes.child}>
                        <DescriptionIcon style={{ fontSize: 50 }}/>
                        </Avatar>
                    </div>
                </Grid>
            </Grid>
        </div>
        </Container>
    )
}

const useStyles = makeStyles({
    root: {
        width:"100%",
        minWidth:"100px",
        transition: "transform 0.15s ease-in-out",
    "&:hover": { transform: "scale3d(1.05, 1.05, 1)" },
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
    child: {
        position: 'absolute',
        top: '-30px',
        left:'42%',
        width:'80px', 
        height:'80px'
    },

    parent: {
        position: 'relative',
        width:"80%",
    },
    cardAction: {
        display: 'block',
        textAlign: 'initial'
      }

});

function SimpleCard(props) {
    const classes = useStyles();

    return (
        <Card className={classes.root}   >
            <CardContent>

                <Typography className={classes.title} color="textSecondary" gutterBottom>
                    
                </Typography>
                <Typography style={{marginTop:'40px'}}variant="h5" component="h2">
                    {props.title}
                </Typography>
                <Typography className={classes.pos} color="textSecondary">
                    
                </Typography>
                <Typography variant="body2" component="p">
                   {props.content}
                    <br />
                   
                </Typography>
            </CardContent>
        </Card>
    );
}