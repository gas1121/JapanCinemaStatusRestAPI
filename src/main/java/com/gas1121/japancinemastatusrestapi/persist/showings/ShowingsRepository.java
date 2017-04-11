package com.gas1121.japancinemastatusrestapi.persist.showings;


import com.gas1121.japancinemastatusrestapi.persist.ReadOnlyRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowingsRepository extends ReadOnlyRepository<Showings, Long> {

    List<Showings> findByTitle(@Param("title") String title);
}