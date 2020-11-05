package com.viu.giin21.service;

/**
 * This service handle the simulation of time
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
public interface SimulateService {

    /**
     * simulates the passage of time
     *
     * @param seconds to simulate
     * @return
     */
    boolean simulate(Integer seconds);
}
