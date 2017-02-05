package com.jeremp.firstnames.web;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import com.jeremp.firstnames.services.DataLoaderService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jeremp
 */
@RestController
@RequestMapping("/data")
public class DataController {
    
    @Autowired
    private FirstNameStatRepository statRepository ;
    
    @Autowired
    private DataLoaderService dataLoaderService ;
    
    @GetMapping("")
    public ResponseEntity<List<String>> guessFirstName(@RequestParam String firstName) throws SQLException{
        if(StringUtils.isNotBlank(firstName)){
            Page<FirstNameStat> page = statRepository.findDistinctByFirstNameStartsWithIgnoreCase(firstName, new PageRequest(0, 10, Sort.Direction.ASC, "firstName"));
            List<String> data = dataLoaderService.findDistinctNames(firstName.toUpperCase(Locale.FRENCH));
            return new ResponseEntity<>(data, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
