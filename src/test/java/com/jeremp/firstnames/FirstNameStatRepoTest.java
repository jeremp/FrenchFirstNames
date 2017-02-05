package com.jeremp.firstnames;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import java.util.Arrays;
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
    FirstNameStatRepository fnStatRepository;

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
        Assert.isTrue(fnStatRepository.count()==3, "The DB should contains 3 entries");
    }

}