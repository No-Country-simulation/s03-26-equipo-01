import { Box, Typography, TextField } from '@mui/material';

interface TestimonialSectionProps {
  value: string;
  onChange: (val: string) => void;
}

export const TestimonialSection = ({
  value,
  onChange,
}: TestimonialSectionProps) => (
  <Box>
    <Typography variant='subtitle2' fontWeight={600} mb={1}>
      3.- Testimonio
    </Typography>
    <Typography variant='caption' color='text.secondary'>
      Testimonio
    </Typography>
    <TextField
      fullWidth
      multiline
      rows={5}
      variant='standard'
      value={value}
      onChange={(e) => onChange(e.target.value)}
    />
  </Box>
);
