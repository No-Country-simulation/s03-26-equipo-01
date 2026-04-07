package com.cms.services;

import com.cms.model.testimonial.Media;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MediaServiceTest {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ResetService resetService;

    @Test
    void SavedMediaAndDeleteImage() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("logo.jpg");
        if (is == null) throw new IllegalStateException("logo.jpg no encontrado en src/test/resources/");

        MockMultipartFile image = new MockMultipartFile(
                "file",
                "test-image.jpg",
                "image/jpeg",
                is.readAllBytes()
        );

        String youtubeUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

        Media created = mediaService.save(image, youtubeUrl);

        assertNotNull(created);
        assertNotNull(created.getPublicId());
        assertNotNull(created.getUrl());
        assertNotNull(created.getVideoId());

        mediaService.deleteImage(created.getPublicId());

        Media deleted = mediaService.findById(created.getId());
        assertNull(deleted.getUrl());
        assertNull(deleted.getPublicId());
    }
    @Test
    void SavedMediaAndDeleteVideo() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("logo.jpg");
        if (is == null) throw new IllegalStateException("logo.jpg no encontrado en src/test/resources/");

        MockMultipartFile image = new MockMultipartFile(
                "file",
                "test-image.jpg",
                "image/jpeg",
                is.readAllBytes()
        );

        String youtubeUrl = "https://www.youtube.com/watch?v=Y2oemCK-vAU";

        Media created = mediaService.save(image, youtubeUrl);

        assertNotNull(created);
        assertNotNull(created.getPublicId());
        assertNotNull(created.getUrl());
        assertNotNull(created.getVideoId());

        mediaService.deleteVideo(created.getVideoId());

        Media deleted = mediaService.findById(created.getId());
        assertNull(deleted.getVideoTitle());
        assertNull(deleted.getThumbnailUrl());
        assertNull(deleted.getVideoUrl());
        assertNull(deleted.getVideoId());
        assertNull(deleted.getChannelName());
    }


    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}