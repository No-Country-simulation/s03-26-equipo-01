package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.CreateTagDto;
import com.cms.controller.dto.TagResponseDto;
import com.cms.controller.dto.UpdateTagDto;
import com.cms.model.Tag;
import com.cms.services.TagService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    @AdminEditorEndpoint
    public ResponseEntity<List<TagResponseDto>> findAll() {
        List<TagResponseDto> tags = tagService.findAll()
                .stream()
                .map(TagResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    @AdminEditorEndpoint
    public ResponseEntity<TagResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(TagResponseDto.fromEntity(tagService.findById(id)));
    }

    @PostMapping
    @AdminEndpoint
    public ResponseEntity<TagResponseDto> create(@Valid @RequestBody CreateTagDto createTagDto) {
        Tag newTag = Tag.builder().name(createTagDto.name()).build();
        Tag createdTag = tagService.create(newTag);
        return ResponseEntity.status(HttpStatus.CREATED).body(TagResponseDto.fromEntity(createdTag));
    }

    @PutMapping("/{id}")
    @AdminEndpoint
    public ResponseEntity<TagResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTagDto updateTagDto
    ) {
        Tag tagDetails = Tag.builder().name(updateTagDto.name()).build();
        Tag updatedTag = tagService.update(id, tagDetails);
        return ResponseEntity.ok(TagResponseDto.fromEntity(updatedTag));
    }

    @DeleteMapping("/{id}")
    @AdminEndpoint
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}