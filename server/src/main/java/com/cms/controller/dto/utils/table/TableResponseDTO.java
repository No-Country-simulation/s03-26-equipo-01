package com.cms.controller.dto.utils.table;

import org.springframework.data.domain.Page;

import java.util.List;

public record TableResponseDTO<T>(
        List<String> headers,
        List<Row<T>> rows,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public record Row<T>(Long id, T data) {}

    public static <T> TableResponseDTO<T> fromPage(List<String> headers, Page<T> page,
                                                   java.util.function.Function<T, Long> idExtractor) {
        return new TableResponseDTO<>(
                headers,
                page.getContent().stream()
                        .map(item -> new Row<>(idExtractor.apply(item), item))
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}