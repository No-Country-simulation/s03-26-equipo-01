interface MultimediaSectionProps {
  title: string;
  name: string;
  mediaUrl?: string;
  placeholder: string;
  type: 'video' | 'image';
}

const MultimediaSection = ({
  title,
  name,
  mediaUrl,
  placeholder,
  type,
}: MultimediaSectionProps) => (
  <div className='multimedia-section'>
    <p>{title}</p>
    <div className='radio-group'>
      <label>
        <input type='radio' name={name} /> Sí
      </label>
      <label>
        <input type='radio' name={name} defaultChecked /> No
      </label>
    </div>
    <div className='thumbnail'>
      {mediaUrl ? (
        type === 'video' ? (
          <a href={mediaUrl} target='_blank' rel='noreferrer'>
            {mediaUrl}
          </a>
        ) : (
          <img src={mediaUrl} alt='thumbnail' />
        )
      ) : (
        <span className='placeholder'>{placeholder}</span>
      )}
    </div>
  </div>
);

export default MultimediaSection;
