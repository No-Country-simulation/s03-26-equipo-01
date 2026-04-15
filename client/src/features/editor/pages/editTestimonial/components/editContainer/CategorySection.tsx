import {
  Box,
  Typography,
  Autocomplete,
  TextField,
  CircularProgress,
} from '@mui/material';
import type { Category } from '../../../../../admin/models/category';
import { useCategories } from '../../hooks/use-categories';

interface CategorySectionProps {
  defaultValue?: Category | null;
}

export const CategorySection = ({ defaultValue }: CategorySectionProps) => {
  const {
    categories,
    selectedCategory,
    setSelectedCategory,
    loading,
    searchByName,
  } = useCategories(defaultValue);

  return (
    <Box>
      <Typography variant='subtitle2' fontWeight={600} mb={1}>
        1.- Categoría
      </Typography>
      <Autocomplete
        options={categories}
        getOptionLabel={(option) => option.name}
        value={selectedCategory}
        onChange={(_, newValue) => setSelectedCategory(newValue)}
        onInputChange={(_, value) => searchByName(value)}
        loading={loading}
        renderInput={(params) => (
          <TextField
            {...params}
            label='Seleccionar categoría'
            variant='standard'
            InputProps={{
              ...params.InputProps,
              endAdornment: (
                <>
                  {loading && <CircularProgress size={16} />}
                  {params.InputProps.endAdornment}
                </>
              ),
            }}
          />
        )}
      />
    </Box>
  );
};
