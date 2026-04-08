import type { Media } from "../../models/media";
import type { MediaResponse } from "./dtos/media";

export function mediaAdapter(media: MediaResponse): Media {
    return {
        imageUrl: media.imageUrl,
        imagePublicId: media.imagePublicId,
        videoUrl: media.videoUrl,
        videoId: media.videoId,
        thumbnailUrl: media.thumbnailUrl
    }
}