import TestimonialCard from "../testomonial-card/TestimonialCard";
import type { TestimonialsContainerProps } from "./testomonial-container";
import './styles/testimonials-container.css';
import { useState } from "react";
import { ToastContainer } from "../../../../../../shared/components/toast/Toast";
import type { ChangeStateResult } from "./types/change-state-result";
import toastData from "./service/data-result.service";

const TestimonialsList = ({testimonials}: TestimonialsContainerProps) => {

    const [actionResult, setActionResult] = useState<ChangeStateResult | null>(null);

    const handleClose = () => setActionResult(null);
    const handleChangeState = (result: ChangeStateResult) => setActionResult(result);

    return (
        <section className = 'testimonials-admin-list'>
            <div className = 'testimonials-admin-list_container'>
                {testimonials.map(testimonial => 
                <TestimonialCard 
                    key = {testimonial.id}
                    testimonial = {testimonial}
                    onChangeState = {handleChangeState}
                />
            )}
            </div>
            {actionResult && <ToastContainer data={toastData(actionResult)} onClose={handleClose} />}
        </section>
    )
}

export default TestimonialsList;