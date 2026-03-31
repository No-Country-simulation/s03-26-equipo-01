import type { NavegationItemProps } from './navegation-item';
import './navegation-item.css';

const NavegationItem = ({item}: NavegationItemProps) => {
    
    return (
        <button>
            <h3>{item.title}</h3>
            <figure>
                <img src={item.iconUrl} alt={item.title} />
            </figure>
        </button>
    )
}

export default NavegationItem;