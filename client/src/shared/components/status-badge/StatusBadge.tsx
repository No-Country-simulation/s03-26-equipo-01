import './status-badge.css';

interface StatusBadgeProps {
  status: string;
}

const StatusBadge = ({ status }: StatusBadgeProps) => {
  const getStatusClass = (state: string): string => {
    const lowerState = state.toLowerCase();
    if (lowerState.includes('draft')) return 'status-draft';
    if (lowerState.includes('pending') || lowerState.includes('pendiente'))
      return 'status-pending';
    if (lowerState.includes('approved') || lowerState.includes('publicado'))
      return 'status-approved';
    if (lowerState.includes('rejected') || lowerState.includes('rechazado'))
      return 'status-rejected';
    return 'status-default';
  };

  return (
    <span className={`status-badge ${getStatusClass(status)}`}>{status}</span>
  );
};

export default StatusBadge;
