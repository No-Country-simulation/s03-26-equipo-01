export type StateTestimonial = 'Aprobado' | 'Borrador' | 'Publicado' | 'Pendiente'
export type AdminTestimonialState = Exclude<StateTestimonial, 'Borrador'>