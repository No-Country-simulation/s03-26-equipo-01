import useActive from '../../../../hooks/use-active';
import NavegationItem from '../navegation-item/NavegationItem';
import type { NavegationListProps } from './navegation-list';
import './navegation-list.css';


const NavegationList = ({ itemsData, urlBase }: NavegationListProps) => {

    const {isElementActive, handleActive} = useActive();

    return (
        <div className = 'home-sidebar-navegation-list'>
            <nav>
                <ul>
                    {itemsData.map(item => 
                        <NavegationItem 
                            isElementActive = {isElementActive}
                            handleActive = {handleActive}
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