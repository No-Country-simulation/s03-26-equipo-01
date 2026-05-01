import { useLocation } from 'react-router-dom';
import NavegationItem from '../navegation-item/NavegationItem';
import type { NavegationListProps } from './navegation-list';
import './navegation-list.css';


const NavegationList = ({ itemsData, urlBase }: NavegationListProps) => {

    const location = useLocation();
    const currentPath = location.pathname.replace(/\/+$/, '');
    const isRouteActive = (routePage: string) => currentPath === `/${urlBase}/${routePage}`;

    return (
        <div className = 'home-sidebar-navegation-list'>
            <nav>
                <ul>
                    {itemsData.map(item => 
                        <NavegationItem 
                            isRouteActive = {isRouteActive}
                            key = {item.id} 
                            item = {item} 
                            urlBase = {urlBase} 
                        />
                    )}
                </ul>
            </nav>
        </div>
    )
}

export default NavegationList;
