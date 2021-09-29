import { Container, Divider } from '@material-ui/core'
import React from 'react'
import { getHistory } from '../services/ApiService';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import FolderIcon from '@material-ui/icons/Folder';
import DescriptionOutlinedIcon from '@material-ui/icons/DescriptionOutlined';
import GetAppOutlinedIcon from '@material-ui/icons/GetAppOutlined';
import VisibilityOutlinedIcon from '@material-ui/icons/VisibilityOutlined';
import { deepOrange, deepPurple, blue } from '@material-ui/core/colors';
import IconButton from '@material-ui/core/IconButton';


export default function History() {
    const [historyList, setHistoryList] = React.useState([]);


    React.useEffect(() => {
        getHistory().then(data => {
            setHistoryList(data.data)
            let newDate = new Date()
            console.log(newDate.getTime())
        })
    }, []);

    return (
        <Container>
            <h1>Historique</h1>
            <Divider/>

       

<TableContainer component={Paper}>
<Table  aria-label="simple table">
  {/* <TableHead>
    <TableRow>
      <TableCell>Dessert (100g serving)</TableCell>
      <TableCell align="right">Calories</TableCell>
      <TableCell align="right">Fat&nbsp;(g)</TableCell>
      <TableCell align="right">Carbs&nbsp;(g)</TableCell>
      <TableCell align="right">Protein&nbsp;(g)</TableCell>
    </TableRow>
  </TableHead> */}
  <TableBody>
    {historyList && historyList.map((row) => (
      <TableRow key={row.name}>
        <TableCell component="th" scope="row">
          {row.mediaType ==="Folder" ? <FolderIcon style={{color: blue[500]}}/> : <DescriptionOutlinedIcon style={{color: deepPurple[500]}}/>}
        </TableCell>
        <TableCell align="left">{row.mediaName}</TableCell>
        <TableCell align="right">{row.changeDate}</TableCell>
        <TableCell align="right">{row.mediaSize}</TableCell>
        <TableCell align="right">{row.mediaState}</TableCell>
        <TableCell>
            <IconButton color="primary" >
                <GetAppOutlinedIcon style={{ fontSize: 17 }} />
            </IconButton>
            <IconButton color="primary" >
                <VisibilityOutlinedIcon style={{ fontSize: 17 }} />
            </IconButton>
            
        </TableCell>
      </TableRow>
    ))}
  </TableBody>
</Table>
</TableContainer>
</Container>
    )
}
