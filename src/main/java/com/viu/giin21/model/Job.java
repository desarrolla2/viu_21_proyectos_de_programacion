package com.viu.giin21.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/**
 * This entity represent a Job that will be executed in any workplace.
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Entity
@Table(name = "job")
@AllArgsConstructor
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer owner;

    private Integer workplace;

    private String name;

    private Integer executed;

    private Integer size;

    private Boolean started;

    private Boolean finalized;

    /**
     * No arguments constructor
     */
    public Job() {
    }

    /**
     * Constructor
     *
     * @param id        primary key
     * @param owner     user that admin this job
     * @param workplace assigned workplace
     * @param name      name of job
     * @param size      amount of work necessary to complete
     */
    public Job(Integer id, Integer owner, Integer workplace, String name, Integer size) {
        this.id = id;
        this.owner = owner;
        this.workplace = workplace;
        this.name = name;
        this.size = size;
        this.executed = 0;
        this.started = false;
        this.finalized = false;
    }

    /**
     * @param executed set amount of work executed
     */
    public void setExecuted(Integer executed) {
        this.executed = executed;
        if (this.executed > this.size) {
            this.executed = this.size;
        }
    }
}
