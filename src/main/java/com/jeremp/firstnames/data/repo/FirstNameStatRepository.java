
package com.jeremp.firstnames.data.repo;

import com.jeremp.firstnames.data.entities.FirstNameStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jeremp
 */
@Repository
public interface FirstNameStatRepository extends CrudRepository<FirstNameStat, Long>{
    
}
