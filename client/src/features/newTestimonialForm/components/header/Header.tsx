import './styles/header.css';

type HeaderPropsType = {
  title: string,
  subtitle: string
}

const Header = ({title, subtitle}: HeaderPropsType) => {
  return (
    <div className='header-container'>
      <div className="header">
        <h1 className="header_title">{title}</h1>
        <h4 className="header_subtitle">{subtitle}</h4>
      </div>
    </div>
  )
}

export default Header;
