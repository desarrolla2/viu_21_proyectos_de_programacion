package com.viu.giin21.controller;

import com.viu.giin21.dto.JobDTO;
import com.viu.giin21.service.AllocateService;
import com.viu.giin21.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * This controller is responsible to handle request and responses related to allocate jobs in workplace
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/job/allocate")
public class AllocateController {

    @Autowired
    private JobService jobService;

    @Autowired
    private AllocateService allocateService;

    /**
     * retrieve form for allocate jobs
     *
     * @return ModelAndView with form for allocate jobs
     */
    @GetMapping
    public ModelAndView allocate_GET() {
        List<JobDTO> jobs = jobService.findByWorkplaceAndFinalized(null, false);
        return new ModelAndView("allocate/index", "jobs", jobs);
    }

    /**
     * handle request POST for allocate jobs
     *
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping
    public ModelAndView allocate_POST() {
        List<JobDTO> jobs = jobService.findByWorkplaceAndFinalized(null, false);
        for (JobDTO job : jobs) {
            allocateService.allocate(job);
        }
        return new ModelAndView("redirect:/");
    }
}
