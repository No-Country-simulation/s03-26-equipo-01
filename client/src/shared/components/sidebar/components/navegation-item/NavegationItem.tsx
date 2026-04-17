import { useNavigate } from 'react-router-dom';
import DropDownItem from './components/dropdown-item/DropdownItem';
import SimpleItem from './components/simple-item/SimpleItem';
import type { DropNavItemProps, NavegationItemProps, SimpleNavItemProps } from './navegation-item';
import './styles/navegation-item.css';

const NavegationItem = ({item, urlBase, isRouteActive}: NavegationItemProps) => {

    const navegate = useNavigate();

    const handleNavegate = (url: string) => navegate(`/${urlBase}/${url}`);
    const isSelected = item.type === 'simple'
        ? isRouteActive(item.routePage)
        : item.subRoutes.some(subRoute => isRouteActive(subRoute.routePage));
 
    return (
        item.type === 'simple' ? 
            <SimpleNavItem 
                item = {item} 
                handleNavegate = {handleNavegate} 
                isSelected = {isSelected}
            /> :
            <DropNavItem 
                item = {item} 
                handleNavegate = {handleNavegate} 
                isRouteActive = {isRouteActive}
                isSelected = {isSelected}
            />
    )
}

const SimpleNavItem = ({item, handleNavegate, isSelected}: SimpleNavItemProps) => {
    return (
        <section className = {isSelected ? 'sidebar-item_container--selected' : 'sidebar-item_container'}>
            <div className = {isSelected ? 'sidebar-item-selected' : 'sidebar-item-disable'} />
            <SimpleItem 
                item = {item} 
                navegate = {handleNavegate} 
            /> 
        </section>
    )
}

const DropNavItem = ({item, handleNavegate, isRouteActive, isSelected}: DropNavItemProps) => {
    return (
        <section className = {isSelected ? 'sidebar-item_container--selected' : 'sidebar-item_container'}>
            <div className = 'sidebar-item-disable' />
            <DropDownItem 
                item = {item} 
                navegate = {handleNavegate} 
                isRouteActive = {isRouteActive}
            />
        </section>
    )
}


export default NavegationItem;
