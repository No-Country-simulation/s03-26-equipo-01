import type { SimpleItemProps } from './simple-item';
import './styles/simple-item.css';

const SimpleItem = ({item}: SimpleItemProps) => {
    
    return (
        <div className = 'sidebar-simple-item sidebar-item'>
            <button className = 'sidebar-simple-item_button'>
                <div>
                    <figure className = 'sidebar-simple-item_icon-container'>
                        <img src = {item.iconUrl} />
                    </figure>
                    <h4 className = 'sidebar-simple-item_title'>{item.title}</h4>
                </div>
            </button>
        </div>
    )
}

export default SimpleItem;