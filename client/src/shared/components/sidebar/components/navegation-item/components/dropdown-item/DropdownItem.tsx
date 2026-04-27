import SimpleItem from "../simple-item/SimpleItem";
import type { DropDownContainerProps, DropDownItemProps, DropDownListProps } from "./dropdown-item";
import './styles/dropdown-item.css';
import dropBoxIcon from '../../../../../../../assets/dropbox-icon.svg';
import useActive from "../../../../../../hooks/use-active";

const DropDownItem = ({item, navegate, isRouteActive}: DropDownItemProps) => {

    const {isActive, handleActive} = useActive();
    const isExpanded = isActive || item.subRoutes.some(subRoute => isRouteActive(subRoute.routePage));
    
    return (
        <div className = 'sidebar-dropdown-item sidebar-item' onClick = {() => handleActive(item.id)}>
            <DropDownContainer 
                isActive = {isExpanded} 
                item = {item}
            />
            {isExpanded && <DropDownList item = {item} navegate = {navegate} isRouteActive = {isRouteActive} />}
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

const DropDownList = ({item, navegate, isRouteActive}: DropDownListProps) => {
    return (
        <section className = 'sidebar-dropdown-subitems-container'>
                {item.subRoutes.map(subRoute => 
                    <section
                        className = {isRouteActive(subRoute.routePage) ? 'sidebar-dropdown-subitem sidebar-dropdown-subitem--selected' : 'sidebar-dropdown-subitem'}
                        key = {subRoute.id}
                    >
                        <div className = {isRouteActive(subRoute.routePage) ? 'sidebar-dropdown-subitem_indicator sidebar-dropdown-subitem_indicator--selected' : 'sidebar-dropdown-subitem_indicator'} />
                        <SimpleItem 
                            navegate = {navegate}
                            item = {subRoute} 
                        />
                    </section>
                )}
        </section>
    )
}

export default DropDownItem;
