import type { CredentialsErrorContainerProps } from './credentials-error-container';
import './credentials-error-container.css';

const CredentialsErrorContainer = ({message}: CredentialsErrorContainerProps) => {
    return (
        <div>
            <p>{message}</p>
        </div>
    )
}

export default CredentialsErrorContainer;