import DropDownItem from './components/dropdown-item/DropdownItem';
import SimpleItem from './components/simple-item/SimpleItem';
import type { NavegationItemProps } from './navegation-item';
import './styles/navegation-item.css';

const NavegationItem = ({item}: NavegationItemProps) => {
 
    return (
        item.type === 'simple' ? 
            <SimpleItem item = {item} /> :
            <DropDownItem item = {item} />
    )
}

export default NavegationItem;