import type { MenuIconProps } from './menu-icon';
import './styles/menu-icon.css';
import icon from '../../../../../../public/menu.png';

const MenuIcon = ({onSubmit, isActive}: MenuIconProps) => {
    
    return (
        <figure className = {isActive ? 'sidebar-menu-icon_container show-sidebar' : 'sidebar-menu-icon_disable'}>
            <img src = {icon} alt='icono de Menu' onClick = {onSubmit} />
        </figure>
    )
}

export default MenuIcon;