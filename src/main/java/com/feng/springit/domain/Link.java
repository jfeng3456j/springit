package com.feng.springit.domain;

import com.feng.springit.service.BeanUtil;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//class represent a table in the db
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Link extends Auditable {

    //https://www.danvega.dev/docs/spring-boot-2-docs/#_spring_mvc_model

    //    using lombok to create this required constructor
    //    public Link(String title, String url) {
    //        this.title = title;
    //        this.url = url;
    //    };

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty(message = "please enter a title.")
    private String title;

    @NonNull
    @NotEmpty(message = "please enter a url.")
    @URL(message = "please enter a valid url.")
    private String url;

    //comment
    @OneToMany(mappedBy = "link")
    private List<Comment> comments = new ArrayList<>();

    //vote
    @OneToMany(mappedBy = "link")
    private List<Vote> votes = new ArrayList<>();

    private int voteCount = 0;

    @ManyToOne
    private User user;

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    //get the domain name
    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(this.url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getCreationDate()));
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
