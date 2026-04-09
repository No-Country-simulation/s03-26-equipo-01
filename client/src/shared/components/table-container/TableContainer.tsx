import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import type { TableContainerProps } from "./table-container";

const TableData = ({tableData, children}: TableContainerProps) => {

    return (
        <TableContainer>
            <Table>
                <TableHead>
                    <TableRow>
                        {tableData.headers.map(header => 
                            <TableCell key = {header}>{header}</TableCell>
                        )}
                        <TableCell>Acciones</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tableData.rows.map(row => 
                        <TableRow key = {tableData.rows.indexOf(row)}>
                            {row.map(cell => 
                                <TableCell key = {row.indexOf(cell)}>{cell.toString()}</TableCell>
                            )}
                            <TableCell>
                                {children}
                            </TableCell> 
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer>
    )
}

export default TableData;