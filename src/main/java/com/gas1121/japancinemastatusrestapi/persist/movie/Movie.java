package com.gas1121.japancinemastatusrestapi.persist.movie;


import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Override
    public  String toString() {
        return String.format(
                "Movie[id=%d, title='%s']",
                id, title
        );
    }
}
