package com.jeremp.firstnames.services;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;


/**
 *
 * @author jeremp
 */
@Service
public class DataLoaderService {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderService.class);
    
    private static final File DATA_FILE = new File("E:\\telechargements\\nat2015_txt (1)\\nat2015.txt");
    
    @Autowired
    private FirstNameStatRepository fnStatRepository ;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    public void init(){
        if(DATA_FILE.exists() && DATA_FILE.length()>0){
            try {
                loadData(new FileInputStream(DATA_FILE));
            } catch (IOException ex) {
                log.error("Erreur de chargement des données", ex);
            }
        }
    }
    
    public void loadData(InputStream is) throws IOException{
        try {
            long a = System.currentTimeMillis();
            List<String> lines = IOUtils.readLines(is, "UTF-8");
            long inserted = 0 ;
            if(lines.size()>1){
                for(String line : lines){
                    if(StringUtils.contains(line, "XXXX")==false){
                        String[] data = StringUtils.split(line, ";");
                        try {
                            fnStatRepository.save(new FirstNameStat(data[1], getSexe(Integer.parseInt(data[0])), Integer.parseInt(data[2]), Long.parseLong(data[3])));
                            inserted++ ;
                        } catch (Exception e) {
                            log.warn("this line can't be saved {} msg={}", line, e.getMessage());
                        }
                    }
                }
            long b = System.currentTimeMillis();                
            log.info("{} elements loaded in {}", inserted, Duration.ofMillis((b-a)));
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
    
    public List<String> findDistinctNames(String name) throws SQLException{
        String query = "select distinct first_name from first_name_stat where first_name like ?";
        List<String> result = jdbcTemplate.queryForList(query, new String[]{name+"%"}, String.class);
        return result ;
    }
    
}
