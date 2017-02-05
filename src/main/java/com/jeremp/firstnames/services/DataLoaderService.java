package com.jeremp.firstnames.services;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author jeremp
 */
@Service
public class DataLoaderService {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderService.class);
    
    @Autowired
    private FirstNameStatRepository fnStatRepository ;
    
    public void loadData(InputStream is) throws IOException{
        try {
            List<String> lines = IOUtils.readLines(is, "UTF-8");
            if(lines.size()>1){
                for(String line : lines){                    
                    String[] data = StringUtils.split(line, ";");
                    try {
                        fnStatRepository.save(new FirstNameStat(data[1], getSexe(Integer.parseInt(data[0])), Integer.parseInt(data[2]), Long.parseLong(data[3])));
                    } catch (Exception e) {
                        log.warn("this line can't be saved {} msg={}", line, e.getMessage());
                    }                       
                }
            }else{
                log.warn("the given inputstream does not contains more than one line");
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    
    private FirstNameStat.Sexe getSexe(int i){
        if(i == 1){
            return FirstNameStat.Sexe.M;
        }else{
            return FirstNameStat.Sexe.F ;
        }
    }
    
}
