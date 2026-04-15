import { Box, Typography, TextField } from '@mui/material';

interface TestimonialSectionProps {
  defaultValue: string;
}

export const TestimonialSection = ({
  defaultValue,
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
      defaultValue={defaultValue}
    />
  </Box>
);
