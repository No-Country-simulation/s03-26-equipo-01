import { Box, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import type { FilterData } from '../../../../../../shared/types/filter-data/filter-data';
import './my-testimonial-filters.css';

interface MyTestimonialFiltersProps {
  onFilter: (filter: FilterData) => void;
}

const MyTestimonialFilters = ({ onFilter }: MyTestimonialFiltersProps) => {
  const handleFilterChange = (key: string, value: string) => {
    const newFilter: FilterData = {};
    if (value && value !== '') {
      newFilter[key as keyof FilterData] = value;
    }
    onFilter(newFilter);
  };

  return (
    <Box className='my-testimonial-filters-container'>
      <label>Filtrar por</label>
      <Box className='filters-group'>
        <FormControl size='small' sx={{ minWidth: 150 }}>
          <InputLabel>Categoría</InputLabel>
          <Select
            label='Categoría'
            defaultValue=''
            onChange={(e) => handleFilterChange('category', e.target.value)}
          >
            <MenuItem value=''>Todas</MenuItem>
            <MenuItem value='Producto'>Producto</MenuItem>
            <MenuItem value='Servicio'>Servicio</MenuItem>
            <MenuItem value='Otro'>Otro</MenuItem>
          </Select>
        </FormControl>

        <FormControl size='small' sx={{ minWidth: 150 }}>
          <InputLabel>Estado</InputLabel>
          <Select
            label='Estado'
            defaultValue=''
            onChange={(e) => handleFilterChange('state', e.target.value)}
          >
            <MenuItem value=''>Todos</MenuItem>
            <MenuItem value='DRAFT'>Draft</MenuItem>
            <MenuItem value='PENDING'>Pendiente</MenuItem>
            <MenuItem value='PUBLISHED'>Publicado</MenuItem>
          </Select>
        </FormControl>

        <FormControl size='small' sx={{ minWidth: 150 }}>
          <InputLabel>Fecha</InputLabel>
          <Select
            label='Fecha'
            defaultValue=''
            onChange={(e) => handleFilterChange('date', e.target.value)}
          >
            <MenuItem value=''>Todas</MenuItem>
            <MenuItem value='today'>Hoy</MenuItem>
            <MenuItem value='week'>Esta semana</MenuItem>
            <MenuItem value='month'>Este mes</MenuItem>
          </Select>
        </FormControl>
      </Box>
    </Box>
  );
};

export default MyTestimonialFilters;
