package com.cms.controller;

import com.cms.controller.annotations.AdminEditorEndpoint;
import com.cms.controller.dto.metrics.CategoryMetricDTO;
import com.cms.controller.dto.metrics.MetricsResponseDTO;
import com.cms.controller.dto.metrics.TagMetricDTO;
import com.cms.services.MetricsService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Metricas agregadas del CMS")
public class MetricsController {

    private final MetricsService metricsService;
    private final AuthUtils authUtils;

    @GetMapping("/tags/testimonials")
    @AdminEditorEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a todos los tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metricas de tags obtenidas correctamente")
    })
    public ResponseEntity<List<TagMetricDTO>> findAllMetricsTags() {
        return ResponseEntity.ok(metricsService.findAllMetricsTags());
    }

    @GetMapping("/categories/testimonials")
    @AdminEditorEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a todas las categorias del admin autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metricas de categorias obtenidas correctamente")
    })
    public ResponseEntity<List<CategoryMetricDTO>> findAllMetricsCategories(Authentication authentication) {
        Long adminId = authUtils.getUserId(authentication);
        return ResponseEntity.ok(metricsService.findAllMetricsCategories(adminId));
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
