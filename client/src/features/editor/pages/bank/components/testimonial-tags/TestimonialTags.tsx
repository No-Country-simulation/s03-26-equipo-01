import type { TestimonialTagsProps } from './testimonial-tags';
import './testimonial-tags.css';

const TestimonialTags = ({ tags }: TestimonialTagsProps) => {
  return (
    <section className='testimonial-editor-tags'>
      {tags.map((tag) => (
        <Tag key={tag.id} name={tag.name} />
      ))}
    </section>
  );
};

const Tag = ({ name }: { name: string }) => {
  return (
    <div className='testimonial-editor-tag-container'>
      <p>{name}</p>
    </div>
  );
};

export default TestimonialTags;
