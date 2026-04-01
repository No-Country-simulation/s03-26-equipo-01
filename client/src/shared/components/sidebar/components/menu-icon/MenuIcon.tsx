import type { MenuIconProps } from './menu-icon';
import './menu-icon.css';
import icon from '../../../../../../public/menu.png';

const MenuIcon = ({onSubmit}: MenuIconProps) => {
    return (
        <figure className = 'sidebar-menu-icon_container'>
            <img src = {icon} alt='icono de Menu' onClick = {onSubmit} />
        </figure>
    )
}

export default MenuIcon;