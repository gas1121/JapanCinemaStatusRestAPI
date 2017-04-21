package com.gas1121.japancinemastatusrestapi.persist.showings;


import javax.persistence.*;

@Entity
@Table(name = "showing")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "cinema_name")
    private String cinemaName;
    @Column(name = "cinema_site")
    private String cinemaSite;
    private String screen;
    @Column(name = "seat_type")
    private String seatType;
    @Column(name = "total_seat_count")
    private String totalSeatCount;
    private String source;

    @Override
    public  String toString() {
        return String.format(
                "Showing[id=%d, title='%s', cinemaName='%s]",
                id, title, cinemaName
        );
    }
}