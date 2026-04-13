import type { Tag } from '../../../../../admin/models/tag';

const TagList = ({ tags }: { tags: Tag[] }) => (
  <div className='tags-container'>
    {tags.map((tag) => (
      <span key={tag.id} className='tag-chip'>
        {tag.name} ×
      </span>
    ))}
  </div>
);

export default TagList;
