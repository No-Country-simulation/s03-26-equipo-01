package com.cms.exception.business.impl;

import com.cms.exception.business.BusinessException;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
