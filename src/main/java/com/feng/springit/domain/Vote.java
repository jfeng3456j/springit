package com.feng.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Vote extends Auditable {

    @Id
    @GeneratedValue
    private Long id;
    private int vote;
    //user
    //link

}
