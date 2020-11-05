package com.viu.giin21.controller;

import com.viu.giin21.dto.SimulationDTO;
import com.viu.giin21.service.JobService;
import com.viu.giin21.service.SimulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * This controller is responsible to handle request and responses related to simulate time execution
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/job/simulate")
public class SimulateController {

    @Autowired
    private SimulateService simulateService;

    @Autowired
    private JobService jobService;

    /**
     * retrieve form for simulate time
     *
     * @return ModelAndView with form for allocate jobs
     */
    @GetMapping()
    public ModelAndView index_GET() {
        Map<String, Object> model = new HashMap<>();
        model.put("simulation", new SimulationDTO(1));
        model.put("jobs", jobService.findByFinalized(false));
        return new ModelAndView("simulate/index", model);
    }

    /**
     * handle request POST for simulate time
     *
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping()
    public ModelAndView index_POST(@Valid SimulationDTO simulation) {
        simulateService.simulate(simulation.getSeconds());
        return new ModelAndView("redirect:/job/simulate");
    }
}
