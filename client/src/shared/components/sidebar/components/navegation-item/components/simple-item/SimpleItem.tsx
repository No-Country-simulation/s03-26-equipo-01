import type { SimpleItemProps } from './simple-item';
import './styles/simple-item.css';

const SimpleItem = ({item}: SimpleItemProps) => {
    
    return (
        <div className = 'sidebar-simple-item sidebar-item'>
            <button className = 'sidebar-simple-item_button'>
                <div className = 'sidebar-simple-item_button_container'>
                    <figure className = 'sidebar-simple-item_icon-container'>
                        <img 
                            src = {item.iconUrl} 
                            alt = {item.iconUrl ? 'icono para el item de navegación' : ''} 
                        />
                    </figure>
                    <div className = 'sidebar-simple-item_button_text-container'>
                        <h4>{item.title}</h4>
                    </div>
                </div>
            </button>
        </div>
    )
}

export default SimpleItem;