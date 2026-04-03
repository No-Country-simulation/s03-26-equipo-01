package com.cms.model;

import com.cms.model.user.User;

public record AuthResult(String token, User user) {
}
