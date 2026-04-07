import type { TestimonialTagsProps } from "./testimonial-tags";

const TestimonialTags = ({testimonial}: TestimonialTagsProps) => {
    return (
        <section>
            {testimonial.tags.map(tag => 
                <p>{tag.name}</p>
            )}
        </section>
    )
}

export default TestimonialTags;