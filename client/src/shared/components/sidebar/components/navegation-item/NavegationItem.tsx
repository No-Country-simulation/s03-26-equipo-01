import { useNavigate } from 'react-router-dom';
import DropDownItem from './components/dropdown-item/DropdownItem';
import SimpleItem from './components/simple-item/SimpleItem';
import type { NavegationItemProps } from './navegation-item';
import './styles/navegation-item.css';

const NavegationItem = ({item, urlBase}: NavegationItemProps) => {

    const navegate = useNavigate();

    const handleNavegate = (url: string) => navegate(`/${urlBase}/${url}`);
 
    return (
        item.type === 'simple' ? 
            <SimpleItem item = {item} navegate = {handleNavegate} /> :
            <DropDownItem item = {item} navegate = {handleNavegate} />
    )
}

export default NavegationItem;