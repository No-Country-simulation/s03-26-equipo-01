
export type Testimonial = {
    fullName: string,
    email: string,
    testimonial: string,
    course: number | null,
    authorization: boolean,
    youtubeUrl?: string,
    image?: File | null,
    rating: number | null,
    idEmbed?: number
}
