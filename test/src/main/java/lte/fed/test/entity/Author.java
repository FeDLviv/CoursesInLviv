package lte.fed.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {

    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String name;

}
