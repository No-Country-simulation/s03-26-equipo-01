import type { CredentialsErrorContainerProps } from './credentials-error-container';
import './credentials-error-container.css';

const CredentialsErrorContainer = ({message}: CredentialsErrorContainerProps) => {
    return (
        <p>{message}</p>
    )
}

export default CredentialsErrorContainer;