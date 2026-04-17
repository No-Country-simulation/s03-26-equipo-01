
export type Testimonial = {
    fullName: string,
    email: string,
    testimonial: string,
    tagIds: number[],
    authorization: boolean,
    youtubeUrl?: string,
    image?: File | null,
    rating: number | null,
    idEmbed?: number
}
