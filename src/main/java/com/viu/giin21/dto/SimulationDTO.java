package com.viu.giin21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This DTO is not related to any model and contains the amount of time in seconds to simulate.
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Data
@AllArgsConstructor
public class SimulationDTO {

    @NotNull
    @Min(1)
    @Max(1000000)
    private Integer seconds;
}
