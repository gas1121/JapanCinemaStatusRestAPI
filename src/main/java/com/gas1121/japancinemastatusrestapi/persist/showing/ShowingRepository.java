package com.gas1121.japancinemastatusrestapi.persist.showing;


import com.gas1121.japancinemastatusrestapi.persist.ReadOnlyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowingRepository extends ReadOnlyRepository<Showing, Long> {

    List<Showing> findByTitle(@Param("title") String title);

    @Query(value = "select title,sum(total_seat_count) as total from showing " +
            "group by title", nativeQuery = true)
    List<Object[]> getAllData();

    @Query(value = "select title, sum(total_seat_count) as total, cinema_name " +
            "from showing group by title,cinema_name", nativeQuery = true)
    List<Object[]> getAllDataDividedByCinemaName();
}