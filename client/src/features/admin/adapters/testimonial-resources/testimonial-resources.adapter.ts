import type TestimonialResources from "../../models/testimonial-resources";
import { categoriesAdapter } from "../category/category.adapter";
import { editorsAdapter } from "../editor/editor.adapter";
import type { TestimonialResourcesResponse } from "./dtos/response";

function testimonialResourcesAdapter(response: TestimonialResourcesResponse): TestimonialResources {
    return {
        users: editorsAdapter(response.users),
        category: categoriesAdapter(response.category),
        states: response.states
    }
}

export default testimonialResourcesAdapter;