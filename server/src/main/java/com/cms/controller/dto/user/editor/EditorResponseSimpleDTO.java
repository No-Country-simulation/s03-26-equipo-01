package com.cms.controller.dto.user.editor;

import com.cms.model.user.impl.Editor;

public record EditorResponseSimpleDTO(
        Long id,
        String mail,
        String Rol,
        Integer testimonialsNumber,
        Boolean enable
) {
    public static EditorResponseSimpleDTO fromModel(Editor editor) {
        return new EditorResponseSimpleDTO(
                editor.getId(),
                editor.getEmail(),
                editor.getRole(),
                editor.getNroTestimonials(),
                editor.isEnabled()
        );
    }
}
