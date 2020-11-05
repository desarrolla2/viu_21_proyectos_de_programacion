package com.viu.giin21.controller;

import com.viu.giin21.dto.WorkplaceDTO;
import com.viu.giin21.service.UserService;
import com.viu.giin21.service.WorkplaceService;
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
 * This controller is responsible to handle request and responses related to manage workplaces
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/workplace")
public class WorkplaceController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkplaceService workplaceService;

    /**
     * retrieve workplaces list
     *
     * @return ModelAndView with workplaces list
     */
    @GetMapping
    public ModelAndView index_GET() {
        List<WorkplaceDTO> workplaces = workplaceService.findAll();

        return new ModelAndView("workplace/index", "workplaces", workplaces);
    }

    /**
     * retrieve form for create workplace
     *
     * @return ModelAndView with form for create workplace
     */
    @GetMapping("/create")
    public ModelAndView create_GET() {
        WorkplaceDTO workplace = new WorkplaceDTO(null, null, "", 1, 1);
        Map<String, Object> model = new HashMap<>();
        model.put("workplace", workplace);
        model.put("users", userService.findAll());

        return new ModelAndView("workplace/create", model);
    }

    /**
     * handle request POST for workplace creation
     *
     * @param workplace updated DTO of workplace
     * @param result    form errors
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/create")
    public ModelAndView create_POST(@Valid WorkplaceDTO workplace, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> model = new HashMap<>();
            model.put("workplace", workplace);
            model.put("users", userService.findAll());
            model.put("errors", true);

            return new ModelAndView("workplace/create", model);
        }
        workplaceService.save(workplace);
        return new ModelAndView("redirect:/workplace");
    }

    /**
     * retrieve form for edit workplace
     *
     * @return ModelAndView with form for edit workplace
     */
    @GetMapping("/{id}/edit")
    public ModelAndView edit_GET(@PathVariable Integer id) {
        Map<String, Object> model = new HashMap<>();
        model.put("workplace", workplaceService.get(id));
        model.put("users", userService.findAll());
        return new ModelAndView("workplace/edit", model);
    }

    /**
     * handle request POST for edit workplace
     *
     * @param id        workplace id
     * @param workplace updated DTO of workplace
     * @param result    form errors
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/edit")
    public ModelAndView edit_POST(@PathVariable Integer id, @Valid WorkplaceDTO workplace, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> model = new HashMap<>();
            model.put("workplace", workplace);
            model.put("users", userService.findAll());
            model.put("errors", true);

            return new ModelAndView("workplace/create", model);
        }

        workplace.setId(id);
        workplaceService.save(workplace);
        return new ModelAndView("redirect:/workplace");
    }

    /**
     * retrieve form for delete workplace
     *
     * @param id workplace id
     * @return ModelAndView with form for delete workplace
     */
    @GetMapping("/{id}/delete")
    public ModelAndView delete_GET(@PathVariable Integer id) {
        WorkplaceDTO workplace = workplaceService.get(id);
        return new ModelAndView("workplace/delete", "workplace", workplace);
    }


    /**
     * handle request POST for delete workplace
     *
     * @param id workplace id
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/delete")
    public ModelAndView delete_POST(@PathVariable Integer id) {
        workplaceService.delete(id);
        return new ModelAndView("redirect:/workplace");
    }
}
