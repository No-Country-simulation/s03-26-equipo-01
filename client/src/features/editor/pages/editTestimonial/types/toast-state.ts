import type { ReactNode } from 'react';

export interface ToastState {
  content: ReactNode;
  type: 'success' | 'error';
}
