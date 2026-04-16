import type MediaIconProps from './MediaIconProps';

const MediaIcon = ({ url, IconOn, IconOff }: MediaIconProps) => {
  // Verifica que la URL sea válida, no null, no undefined, y no sea solo espacios
  const hasMedia =
    url &&
    typeof url === 'string' &&
    url.trim().length > 0 &&
    url !== 'null' &&
    url !== 'undefined';

  return hasMedia ? (
    <IconOn color='green' size={20} />
  ) : (
    <IconOff color='red' size={20} />
  );
};

export default MediaIcon;
