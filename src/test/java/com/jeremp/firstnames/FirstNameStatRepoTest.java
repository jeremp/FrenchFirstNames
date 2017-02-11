package com.jeremp.firstnames;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import com.jeremp.firstnames.services.DataLoaderService;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 *
 * @author jeremp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstNameStatRepoTest {

    @Autowired
    private FirstNameStatRepository fnStatRepository;
    
    @Autowired
    private DataLoaderService dataLoaderService ;

    @Before
    public void init() {
        fnStatRepository.save(
                Arrays.asList(
                        new FirstNameStat("JEREMY", FirstNameStat.Sexe.M, 1983, 1526),
                        new FirstNameStat("ANTHONY", FirstNameStat.Sexe.M, 1983, 5236),
                        new FirstNameStat("ROBIN", FirstNameStat.Sexe.M, 2012, 2200)
                )
        );
    }

    @Test
    public void testCount() {
        Assert.isTrue(fnStatRepository.count()>3, "The DB should contains at least 3 entries");
    }

    @Test
    public void testByFirstName() throws SQLException{
        List<String> distincNames = dataLoaderService.findDistinctNames("JERE");
        Assert.isTrue(new HashSet<>(distincNames).size()==distincNames.size());
    }
}
