package com.cms.model.embeds;

import com.cms.model.embeds.usuariosLocal.Admin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registry_embeds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Embeds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long embedId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
    @Column(name = "embed_token", nullable = false,unique = true)
    private String embedToken;




}
