import { Container, Grid, Box, Paper, Button, Input } from '@material-ui/core'
import React from 'react'
import { deepOrange, deepPurple, purple , blue} from '@material-ui/core/colors';
import CreateNewFolderOutlinedIcon from '@material-ui/icons/CreateNewFolderOutlined';
import NoteAddOutlinedIcon from '@material-ui/icons/NoteAddOutlined';
import { createFolder, getFolders, uploadDocs } from '../services/ApiService';
import EnhancedTable from '../components/EnhancedTable';
import ArrowBackIosOutlinedIcon from '@material-ui/icons/ArrowBackIosOutlined';
import localStorageService from '../services/localStorageService';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import Icon from '@material-ui/core/Icon';
import SaveIcon from '@material-ui/icons/Save';

export default function KeoBox() {
    const [repoList, setRepoList] = React.useState([]);
    const [docs1, setDocs1] = React.useState([]);
    const [docs2, setDocs2] = React.useState([]);
    const [newDocs, setNewDocs] = React.useState('');
    const [selectedRepo, setSelectedRepo] = React.useState([]);
    const [viewdRepo, setViewRepo] = React.useState([]);
    const [backRepo, setBackRepo] = React.useState([]);
    const [backDocs, setBackDocs] = React.useState([]);
    const [parentId, setParentId] = React.useState(null);
    const [newRepo, setNewRepo] = React.useState({ 
                                                name: '', 
                                                path: "local/",
                                                repDateCreation:new Date().getTime(), 
                                                hasChild: false, 
                                                parents:{id:null}, 
                                                user: { 
                                                    id: localStorageService.getItem("user").id 
                                                } 
                                            })
    const [open, setOpen] = React.useState(false);
    const [openId, setOpenId] = React.useState(0);                                       
    const [prevoousParenetId, setPreviousParentId] = React.useState(0);
    const [openDoc, setOpenDoc] = React.useState(false);                                       

    React.useEffect(() => {
        getFolders().then(data => {
            setRepoList(data.data);
            setSelectedRepo(data.data);
            setParentId(0);
            let newDate = new Date()
            console.log(newDate.getTime())
        })
    }, []);

    function expandFolder(data) {
        // console.log(data)
        setViewRepo(data);
    }

    

    function expandChildFolder(data) {
        // console.log(data)
        //  setPreviousParentId(parentId)

        backRepo.push(selectedRepo);
        setBackRepo(backRepo);
        backDocs.push(docs1);
        setSelectedRepo(viewdRepo);
        setViewRepo(data);

    }
    function getDocOnExpand(docs){
        // setDocs1(docs2)
        setDocs2(docs)
    }

    function getDocOnChildExpand(docs){
        setDocs1(docs2)
        setDocs2(docs)
    }

    function backFunction() {
        setViewRepo(selectedRepo)
        setSelectedRepo(backRepo.pop());
        setDocs2(docs1)
        setDocs1(backDocs.pop())
    }

    function  createNewFolder() {
        console.log("opentid"+openId)
        console.log("parent"+parentId)
       
         console.log(newRepo)
             createFolder(newRepo).then(data => {
                if(openId===1){
                    selectedRepo.push(data.data)
                }
                else if(openId===2){
                    viewdRepo.push(data.data)
                }
                handleClose()
                console.log(data.data)
                
             }
                
        )
        
    }

    function changeFolderName(e) {
        console.log(e.target.value)
        setNewRepo({ ...newRepo, [e.target.name]: e.target.value });
        
        if(openId===1){
            if(prevoousParenetId!==0){
                setNewRepo(tempData=>( { ...tempData,parents:{  ...tempData.parents, id:prevoousParenetId }}));
            }else{
                setNewRepo(tempData=>( { ...tempData,parents: null }));
            }
            
        }
        else if(openId===2){
        
            setNewRepo(tempData=>( { ...tempData,parents:{  ...tempData.parents, id:parentId }}));
            console.log(newRepo)
            
        }
    }
    
    function changeParentId(parent) {
    
        console.log(parent)
        setParentId(parent);
        // setNewRepo({ ...newRepo, parents:{id:parent} });
    }

    
    function handleOpenDocs (e,id){
        setOpenId(id);
        setOpenDoc(true);
        setOpen(true);
    };

    function handleOpen (e,id){
        setOpenId(id);
        setOpen(true);
    };

    function handleClose (){
        setOpen(false);
        setOpenDoc(false);
    };

    function handleFilePick(e){
        setNewDocs(e.target.files);
    }

    function createNewDocs(){
        // console.log(newDocs);
        
        for (let index = 0; index < newDocs.length; index++) {
            console.log(newDocs[index])
            if(openId===1){
                if(prevoousParenetId!==0){
                    uploadDocs(newDocs[index], prevoousParenetId).then(data=>{
                       
                        let temp=[];
                        temp=temp.concat(docs1,data.data)
                        setDocs1(temp)
                        
                    })
                }else{
                   
                }
                
            }
            else if(openId===2){
            
                uploadDocs(newDocs[index], parentId).then(data=>{
                    console.log(data.data)
                    let temp=[];
                    temp=temp.concat(docs2,data.data)
                    setDocs2(temp)
                   
                   
                })
                
            }
        }
        

       
        handleClose()
        
    }

    return (
        <div>
            <Grid container >
                <Grid container item xs={12} lg={6}  >

                    <Paper style={{ maxHeight: '80vh', width: '95%' }} elevation={5} >

                        <div style={{ textAlign: 'right' }}>
                            {repoList !== selectedRepo ?
                                <Button onClick={backFunction} variant="contained" endIcon={<ArrowBackIosOutlinedIcon />} style={{ margin: '10px' }}></Button>
                                : null}
                            <Button variant="contained" onClick={(e)=> handleOpenDocs(e,1)} color="primary" endIcon={<NoteAddOutlinedIcon />} style={{ margin: '5px', backgroundColor: blue[500] }}>Ajouter des fichiers</Button>
                            <Button onClick={(e)=> handleOpen(e,1)} variant="outlined" color="secondary" endIcon={<CreateNewFolderOutlinedIcon />} style={{ margin: '5px', }}>Nouveau dossier</Button>
                        </div>
                        <Box style={{ maxHeight: '85%', }} overflow="auto" p={2} >

                            <EnhancedTable rows={selectedRepo} setViewFunction={expandFolder} setDocsFunction={getDocOnExpand} parentIdFunction={changeParentId} name="parent" docs={docs1}/>
                        </Box>
                    </Paper>
                </Grid>
                <Grid container item xs={12} lg={6}>
                    <Paper style={{ maxHeight: '80vh', width: '95%' }} elevation={5} >
                        <div style={{ textAlign: 'right' }}>
                            {repoList !== selectedRepo ?
                                <Button onClick={backFunction} variant="contained" endIcon={<ArrowBackIosOutlinedIcon />} style={{ margin: '10px' }}></Button>
                                : null}
                            <Button variant="contained" onClick={(e)=>handleOpenDocs(e,2)}color="primary" endIcon={<NoteAddOutlinedIcon />} style={{ margin: '5px', backgroundColor: blue[500] }}>Ajouter des fichiers</Button>
                            <Button onClick={(e)=>handleOpen(e,2)} variant="outlined" color="secondary" endIcon={<CreateNewFolderOutlinedIcon />} style={{ margin: '5px', }}>Nouveau dossier</Button>
                        </div>
                        <Box style={{ maxHeight: '85%' }} overflow="auto" flex={1} flexDirection="column" display="flex" p={2}>

                            <EnhancedTable rows={viewdRepo} setViewFunction={expandChildFolder} setDocsFunction={getDocOnChildExpand} parentIdFunction={changeParentId} name="child" docs={docs2}/>
                        </Box>
                    </Paper>
                </Grid>
            </Grid>

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
                                        {openDoc ? 
                                        <Input 
                                        type="file"
                                        variant="outlined"
                                        name="doc"
                                        inputProps={{ multiple: true }}
                                        onChange={handleFilePick}
                                        /> :
                                        <TextField id="outlined-basic" name="name" value={newRepo.name} onChange={changeFolderName} label="Name" variant="outlined" />
                                        }
                                    </Grid>
                                    <Grid container item xs={12} lg={6}  >
                                        <Button
                                            type="submit"
                                            onClick={openDoc? createNewDocs: createNewFolder}
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
    )
}


