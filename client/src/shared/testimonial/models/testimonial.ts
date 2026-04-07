
export type Testimonial = {
    fullName: string,
    email: string,
    testimonial: string,
    course: number | null,
    authorization: boolean,
    youtubeUrl?: string,
    image?: string | null,
    rating: number | null,
    idEmbed?: number
}