package com.viu.giin21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This DTO is related to entity JOB
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Data
@AllArgsConstructor
public class JobDTO {

    private Integer id;

    private Integer owner;

    private String ownerUsername;

    private Integer workplace;

    private String workplaceName;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer size;

    @NotNull
    @Min(0)
    @Max(1000000)
    private Integer executed;

    @NotNull
    private Boolean started;

    @NotNull
    private Boolean finalized;

    /**
     * No arguments constructor
     */
    public JobDTO() {
        this.executed = 0;
        this.started = false;
        this.finalized = false;
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
    public JobDTO(Integer id, Integer owner, Integer workplace, String name, Integer size) {
        this.id = id;
        this.owner = owner;
        this.workplace = workplace;
        this.name = name;
        this.executed = 0;
        this.size = size;
        this.started = false;
        this.finalized = false;
    }

    /**
     * @param id        primary key
     * @param owner     user that admin this job
     * @param workplace assigned workplace
     * @param name      name of job
     * @param executed  amount of work previusly executed
     * @param size      amount of work necessary to complete
     * @param started   indicates if the job has started
     * @param finalized indicates if the job has finalized
     */
    public JobDTO(Integer id, Integer owner, Integer workplace, String name, Integer executed, Integer size, Boolean started, Boolean finalized) {
        this.id = id;
        this.owner = owner;
        this.workplace = workplace;
        this.name = name;
        this.size = size;
        this.executed = executed;
        this.started = started;
        this.finalized = finalized;
    }
}
