import { Star } from 'lucide-react';

const RatingDisplay = ({ rating }: { rating: number }) => (
  <span style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
    <Star color='#f59e0b' fill='#f59e0b' size={16} />
    {rating}/10
  </span>
);

export default RatingDisplay;
