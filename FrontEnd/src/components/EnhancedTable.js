import Checkbox from '@material-ui/core/Checkbox';
import IconButton from '@material-ui/core/IconButton';
import Paper from '@material-ui/core/Paper';
import { lighten, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableRow from '@material-ui/core/TableRow';
import Toolbar from '@material-ui/core/Toolbar';
import Tooltip from '@material-ui/core/Tooltip';
import Typography from '@material-ui/core/Typography';
import DeleteIcon from '@material-ui/icons/Delete';
import GetAppOutlinedIcon from '@material-ui/icons/GetAppOutlined';
import SwapHorizOutlinedIcon from '@material-ui/icons/SwapHorizOutlined';
import EditOutlinedIcon from '@material-ui/icons/EditOutlined';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import FolderIcon from '@material-ui/icons/Folder';
import DescriptionOutlinedIcon from '@material-ui/icons/DescriptionOutlined';
import React from 'react';
import { deepOrange, deepPurple, purple , blue} from '@material-ui/core/colors';
import { handleDocDownload, updateDocsName } from '../services/ApiService';
import { Grid, Button,TextField  } from '@material-ui/core'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import SaveIcon from '@material-ui/icons/Save';



const useToolbarStyles = makeStyles((theme) => ({
    root: {
        paddingLeft: theme.spacing(2),
        paddingRight: theme.spacing(1),
    },
    highlight:
        theme.palette.type === 'light'
            ? {
                color: blue[500],
                backgroundColor: lighten(blue[500], 0.85),
            }
            : {
                color: theme.palette.text.primary,
                backgroundColor: blue[500],
            },
    title: {
        flex: '1 1 100%',
    },
}));

const EnhancedTableToolbar = (props) => {
    const classes = useToolbarStyles();
    const { numSelected } = props;

    return (
        <Toolbar
            className={clsx(classes.root, {
                [classes.highlight]: numSelected > 0,
            })}
        >
            {numSelected > 0 ? (
                <Typography className={classes.title} color="inherit" variant="subtitle1" component="div">
                    {numSelected} selected
                </Typography>
            ) : (
                <Typography className={classes.title} variant="h6" id="tableTitle" component="div">
                    Repos
                </Typography>
            )}

            {numSelected > 0 ? (
                <Tooltip title="Delete">
                    <IconButton aria-label="delete">
                        <DeleteIcon />
                    </IconButton>
                </Tooltip>
            ) : (
                <Tooltip title="Filter list">
                    <IconButton aria-label="filter list">

                    </IconButton>
                </Tooltip>
            )}
        </Toolbar>
    );
};

EnhancedTableToolbar.propTypes = {
    numSelected: PropTypes.number.isRequired,
};

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    paper: {
        width: '100%',
        marginBottom: theme.spacing(2),
    },
    table: {

    },
    visuallyHidden: {
        border: 0,
        clip: 'rect(0 0 0 0)',
        height: 1,
        margin: -1,
        overflow: 'hidden',
        padding: 0,
        position: 'absolute',
        top: 20,
        width: 1,
    },
}));

