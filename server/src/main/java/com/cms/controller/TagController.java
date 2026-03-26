package com.cms.controller;

import com.cms.controller.dtos.TagRequestDto;
import com.cms.controller.dtos.TagResponseDto;
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

    @PostMapping
    public ResponseEntity<TagResponseDto> create(@Valid @RequestBody TagRequestDto tagRequestDto) {
        Tag createdTag = tagService.create(toModel(tagRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto(createdTag));
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> findAll() {
        List<TagResponseDto> tags = tagService.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(toResponseDto(tagService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDto> update(@PathVariable Long id, @Valid @RequestBody TagRequestDto tagRequestDto) {
        Tag updatedTag = tagService.update(id, toModel(tagRequestDto));
        return ResponseEntity.ok(toResponseDto(updatedTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Tag toModel(TagRequestDto tagRequestDto) {
        return Tag.builder()
                .name(tagRequestDto.name())
                .build();
    }

    private TagResponseDto toResponseDto(Tag tag) {
        return new TagResponseDto(
                tag.getId(),
                tag.getName(),
                tag.getCreatedAt(),
                tag.getUpdatedAt()
        );
    }
}
