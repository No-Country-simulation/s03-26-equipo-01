import type { TableResponseDTO } from '../pages/bank/components/table';
import type { TestimonialSimpleDTO } from '../pages/bank/components/testimonial';
import type { TableDataContent } from '../../../shared/types/table/table';

const adaptTestimonialBankToTable = (
  data: TableResponseDTO<TestimonialSimpleDTO>,
): TableDataContent => ({
  headers: data.headers,
  rows: data.rows.map((row) => ({
    id: row.id,
    fields: [
      row.data.testimonial,
      row.data.media?.videoUrl ? '✅' : '❌',
      row.data.media?.thumbnailUrl ? '✅' : '❌',
      row.data.tags.map((t) => t.name).join(', '),
      String(row.data.rating),
    ],
  })),
});

export default adaptTestimonialBankToTable;
