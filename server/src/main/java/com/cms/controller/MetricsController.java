package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.services.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Metricas agregadas del CMS")
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/tags/{tagId}/testimonials")
    @AdminEditorEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a un tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrica de tag obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Tag no encontrado")
    })
    public ResponseEntity<TagMetricDTO> getTagMetrics(
            @Parameter(description = "ID del tag", example = "1")
            @PathVariable Long tagId
    ) {
        return ResponseEntity.ok(metricsService.getTagMetrics(tagId));
    }

    @GetMapping("/categories/{categoryId}/testimonials")
    @AdminEditorEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a una categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrica de categoria obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    })
    public ResponseEntity<CategoryMetricDTO> getCategoryMetrics(
            @Parameter(description = "ID de la categoria", example = "1")
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(metricsService.getCategoryMetrics(categoryId));
    }

    @GetMapping("/testimonials")
    @AdminEditorEndpoint
    @Operation(summary = "Obtener ambas metricas de testimonios en una sola respuesta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metricas obtenidas correctamente"),
            @ApiResponse(responseCode = "404", description = "Tag o categoria no encontrados")
    })
    public ResponseEntity<MetricsResponseDTO> getTestimonialsMetrics(
            @Parameter(description = "ID del tag", example = "1")
            @RequestParam Long tagId,
            @Parameter(description = "ID de la categoria", example = "1")
            @RequestParam Long categoryId
    ) {
        return ResponseEntity.ok(metricsService.getTestimonialsMetrics(tagId, categoryId));
    }
}
