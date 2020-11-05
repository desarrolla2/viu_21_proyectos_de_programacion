package com.viu.giin21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This DTO is related to entity Workplace
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Data
@AllArgsConstructor
public class WorkplaceDTO {

    private Integer id;

    @NotNull
    private Integer owner;

    private String ownerUsername;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer processability;

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer maximumQueueSize;
    private Integer elapsedLoadTime;
    private Integer elapsedUnloadTime;

    /**
     * No arguments constructor
     */
    public WorkplaceDTO() {
        this.elapsedLoadTime = 0;
        this.elapsedUnloadTime = 0;
    }

    /**
     * Constructor
     *
     * @param id               primary key
     * @param owner            user that admin this workplace
     * @param name             name of workplace
     * @param processability   amount of work per second
     * @param maximumQueueSize maximum jobs in this workplace
     */
    public WorkplaceDTO(Integer id, Integer owner, String name, Integer processability, Integer maximumQueueSize) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.processability = processability;
        this.maximumQueueSize = maximumQueueSize;
        this.elapsedLoadTime = 0;
        this.elapsedUnloadTime = 0;
    }
}
