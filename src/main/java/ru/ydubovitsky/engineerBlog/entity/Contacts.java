package ru.ydubovitsky.engineerBlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String phone;

    String address;

    String webSite;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private Integer appUserId;
}
