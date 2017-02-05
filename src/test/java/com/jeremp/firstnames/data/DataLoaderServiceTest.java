
package com.jeremp.firstnames.data;

import com.jeremp.firstnames.data.repo.FirstNameStatRepository;
import com.jeremp.firstnames.services.DataLoaderService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jeremp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataLoaderServiceTest {

     @Autowired
     private DataLoaderService dataLoaderService ;
     
     @Autowired
     private FirstNameStatRepository repository ;
     
     @Test
     public void loadDataTest() throws IOException{
         dataLoaderService.loadData(new FileInputStream(new File("E:\\telechargements\\nat2015_txt (1)\\nat2015.txt")));
         System.out.println("TOTAL="+repository.count());
     }
    
}
