package com.viu.giin21.controller;

import com.viu.giin21.dto.JobDTO;
import com.viu.giin21.service.JobService;
import com.viu.giin21.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller is responsible to handle request and responses related to manage jobs
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    /**
     * retrieve jobs list
     *
     * @return ModelAndView with jobs list
     */
    @GetMapping
    public ModelAndView index_GET() {
        List<JobDTO> jobs = jobService.findAll();
        return new ModelAndView("job/index", "jobs", jobs);
    }

    /**
     * retrieve form for create job
     *
     * @return ModelAndView with form for create job
     */
    @GetMapping("/create")
    public ModelAndView create_GET() {
        JobDTO job = new JobDTO(null, null, null, "", 1);
        Map<String, Object> model = new HashMap<>();
        model.put("job", job);
        model.put("users", userService.findAll());
        return new ModelAndView("job/create", model);
    }

    /**
     * handle request POST for job creation
     *
     * @param job    updated DTO of job
     * @param result form errors
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/create")
    public ModelAndView create_POST(@Valid JobDTO job, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> model = new HashMap<>();
            model.put("job", job);
            model.put("errors", true);
            model.put("users", userService.findAll());
            return new ModelAndView("job/create", model);
        }
        jobService.save(job);
        return new ModelAndView("redirect:/job");
    }

    /**
     * retrieve form for edit job
     *
     * @return ModelAndView with form for edit job
     */
    @GetMapping("/{id}/edit")
    public ModelAndView edit_GET(@PathVariable Integer id) {
        JobDTO job = jobService.get(id);
        Map<String, Object> model = new HashMap<>();
        model.put("job", job);
        model.put("users", userService.findAll());
        return new ModelAndView("job/edit", model);
    }

    /**
     * handle request POST for edit job
     *
     * @param id  job id
     * @param job updated DTO of job
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/edit")
    public ModelAndView edit_POST(@PathVariable Integer id, @Valid JobDTO job, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> model = new HashMap<>();
            model.put("job", job);
            model.put("errors", true);
            model.put("users", userService.findAll());
            return new ModelAndView("job/edit", model);
        }
        job.setId(id);
        jobService.save(job);
        return new ModelAndView("redirect:/job");
    }

    /**
     * retrieve form for delete job
     *
     * @param id job id
     * @return ModelAndView with form for delete job
     */
    @GetMapping("/{id}/delete")
    public ModelAndView delete_GET(@PathVariable Integer id) {
        JobDTO job = jobService.get(id);
        return new ModelAndView("job/delete", "job", job);
    }

    /**
     * handle request POST for delete job
     *
     * @param id job id
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/delete")
    public ModelAndView delete_POST(@PathVariable Integer id) {
        jobService.delete(id);
        return new ModelAndView("redirect:/job");
    }
}
