import { useState } from "react";
import SimpleItem from "../simple-item/SimpleItem";
import type { DropDownItemProps } from "./dropdown-item";
import './styles/dropdown-item.css';

const DropDownItem = ({item}: DropDownItemProps) => {

    const [isActive, setIsActive] = useState<boolean>(false);

    const handleActive = () => setIsActive(!isActive);
    
    return (
        <div className = 'sidebar-dropdown-item sidebar-item'>
            <section className = 'sidebar-dropdown-nav-item-container'>
                <div>
                    <figure>
                        <img src = {item.iconUrl} />
                    </figure>
                    <h4 className = 'sidebar-dropdown-item_title'>{item.title}</h4>
                </div>
            <figure className = 'sidebar-dropdown-item_icon-container'>
                <img src = {item.iconUrl} onClick = {() => handleActive()}/>
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