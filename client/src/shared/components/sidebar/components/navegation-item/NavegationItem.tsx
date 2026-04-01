import DropDownItem from './components/dropdown-item/DropdownItem';
import SimpleItem from './components/simple-item/SimpleItem';
import type { NavegationItemProps } from './navegation-item';
import './navegation-item.css';

const NavegationItem = ({item}: NavegationItemProps) => {
 
    return (
        item.isDropdown ? 
            <DropDownItem item = {item} /> : 
            <SimpleItem item = {item} /> 
    )
}

export default NavegationItem;