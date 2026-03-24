package com.cms.exception;


final public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String entity;
    private Long id = null;
    private String nombre = null;

    public EntityNotFoundException(String entity, Long id) {
        this.entity = entity;
        this.id = id;
    }

    public EntityNotFoundException(String entity, String nombre) {
        this.entity = entity;
        this.nombre = nombre;
    }

    @Override
    public String getMessage() {
        if (id == null)
            return "invalid " + entity + " #" + nombre;
        else
            return "invalid " + entity + " #" + id;
    }
}
