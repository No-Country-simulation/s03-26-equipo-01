import type { NavegationItemProps } from "../../navegation-item";

const DropDownItem = ({item}: NavegationItemProps) => {
    
    return (
        <button>
            <h3>{item.title}</h3>
            <figure>
                <img src = {item.iconUrl} />
            </figure>
        </button>
    )
}

export default DropDownItem;