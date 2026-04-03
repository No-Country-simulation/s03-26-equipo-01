import SimpleItem from "../simple-item/SimpleItem";
import type { DropDownContainerProps, DropDownItemProps, DropDownListProps } from "./dropdown-item";
import './styles/dropdown-item.css';
import dropBoxIcon from '../../../../../../../assets/dropbox-icon.svg';
import useActive from "../../../../../../hooks/use-active";

const DropDownItem = ({item, navegate, onActive}: DropDownItemProps) => {

    const {isActive, handleActive} = useActive();
    
    return (
        <div className = 'sidebar-dropdown-item sidebar-item' onClick = {() => handleActive()}>
            <DropDownContainer 
                isActive = {isActive} 
                item = {item}
            />
            {isActive && <DropDownList item = {item} navegate = {navegate} onActive = {onActive} />}
        </div>
    )
}

const DropDownContainer = ({item, isActive}: DropDownContainerProps) => {
    return (
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
                <img src = {dropBoxIcon} className = {isActive ? 'img_sidebar-rotate' : ''}/>
            </figure>
        </section>
    )
}

const DropDownList = ({item, navegate, onActive}: DropDownListProps) => {
    return (
        <section className = 'sidebar-dropdown-subitems-container'>
                {item.subRoutes.map(subRoute => 
                    <SimpleItem 
                        navegate = {navegate}
                        key = {subRoute.id} 
                        item = {subRoute} 
                        handleActive = {onActive}
                    />
                )}
        </section>
    )
}

export default DropDownItem;