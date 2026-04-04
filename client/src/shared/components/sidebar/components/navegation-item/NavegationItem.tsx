import { useNavigate } from 'react-router-dom';
import DropDownItem from './components/dropdown-item/DropdownItem';
import SimpleItem from './components/simple-item/SimpleItem';
import type { DropNavItemProps, NavegationItemProps, SimpleNavItemProps } from './navegation-item';
import './styles/navegation-item.css';

const NavegationItem = ({item, urlBase, handleActive, isElementActive}: NavegationItemProps) => {

    const navegate = useNavigate();

    const handleNavegate = (url: string) => navegate(`/${urlBase}/${url}`);
 
    return (
        item.type === 'simple' ? 
            <SimpleNavItem 
                item = {item} 
                handleNavegate = {handleNavegate} 
                handleActive = {handleActive}
                isElementActive = {isElementActive}
            /> :
            <DropNavItem 
                item = {item} 
                handleNavegate = {handleNavegate} 
                handleActive = {handleActive}
                isElementActive = {isElementActive}
            />
    )
}

const SimpleNavItem = ({item, handleNavegate, handleActive, isElementActive}: SimpleNavItemProps) => {
    return (
        <section className = {isElementActive(item.id) ? 'sidebar-item_container--selected' : 'sidebar-item_container'}>
            <div className = {isElementActive(item.id) ? 'sidebar-item-selected' : 'sidebar-item-disable'} />
            <SimpleItem 
                item = {item} 
                navegate = {handleNavegate} 
                handleActive = {handleActive}
            /> 
        </section>
    )
}

const DropNavItem = ({item, handleNavegate, handleActive, isElementActive}: DropNavItemProps) => {
    return (
        <section className = {isElementActive(item.id) ? 'sidebar-item_container--selected' : 'sidebar-item_container'}>
            <div className = {isElementActive(item.id) ? 'sidebar-item-selected' : 'sidebar-item-disable'} />
            <DropDownItem 
                item = {item} 
                navegate = {handleNavegate} 
                onActive = {handleActive}
            />
        </section>
    )
}


export default NavegationItem;