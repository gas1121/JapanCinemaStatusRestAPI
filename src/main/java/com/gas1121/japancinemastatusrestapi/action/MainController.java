package com.gas1121.japancinemastatusrestapi.action;

import com.gas1121.japancinemastatusrestapi.persist.showings.Showing;
import com.gas1121.japancinemastatusrestapi.persist.showings.ShowingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ShowingRepository showingRepository;

    @GetMapping(path="/api/showings", produces = CONTENT_TYPE)
    public @ResponseBody String api() {
        logger.info("get /api/showings");
        Iterable<Showing> result = showingRepository.findAll();
        logger.info(result.toString());
        return result.toString();
    }
}