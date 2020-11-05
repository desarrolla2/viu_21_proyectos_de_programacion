package com.viu.giin21.controller;

import com.viu.giin21.dto.UserDTO;
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
import java.util.List;

/**
 * This controller is responsible to handle request and responses related to manage users
 *
 * @author Daniel González daniel@devtia.com
 * @version 0.1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * retrieve users list
     *
     * @return ModelAndView with users list
     */
    @GetMapping
    public ModelAndView index_GET() {
        List<UserDTO> users = userService.findAll();
        return new ModelAndView("user/index", "users", users);
    }

    /**
     * retrieve form for create user
     *
     * @return ModelAndView with form for create user
     */
    @GetMapping("/create")
    public ModelAndView create_GET() {
        UserDTO user = new UserDTO(null, "", "", "");
        return new ModelAndView("user/create", "user", user);
    }

    /**
     * handle request POST for user creation
     *
     * @param user   updated DTO of user
     * @param result form errors
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/create")
    public ModelAndView create_POST(@Valid UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/user/create");
            modelAndView.addObject("errors", true);
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        userService.save(user);
        return new ModelAndView("redirect:/user");
    }

    /**
     * retrieve form for edit user
     *
     * @return ModelAndView with form for edit user
     */
    @GetMapping("/{id}/edit")
    public ModelAndView edit_GET(@PathVariable Integer id) {
        UserDTO userDto = userService.get(id);
        return new ModelAndView("user/edit", "user", userDto);
    }

    /**
     * handle request POST for edit user
     *
     * @param id     user id
     * @param user   updated DTO of user
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/edit")
    public ModelAndView edit_POST(@PathVariable Integer id, UserDTO user) {
        user.setId(id);
        userService.save(user);
        return new ModelAndView("redirect:/user");
    }

    /**
     * retrieve form for delete user
     *
     * @param id user id
     * @return ModelAndView with form for delete user
     */
    @GetMapping("/{id}/delete")
    public ModelAndView delete_GET(@PathVariable Integer id) {
        UserDTO userDto = userService.get(id);
        return new ModelAndView("user/delete", "user", userDto);
    }

    /**
     * handle request POST for delete user
     *
     * @param id user id
     * @return ModelAndView with form errors or redirection to index
     */
    @PostMapping("/{id}/delete")
    public ModelAndView delete_POST(@PathVariable Integer id) {
        userService.delete(id);
        return new ModelAndView("redirect:/user");
    }
}
