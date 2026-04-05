import type { StateResponse } from "./dtos/response";

export function stateResponseAdapter(response: StateResponse) { 
    switch (response) {
        case 'APPROVED':
            return 'APROBADO'
        case 'DRAFT':
            return 'BORRADOR'
        case 'PUBLISHED':
            return 'PUBLICADO'
        case 'ARCHIVED':
            return 'ARCHIVADO'
    }
}