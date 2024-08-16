package com.company.firstprojectspring.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private LocalDate age;


    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Card> cardList;


    @OneToMany(
            mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Prod> prodList;



}
