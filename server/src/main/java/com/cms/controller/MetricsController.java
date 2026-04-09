package com.cms.controller;

import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.metrics.CategoryMetricDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Metricas agregadas del CMS")
public class MetricsController {

    private final MetricsService metricsService;
    private final AuthUtils authUtils;

    @GetMapping("/tags/testimonials")
    @AdminEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a los tags del administrador autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metricas de tags obtenidas correctamente")
    })
    public ResponseEntity<List<TagMetricDTO>> findAllMetricsTags(Authentication authentication) {
        Long adminId = authUtils.getUserId(authentication);
        return ResponseEntity.ok(metricsService.findAllMetricsTags(adminId));
    }

    @GetMapping("/categories/testimonials")
    @AdminEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a todas las categorias del admin autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metricas de categorias obtenidas correctamente")
    })
    public ResponseEntity<List<CategoryMetricDTO>> findAllMetricsCategories(Authentication authentication) {
        Long adminId = authUtils.getUserId(authentication);
        return ResponseEntity.ok(metricsService.findAllMetricsCategories(adminId));
    }


    @GetMapping("/categories/{categoryId}/testimonials")
    @AdminEndpoint
    @Operation(summary = "Obtener la cantidad de testimonios asociados a una categoria del admin autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrica de categoria obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada o no pertenece al admin")
    })
    public ResponseEntity<CategoryMetricDTO> getCategoryMetrics(
            @Parameter(description = "ID de la categoria", example = "1")
            @PathVariable Long categoryId,
            Authentication authentication
    ) {
        Long adminId = authUtils.getUserId(authentication);
        return ResponseEntity.ok(metricsService.getCategoryMetrics(categoryId, adminId));
    }
}