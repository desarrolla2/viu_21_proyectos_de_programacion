package com.viu.giin21.controller;

import com.viu.giin21.service.JobService;
import com.viu.giin21.service.UserService;
import com.viu.giin21.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * This controller is responsible to handle request and responses related dashboard
 * and jobs
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private JobService jobService;

    /**
     * retrieve dashboard
     *
     * @return ModelAndView dashboard
     */
    @GetMapping("/")
    public ModelAndView index_GET() {
        HashMap<String, Integer> data = prepareData();

        return new ModelAndView("default/index", "data", data);
    }

    private HashMap<String, Integer> prepareData() {
        HashMap<String, Integer> data = new HashMap<String, Integer>();
        long number_of_users = userService.count();
        long number_of_workplaces = workplaceService.count();
        long number_of_jobs = jobService.count();
        long number_of_jobs_not_assigned = jobService.findByWorkplaceAndFinalized(null, false).stream().count();
        long number_of_jobs_not_finalized = jobService.findByFinalized(false).stream().count();


        data.put("number_of_users", new Integer((int) number_of_users));
        data.put("number_of_workplaces", new Integer((int) number_of_workplaces));
        data.put("number_of_jobs", new Integer((int) number_of_jobs));
        data.put("number_of_jobs_not_assigned", new Integer((int) number_of_jobs_not_assigned));
        data.put("number_of_jobs_not_finalized", new Integer((int) number_of_jobs_not_finalized));
        data.put("number_of_jobs_not_finalized_1", new Integer(1));

        return data;
    }
}
