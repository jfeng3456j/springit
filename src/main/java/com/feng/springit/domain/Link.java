package com.feng.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


//class represent a table in the db
@Entity
@NoArgsConstructor
@Data
public class Link extends Auditable {

    //https://www.danvega.dev/docs/spring-boot-2-docs/#_spring_mvc_model

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String url;

    //comment
    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<>();
}
