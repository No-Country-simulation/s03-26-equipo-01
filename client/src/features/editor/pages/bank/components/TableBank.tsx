import TableEditData from '../../../../../shared/components/table-container/TableContainer';
import BodyBankAction from './BodyBankAction';
import type { TableResponseDTO } from './table';
import type { TestimonialSimpleDTO } from './testimonial';

interface TableBankProps {
  tableData: TableResponseDTO<TestimonialSimpleDTO>;
  onAsoc: (id: number) => void;
}

const TableBank = ({ tableData, onAsoc }: TableBankProps) => {
  return (
    <TableEditData
      tableData={{ headers: tableData.headers, rows: [] }}
      activeEdit={() => {}}
      activeDelete={() => {}}
      customBody={
        <BodyBankAction
          rows={tableData.rows}
          onAsoc={onAsoc}
          currentPage={tableData.page}
          pageSize={tableData.size}
        />
      }
    />
  );
};

export default TableBank;
