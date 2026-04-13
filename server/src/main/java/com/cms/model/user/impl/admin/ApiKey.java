package com.cms.model.user.impl.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiKey {

    private Long id;
    private String keyHash;
    private String prefix;
    private boolean active;
    private Admin admin;

}