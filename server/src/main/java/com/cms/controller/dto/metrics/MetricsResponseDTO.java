package com.cms.controller.dto.metrics;

public record MetricsResponseDTO(
        TagMetricDTO tag,
        CategoryMetricDTO category
) {
}
