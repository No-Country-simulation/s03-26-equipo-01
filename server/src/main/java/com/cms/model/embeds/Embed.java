package com.cms.model.embeds;

import com.cms.model.user.impl.admin.Admin;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Embed {
    private Long id;


    private Admin admin;





}
