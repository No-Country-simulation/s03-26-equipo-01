package com.cms.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YouTubeVideoResponse(
        List<Item> items
) {
    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Item(
            Snippet snippet,
            Status status
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Snippet(
            String title,
            String channelTitle,
            Thumbnails thumbnails
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Thumbnails(
            Thumbnail high
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Thumbnail(
            String url
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Status(
            String privacyStatus
    ) {}
}