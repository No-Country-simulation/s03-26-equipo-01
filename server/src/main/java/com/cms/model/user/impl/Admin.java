package com.cms.model.user.impl;

import com.cms.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
}
