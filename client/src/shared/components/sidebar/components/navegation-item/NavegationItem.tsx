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
        <>
            {isElementActive(item.id) && <section className = 'sidebar-item-active'>aa</section>}
            <SimpleItem 
                item = {item} 
                navegate = {handleNavegate} 
                handleActive = {handleActive}
            /> 
        </>
    )
}

const DropNavItem = ({item, handleNavegate, handleActive, isElementActive}: DropNavItemProps) => {
    return (
        <>
            {isElementActive(item.id) && <section className = 'sidebar-item-active'>aa</section>}
            <DropDownItem 
                item = {item} 
                navegate = {handleNavegate} 
                onActive = {handleActive}
            />
        </>
    )
}


export default NavegationItem;