import type MediaIconProps from './MediaIconProps';

const MediaIcon = ({ url, IconOn, IconOff }: MediaIconProps) => {
  const hasMedia = url && url.trim() !== '';
  return hasMedia ? (
    <IconOn color='green' size={20} />
  ) : (
    <IconOff color='red' size={20} />
  );
};

export default MediaIcon;
