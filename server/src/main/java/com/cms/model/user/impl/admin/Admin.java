package com.cms.model.user.impl.admin;

import com.cms.exception.business.impl.DuplicateResourceException;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.embeds.Embed;
import com.cms.model.user.User;
import com.cms.model.user.impl.Editor;
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

    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    @Builder.Default
    private List<Editor> editors = new ArrayList<>();

    @Builder.Default
    private List<Category> categories = new ArrayList<>();

    public void agregarEmbed(Embed embed) {
        embeds.add(embed);
    }

    public void agregarTag(Tag tag, Boolean hasNamesTagInListAdmin) {
        validatedIfTagNameDuplicated(tag,hasNamesTagInListAdmin);
        tags.add(tag);
    }

    private void validatedIfTagNameDuplicated(Tag tag, Boolean hasNamesTagInListAdmin) {
        if (hasNamesTagInListAdmin) throw new DuplicateResourceException("El admin ya tiene una tag con el nombre: " + tag.getName());

    }
}
