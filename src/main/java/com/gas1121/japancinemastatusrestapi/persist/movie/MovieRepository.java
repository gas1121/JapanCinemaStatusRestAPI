package com.gas1121.japancinemastatusrestapi.persist.movie;


import com.gas1121.japancinemastatusrestapi.persist.ReadOnlyRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends ReadOnlyRepository<Movie, Long> {

    List<Movie> findByTitle(@Param("title") String title);
}
