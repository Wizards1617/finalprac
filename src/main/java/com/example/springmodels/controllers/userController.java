package com.example.springmodels.controllers;

import com.example.springmodels.models.Car;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.models.roleEnum;
import com.example.springmodels.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAnyAuthority('USER','ADMIN','MANAGER')")
public class userController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/car")
    public String carMain(Model model)
    {
        Iterable<Car> car = carRepository.findAll();
        model.addAttribute("car", car);
        return "car-main";
    }
}
