import GenericTable from '../../../../../shared/components/table-container/components/generic-table/GenericTable';
import BodyTestimony from '../../../../../shared/components/table-container/components/body-testimony/BodyTestimony';
import type { TablePaginator } from '../../../../../shared/types/table/table';
import type { TestimonialSimpleDTO } from './testimonial';

interface TableBankProps {
  tableData: TablePaginator<TestimonialSimpleDTO>;
  onAsoc: (id: number) => void;
}

const TableBank = ({ tableData }: TableBankProps) => {
  return (
    <GenericTable
      data={tableData}
      renderBody={
        <BodyTestimony
          rows={tableData.rows}
          currentPage={tableData.page}
          pageSize={tableData.size}
          columns={tableData.headers}
        />
      }
    />
  );
};

export default TableBank;