export default function EnhancedTable(props) {
    const classes = useStyles();
    const [selected, setSelected] = React.useState([]);
    const [dense, setDense] = React.useState(false);
    const [open, setOpen] = React.useState(false);
    const [selectedDoc, setSelectedDoc] = React.useState({
        idDoc:'',
        docName:'',
        docType:'',
        docSize:'',
        docCreationDate:'',
        data:'',
        historyList:'',
        email_history:''
    });

     const handleClick = (event, name, enfants,data) => {
        // const selectedIndex = selected.indexOf(name);
        // let newSelected = [];
        // if (selectedIndex === -1) {
        //     newSelected = newSelected.concat(selected, name);
        // } else if (selectedIndex === 0) {
        //     newSelected = newSelected.concat(selected.slice(1));
        // } else if (selectedIndex === selected.length - 1) {
        //     newSelected = newSelected.concat(selected.slice(0, -1));
        // } else if (selectedIndex > 0) {
        //     newSelected = newSelected.concat(
        //         selected.slice(0, selectedIndex),
        //         selected.slice(selectedIndex + 1),
        //     );
        // }
        // setSelected(newSelected);

        setSelected(name);
        props.setViewFunction(enfants);
        props.parentIdFunction(name)
        props.setDocsFunction(data.docs)
    };


    function updateDocNameHandler(){
        updateDocsName(selectedDoc).then(data=>{
            handleClose ()
        }).catch()
    }

    function handleOpen (e,id,data){
       setSelectedDoc(data)
        setOpen(true);
    };

    function handleClose (){
        setOpen(false);
        
    };

    function changedocName(e){
        setSelectedDoc({ ...selectedDoc, [e.target.name]: e.target.value });
    }
    // const isSelected = (name) => selected.indexOf(name) !== -1;
    const isSelected = (name) => selected === name;


    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                {/* <EnhancedTableToolbar numSelected={selected.length} /> */}
                <TableContainer>
                    <Table
                        className={classes.table}
                        aria-labelledby="tableTitle"
                        size={dense ? 'small' : 'medium'}
                        aria-label="enhanced table"
                    >

                        <TableBody>
                            {
                                props.rows && props.rows.map((row, index) => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${index}`;

                                    return (
                                        <TableRow

                                            aria-checked={isItemSelected}
                                            tabIndex={-1}
                                            key={row.id}
                                            selected={isItemSelected}
                                        >
                                            <TableCell padding="checkbox" >
                                                <Checkbox
                                                    checked={isItemSelected}
                                                    inputProps={{ 'aria-labelledby': labelId }}
                                                />
                                            </TableCell>
                                            <TableCell
                                                hover
                                                onClick={(event) => handleClick(event, row.id, row.enfants,row)}
                                                role="checkbox"
                                                component="th" id={labelId} scope="row" padding="none" align="left">
                                                <IconButton color="Default" >
                                                    <FolderIcon /> {row.name}
                                                </IconButton>
                                            </TableCell>
                                            <TableCell align="right">
                                                <IconButton color="primary">
                                                    <GetAppOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="primary" >
                                                    <EditOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="primary" >
                                                    <SwapHorizOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="secondary" >
                                                    <DeleteIcon style={{ fontSize: 17 }} />
                                                </IconButton>
                                            </TableCell>
                                        </TableRow>
                                    );
                                })}
                                {props.docs && props.docs.map((doc, index) => {
                                    return (
                                        <TableRow

                                            tabIndex={-1}
                                            key={doc.idDoc}
                                        >
                                            <TableCell padding="checkbox" >
                                                
                                            </TableCell>
                                            <TableCell
                                                
                                                // onClick={(event) => handleClick(event, doc.idDoc)}
                                                
                                                component="th"  scope="row" padding="none" align="left">
                                               
                                                    <DescriptionOutlinedIcon /> {doc.docName}
                                                
                                            </TableCell>
                                            <TableCell align="right">
                                                <IconButton color="primary" onClick={() => {handleDocDownload(doc.idDoc,doc.docName)}}>
                                                    <GetAppOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="primary" onClick={(e)=>handleOpen(e,0,doc)}>
                                                    <EditOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="primary" >
                                                    <SwapHorizOutlinedIcon style={{ fontSize: 17 }} />
                                                </IconButton>

                                                <IconButton color="secondary" >
                                                    <DeleteIcon style={{ fontSize: 17 }} />
                                                </IconButton>
                                            </TableCell>
                                        </TableRow>
                                    );
                                })}

                        </TableBody>
                    </Table>
                </TableContainer>

            </Paper>

            <Dialog

maxWidth="sm"
fullWidth="md"
open={open}
onClose={handleClose}

>
<div style={{ textAlign: 'center' }}>
    <DialogActions>
        <Button onClick={e => handleClose()} color="primary">
            Close
        </Button>
    </DialogActions>
    {/* <DialogTitle id="max-width-dialog-title">Create New Repo</DialogTitle> */}

    <DialogContent>
        <DialogContentText>
            {/* onSubmit={createNewFolder} */}
            {/* <form noValidate onSubmit={createNewFolder}> */}
                <Grid container >
                    <Grid container item xs={12} lg={6}  >
                        
                        <TextField id="outlined-basic" name="docName" value={selectedDoc.docName} onChange={changedocName} label="Name" variant="outlined" />
                      
                    </Grid>
                    <Grid container item xs={12} lg={6}  >
                        <Button
                            type="submit"
                            onClick={updateDocNameHandler}
                            variant="contained"
                            color="primary"
                            endIcon={<SaveIcon />}
                        >
                            Add
                        </Button>
                    </Grid>
                </Grid>
            {/* </form> */}
        </DialogContentText>

    </DialogContent>
</div>
</Dialog>


        </div>
    );
}
