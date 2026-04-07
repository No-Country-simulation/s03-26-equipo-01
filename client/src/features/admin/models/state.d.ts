export type State = 'Aprobado' | 'Borrador' | 'Publicado' | 'Pendiente'
export type AdminState = Exclude<State, 'Borrador'>