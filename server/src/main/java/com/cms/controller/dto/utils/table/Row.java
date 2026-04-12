package com.cms.controller.dto.utils.table;

import java.util.List;

public record Row<T>(
        Long id,
        T data
) {}