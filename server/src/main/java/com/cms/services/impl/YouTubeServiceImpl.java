package com.cms.services.impl;

import com.cms.client.YoutubeClient;
import com.cms.client.dto.YouTubeVideoResponse;
import com.cms.model.testimonial.Media;
import com.cms.services.YoutubeService;
import org.springframework.stereotype.Service;

@Service
public class YouTubeServiceImpl implements YoutubeService {

    private final YoutubeClient youtubeClient;

    public YouTubeServiceImpl(YoutubeClient youtubeClient) {
        this.youtubeClient = youtubeClient;
    }

    @Override
    public Media fromUrl(String youtubeUrl) {
        String videoId = extractVideoId(youtubeUrl);
        if (videoId == null) {
            throw new IllegalArgumentException("URL de YouTube inválida: " + youtubeUrl);
        }

        YouTubeVideoResponse response = youtubeClient.getVideoInfo(videoId);

        if (response.isEmpty()) {
            throw new IllegalArgumentException("El video no existe o no está disponible");
        }

        YouTubeVideoResponse.Item item = response.items().getFirst();

        if (!"public".equals(item.status().privacyStatus())) {
            throw new IllegalArgumentException("El video no es público");
        }

        return Media.builder()
                .videoId(videoId)
                .videoUrl("https://www.youtube.com/embed/" + videoId)
                .videoTitle(item.snippet().title())
                .thumbnailUrl(item.snippet().thumbnails().high().url())
                .channelName(item.snippet().channelTitle())
                .build();
    }

    private String extractVideoId(String url) {
        String pattern = "(?:youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]{11})";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(url);
        return matcher.find() ? matcher.group(1) : null;
    }
}