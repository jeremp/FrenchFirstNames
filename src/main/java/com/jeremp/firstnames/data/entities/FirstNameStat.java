
package com.jeremp.firstnames.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jeremp
 */
@Entity
public class FirstNameStat implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Sexe gender ;
    private String firstName;    
    private int year ;    
    private long nbTimesGiven ;

    public enum Sexe {M, F}
    
    public FirstNameStat() {
    }

    public FirstNameStat(String firstName, Sexe gender, int year, long nbTimesGiven) {
        this.firstName = firstName;
        this.year = year;
        this.nbTimesGiven = nbTimesGiven;
        this.gender = gender ;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getNbTimesGiven() {
        return nbTimesGiven;
    }

    public void setNbTimesGiven(long nbTimesGiven) {
        this.nbTimesGiven = nbTimesGiven;
    }
    
}
