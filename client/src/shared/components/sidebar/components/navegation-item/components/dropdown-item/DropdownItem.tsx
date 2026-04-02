import { useState } from "react";
import SimpleItem from "../simple-item/SimpleItem";
import type { DropDownItemProps } from "./dropdown-item";
import './styles/dropdown-item.css';
import dropBoxIcon from '../../../../../../../assets/dropbox-icon.svg';

const DropDownItem = ({item}: DropDownItemProps) => {

    const [isActive, setIsActive] = useState<boolean>(false);

    const handleActive = () => setIsActive(!isActive);
    
    return (
        <div className = 'sidebar-dropdown-item sidebar-item'>
            <section className = 'sidebar-dropdown-nav-item-container'>
                <div className = 'sidebar-dropdown-nav-item-title-container'>
                    <figure>
                        <img src = {item.iconUrl} />
                    </figure>
                    <div className = 'sidebar-dropdown-title-container'>
                        <h4 className = 'sidebar-dropdown-item_title'>{item.title}</h4>
                    </div>
                </div>
            <figure className = 'sidebar-dropdown-item_icon-container'>
                <img src = {dropBoxIcon} onClick = {() => handleActive()} className = {isActive ? 'img_sidebar-rotate' : ''}/>
            </figure>
            </section>
            {isActive && <section className = 'sidebar-dropdown-subitems-container'>
                {item.subRoutes.map(subRoute => 
                    <SimpleItem 
                        key = {subRoute.id} 
                        item = {subRoute} 
                    />
                )}
            </section>}
        </div>
    )
}

export default DropDownItem;