
package com.jeremp.firstnames.data.repo;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jeremp
 */
@Repository
public interface FirstNameStatRepository extends CrudRepository<FirstNameStat, Long>{
        
    List<FirstNameStat> findAllByFirstNameOrderByYear(String firstName);
}
