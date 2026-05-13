import './styles/toast.css';
import closeIcon from '../../../assets/close-icon.svg';
import type { ReactNode } from 'react';
import type { ToastData } from '../../types/toast-data/toast-data';

export interface ToastProps {
  content: ReactNode;
  type: 'success' | 'error' | 'info';
  onClose: () => void;
}

const Toast = ({ content, type, onClose }: ToastProps) => {
  return (
    <section className={`toast-container toast--${type}`}>
      <div className='toast__status-bar'></div>
      <div className='toast__content'>{content}</div>
      <div className='toast__close'>
        <img src={closeIcon} alt='Cerrar' onClick={onClose} />
      </div>
    </section>
  );
};

interface ToastContainerProps {
  data: ToastData
  onClose: () => void;
}

export const ToastContainer = ({ data, onClose }: ToastContainerProps) => {
  return (
    <section className={`toast-container toast--${data.type} falling-container`}>
      <div className='toast__status-bar'></div>
      <div className='toast__content-container'>
        <p>¡Listo!</p>
        <div className='toast__content'>{data.message}</div>
      </div>
      <div className='toast__close'>
        <img src={closeIcon} alt='Cerrar' onClick={onClose} />
      </div>
    </section>
  );
};


export default Toast;
