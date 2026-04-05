package com.cms.model.user.impl.admin;

import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResource {

    private List<Category> categories;

    private List<User> users;

    private List<Tag> tags;
}
