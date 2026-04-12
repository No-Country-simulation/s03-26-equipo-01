import type { AdminTestimonialState } from "../../models/state";
import type { StateResponse } from "./dtos/response";

export function stateResponseAdapter(response: StateResponse): AdminTestimonialState { 
    switch (response) {
        case 'APPROVED':
            return 'APROBADO'
        case 'PUBLISHED':
            return 'PUBLICADO'
        case 'PENDING':
            return 'PENDIENTE'
    }
}