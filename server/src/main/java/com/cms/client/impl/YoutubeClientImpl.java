package com.cms.client.impl;

import com.cms.client.YoutubeClient;
import com.cms.client.dto.YouTubeVideoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class YoutubeClientImpl implements YoutubeClient {
    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public YouTubeVideoResponse getVideoInfo(String videoId) {
        String url = "https://www.googleapis.com/youtube/v3/videos"
                + "?id=" + videoId
                + "&part=snippet,status"
                + "&key=" + apiKey;

        return restTemplate.getForObject(url, YouTubeVideoResponse.class);
    }
}
