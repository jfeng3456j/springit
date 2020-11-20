package com.feng.springit.domain;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


//class represent a table in the db
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
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

//    using lombok to creat this required constructor
//    public Link(String title, String url) {
//        this.title = title;
//        this.url = url;
//    };

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
