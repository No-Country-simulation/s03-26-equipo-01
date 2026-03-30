package com.cms.exception.business.impl;

import com.cms.exception.business.BusinessException;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
