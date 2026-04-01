import NavegationItem from '../navegation-item/NavegationItem';
import type { NavegationListProps } from './navegation-list';
import './navegation-list.css';

const NavegationList = ({ itemsData }: NavegationListProps) => {
    return (
        <div className = 'home-sidebar-navegation-list'>
            <nav>
                <ul>
                    {itemsData.map(item => (
                        <NavegationItem
                            key = {item.id}
                            item = {item}
                        />
                    ))}
                </ul>
            </nav>
        </div>
    )
}

export default NavegationList;