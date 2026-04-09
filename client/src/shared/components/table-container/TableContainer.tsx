import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import type { BodyProps, HeaderProps, TableContainerProps } from "./table-container";
import './table.css';

const TableData = ({tableData, children}: TableContainerProps) => {

    return (
        <div className = 'table-data-container'>
            <TableContainer component = {Paper}>
                <Table>
                    <Header tableData = {tableData} />
                    <Body tableData = {tableData} children = {children} />
                </Table>
            </TableContainer>
        </div>
    )
}

const Header = ({tableData}: HeaderProps) => {
    return (
        <TableHead className = "table-header">
            <TableRow>
                {tableData.headers.map(header => 
                    <TableCell key = {header} className = "table-border">{header}</TableCell>
                )}
                <TableCell className = "table-border">ACCIONES</TableCell>
            </TableRow>
        </TableHead>
    )
}

const Body = ({tableData, children}: BodyProps) => {
    return (
        <TableBody>
            {tableData.rows.map(row => 
                <TableRow key = {tableData.rows.indexOf(row)} className = {classColor(tableData.rows.indexOf(row))}>
                    {row.map(cell => 
                        <TableCell key = {row.indexOf(cell)}>{cell.toString()}</TableCell>
                    )}
                    <TableCell>
                        {children}
                    </TableCell> 
                </TableRow>
             )}
                </TableBody>
    )
}

const classColor = (index: number): string => index % 2 === 0  ? 'clean-color' : 'dark-color';

export default TableData;