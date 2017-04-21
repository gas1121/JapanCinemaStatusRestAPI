package com.gas1121.japancinemastatusrestapi.action;

import com.gas1121.japancinemastatusrestapi.persist.showings.Showing;
import com.gas1121.japancinemastatusrestapi.persist.showings.ShowingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;

@RestController
public class MainController {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ShowingRepository showingRepository;

    @GetMapping(path="/api/showing", produces = CONTENT_TYPE)
    public @ResponseBody String api() {
        logger.info("get /api/showing");
        Iterable<Object[]> result = showingRepository.getAllData();
        StringBuilder output = new StringBuilder();
        for (Object[] curr: result) {
            for (Object column: curr) {
                output.append(column.toString());
                output.append(" ");
            }
            output.append("\n");
        }
        logger.info(output.toString());
        return output.toString();
    }
}