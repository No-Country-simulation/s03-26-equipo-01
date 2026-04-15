import { useState, useRef, useEffect } from 'react';
import {
  Box,
  TextField,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
  Paper,
  CircularProgress,
} from '@mui/material';
import type { Category } from '../../../../../admin/models/category';

interface CustomAutocompleteProps {
  value: Category | null;
  options: Category[];
  onChange: (val: Category | null) => void;
  onSearch: (query: string) => void;
  loading?: boolean;
}

export const CategorySection = ({
  value,
  options,
  onChange,
  onSearch,
  loading,
}: CustomAutocompleteProps) => {
  const [open, setOpen] = useState(false);
  const [query, setQuery] = useState('');
  const containerRef = useRef<HTMLDivElement>(null);

  // Derivamos el valor: si está abierto y hay texto escrito, mostramos eso.
  // Si no, mostramos lo que viene del padre (el objeto seleccionado).
  const displayValue = open && query !== '' ? query : value?.name || '';

  useEffect(() => {
    const handleClickOutside = (e: MouseEvent) => {
      if (
        containerRef.current &&
        !containerRef.current.contains(e.target as Node)
      ) {
        setOpen(false);
        setQuery('');
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  return (
    <Box ref={containerRef} sx={{ position: 'relative', width: '100%' }}>
      <TextField
        fullWidth
        label='1.- Categoría'
        variant='standard'
        required
        value={displayValue}
        error={!value && !open}
        helperText={!value && !open ? 'La categoría es obligatoria' : ''}
        onChange={(e) => {
          const val = e.target.value;
          setQuery(val);
          onSearch(val);
          if (!open) setOpen(true);
        }}
        onFocus={() => setOpen(true)}
        autoComplete='off'
        InputProps={{
          endAdornment: loading ? <CircularProgress size={16} /> : null,
        }}
      />

      {open && options.length > 0 && (
        <Paper
          elevation={3}
          sx={{
            position: 'absolute',
            top: '100%',
            left: 0,
            right: 0,
            zIndex: 10,
            maxHeight: 200,
            overflow: 'auto',
            mt: 0.5,
          }}
        >
          <List dense>
            {options.map((option) => (
              <ListItem key={option.id} disablePadding>
                {/* CAMBIO CLAVE: Usamos onMouseDown. 
                  Esto se dispara ANTES que el onBlur del TextField, 
                  evitando que el componente se cierre antes de capturar la opción.
                */}
                <ListItemButton
                  onMouseDown={(e) => {
                    e.preventDefault(); // Evita que el input pierda el foco inmediatamente
                    onChange(option);
                    setQuery('');
                    setOpen(false);
                  }}
                >
                  <ListItemText primary={option.name} />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
        </Paper>
      )}
    </Box>
  );
};
