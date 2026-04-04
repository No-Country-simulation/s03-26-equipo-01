package com.cms.model.user.impl.admin;

import com.cms.model.embeds.Embed;
import com.cms.model.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Admin extends User {

    @Builder.Default
    private List<Embed> embeds = new ArrayList<>();

    public void agregarEmbed(Embed embed) {
        embeds.add(embed);
    }
}
