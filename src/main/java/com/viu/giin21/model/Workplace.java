package com.viu.giin21.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * This entity represent a workplace that will execute jobs
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Entity
@Table(name = "workplace")
@AllArgsConstructor
@Data
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer owner;

    private String name;

    private Integer processability;

    private Integer maximumQueueSize;

    private Integer elapsedLoadTime;

    private Integer elapsedUnloadTime;

    /**
     * No arguments constructor
     */
    public Workplace() {
    }

    /**
     * @param id               primary key
     * @param owner            user that admin this job
     * @param name             name of job
     * @param processability   amount of work per second
     * @param maximumQueueSize maximum jobs in this workplace
     */
    public Workplace(Integer id, Integer owner, String name, Integer processability, Integer maximumQueueSize) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.processability = processability;
        this.maximumQueueSize = maximumQueueSize;
        this.elapsedLoadTime = 0;
        this.elapsedUnloadTime = 0;
    }
}
