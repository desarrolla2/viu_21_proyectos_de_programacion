package com.viu.giin21.controller;

import com.viu.giin21.dto.JobDTO;
import com.viu.giin21.dto.WorkplaceDTO;
import com.viu.giin21.service.JobService;
import com.viu.giin21.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This controller is responsible to handle request and responses related to reset and repopulate database workplaces
 * and jobs
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private JobService jobService;

    @Autowired
    private WorkplaceService workplaceService;

    /**
     * retrieve form for clean database
     *
     * @return ModelAndView with form for clean database
     */
    @GetMapping("/clean")
    public ModelAndView clean_GET() {
        return new ModelAndView("database/clean");
    }

    /**
     * handle request POST for clean database
     *
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/clean")
    public ModelAndView clean_POST() {
        List<JobDTO> jobs = jobService.findAll();
        for (JobDTO job : jobs) {
            jobService.delete(job.getId());
        }

        List<WorkplaceDTO> workplaces = workplaceService.findAll();
        for (WorkplaceDTO workplace : workplaces) {
            workplaceService.delete(workplace.getId());
        }

        return new ModelAndView("redirect:/");
    }

    /**
     * retrieve form for populate database
     *
     * @return ModelAndView with form for populate database
     */
    @GetMapping("/populate")
    public ModelAndView populate_GET() {
        return new ModelAndView("database/populate");
    }

    /**
     * handle request POST for populate database
     *
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/populate")
    public ModelAndView populate_POST() {
        List<WorkplaceDTO> workplaces = new ArrayList<WorkplaceDTO>();
        List<JobDTO> jobs = new ArrayList<JobDTO>();

        int processability1 = 442010;
        int processability2 = 148600;
        int processability3 = 94640;

        workplaces.add(new WorkplaceDTO(null, 1, "Fugaku", processability1, 5));
        workplaces.add(new WorkplaceDTO(null, 1, "Summit", processability2, 4));
        workplaces.add(new WorkplaceDTO(null, 1, "Sierra", processability3, 3));
        for (WorkplaceDTO workplace : workplaces) {
            workplaceService.save(workplace);
        }

        jobs.add(new JobDTO(null, 1, null, "Ciencia #1", processability1 * 4 * 10));
        jobs.add(new JobDTO(null, 1, null, "Ciencia #2", processability1 * 3 * 10));
        jobs.add(new JobDTO(null, 1, null, "Ciencia #3", processability1 * 2 * 10));
        jobs.add(new JobDTO(null, 1, null, "Materiales #1", processability2 * 2 * 10));
        jobs.add(new JobDTO(null, 1, null, "Materiales #2", processability2 * 3 * 10));
        jobs.add(new JobDTO(null, 1, null, "Materiales #3", processability2 * 4 * 10));
        jobs.add(new JobDTO(null, 1, null, "Tiempo #1", processability3 * 4 * 10));
        jobs.add(new JobDTO(null, 1, null, "Tiempo #2", processability3 * 3 * 10));
        jobs.add(new JobDTO(null, 1, null, "Tiempo #3", processability3 * 2 * 10));
        jobs.add(new JobDTO(null, 1, null, "Proteinas #1", processability1 * 2 * 10));
        jobs.add(new JobDTO(null, 1, null, "Proteinas #2", processability1 * 3 * 10));
        jobs.add(new JobDTO(null, 1, null, "Proteinas #3", processability1 * 4 * 10));
        jobs.add(new JobDTO(null, 1, null, "Espacio #1", processability2 * 4 * 10));
        jobs.add(new JobDTO(null, 1, null, "Espacio #2", processability2 * 3 * 10));
        jobs.add(new JobDTO(null, 1, null, "Espacio #3", processability2 * 2 * 10));
        Collections.shuffle(jobs);

        for (JobDTO job : jobs) {
            jobService.save(job);
        }

        return new ModelAndView("redirect:/");
    }
}
