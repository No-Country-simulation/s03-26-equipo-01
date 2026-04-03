package com.cms.model.user.impl;

import com.cms.model.user.User;
import com.cms.model.user.impl.admin.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Editor extends User {

    private Admin createdBy;
}
