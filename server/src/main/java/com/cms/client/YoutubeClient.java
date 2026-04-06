package com.cms.client;

import com.cms.client.dto.YouTubeVideoResponse;

public interface YoutubeClient {
    public YouTubeVideoResponse getVideoInfo(String videoId);

}
