package com.example.springmodels.controllers;

import com.example.springmodels.models.*;
import com.example.springmodels.repos.CarRepository;
import com.example.springmodels.repos.DillerRepository;
import com.example.springmodels.repos.FeedbackRepository;
import com.example.springmodels.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class managerController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DillerRepository dillerRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private com.example.springmodels.repos.userRepository userRepository;

    @GetMapping("/car")
    public String carMain(Model model)
    {
        Iterable<Car> car = carRepository.findAll();
        model.addAttribute("car", car);
        return "car-main";
    }

    @GetMapping("/car/add")
    public String carAdd(Model model) {
        model.addAttribute("car", new Car());
        return "car-add";
    }

    @PostMapping("/car/add")
    public String carPostAdd(@ModelAttribute("car") @Valid Car car,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "car-add";
        }
        carRepository.save(car);
        return "redirect:/manager/car"; // Redirect to the author details list page
    }

    @GetMapping("/car/{id}/edit")
    public  String carDetails(@PathVariable(value = "id") long id, Model model)
    {
        Car res1 = carRepository.findById(id).orElseThrow();
        model.addAttribute("car",res1);
        return "car-edit";
    }


    @PostMapping ("/car/{id}/edit")
    public  String carUpdate(@ModelAttribute("car") @Valid Car car,
                                       BindingResult bindingResult,
                                       @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "car-edit";
        carRepository.save(car);
        return "redirect:/manager/car";
    }

    @GetMapping("/car/{id}/remove")
    public  String carDelete(@PathVariable(value = "id") long id, Model model)
    {
        Car authorDetails = carRepository.findById(id).orElseThrow();
        carRepository.delete(authorDetails);
        return "redirect:/manager/car";
    }


    /*-------------------------------------------------------------------------------------------*/

    @GetMapping("/diller")
    public String dillerMain(Model model)
    {
        Iterable<Diller> diller = dillerRepository.findAll();
        model.addAttribute("diller", diller);
        return "diller-main";
    }

    @GetMapping("/diller/add")
    public String dillerAdd(Model model) {
        model.addAttribute("diller", new Diller());
        return "diller-add";
    }

    @PostMapping("/diller/add")
    public String dillerPostAdd(@ModelAttribute("diller") @Valid Diller diller,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "diller-add";
        }
        dillerRepository.save(diller);
        return "redirect:/manager/diller"; // Redirect to the author details list page
    }

    @GetMapping("/diller/{id}/edit")
    public  String dillerDetails(@PathVariable(value = "id") long id, Model model)
    {
        Diller res2 = dillerRepository.findById(id).orElseThrow();
        model.addAttribute("diller",res2);
        return "diller-edit";
    }


    @PostMapping ("/diller/{id}/edit")
    public  String dillerUpdate(@ModelAttribute("diller") @Valid Diller diller,
                             BindingResult bindingResult,
                             @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "diller-edit";
        dillerRepository.save(diller);
        return "redirect:/manager/diller";
    }

    @GetMapping("/diller/{id}/remove")
    public  String dillerDelete(@PathVariable(value = "id") long id, Model model)
    {
        Diller diller = dillerRepository.findById(id).orElseThrow();
        dillerRepository.delete(diller);
        return "redirect:/manager/diller";
    }

    /*-------------------------------------------------------------------------------------------*/

    @GetMapping("/feedback")
    public String feedbackMain(Model model)
    {
        Iterable<Feedback> feedback = feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedback);
        return "feedback-main";
    }

    @GetMapping("/feedback/add")
    public String feedbackAdd(@ModelAttribute("feedback") Feedback feedback, Model feedbackr)
    {
        Iterable<modelUser> feedbackDetails = userRepository.findAll();
        feedbackr.addAttribute("modelUser", feedbackDetails);
        return "feedback-add";
    }

    @PostMapping("/feedback/add")
    public String feedbackPostAdd(@ModelAttribute("feedback") @Valid Feedback feedback, BindingResult bindingResult,
                                   @RequestParam String username, Model feedbackr)
    {
        if(bindingResult.hasErrors()) {
            Iterable<modelUser> feedbackDetails = userRepository.findAll();
            feedbackr.addAttribute("user", feedbackDetails);
            return "feedback-add";
        }
        feedback.setModelUser(userRepository.findByUsername(username));
        feedbackRepository.save(feedback);
        return "redirect:/manager/feedback";
    }

    @GetMapping("/feedback/{id}/edit")
    public  String feedbackDetails(@PathVariable(value = "id") long id, Model model, Model feedbackr)
    {
        Iterable<modelUser> feedbackDetails = userRepository.findAll();
        feedbackr.addAttribute("user", feedbackDetails);
        Feedback res3 = feedbackRepository.findById(id).orElseThrow();
        model.addAttribute("feedback",res3);
        return "feedback-edit";
    }

    @PostMapping ("/feedback/{id}/edit")
    public  String feedbackUpdate(@ModelAttribute("feedback") @Valid Feedback feedback,
                                   BindingResult bindingResult,
                                   @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "feedback-edit";
        feedbackRepository.save(feedback);
        return "/feedback-main";
    }

    @GetMapping("/feedback/{id}/remove")
    public  String feedbackDelete(@PathVariable(value = "id") long id, Model model)
    {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow();
        feedbackRepository.delete(feedback);
        return "redirect:/manager/feedback";
    }


    /*-------------------------------------------------------------------------------------------*/

    @GetMapping("/order")
    public String orderMain(Model model)
    {
        Iterable<Order> order = orderRepository.findAll();
        model.addAttribute("orders", order);
        return "order-main";
    }

    @GetMapping("/order/add")
    public String orderAdd(@ModelAttribute("order") Order order, Model users, Model car)
    {
        Iterable<modelUser> orderDetails = userRepository.findAll();

        Iterable<Car> cars = carRepository.findAll();
        users.addAttribute("modelUser", orderDetails);
        car.addAttribute("modelCar", cars);
        return "order-add";
    }

    @PostMapping("/order/add")
    public String orderPostAdd(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult,
                                  @RequestParam String username, Model orderr, @RequestParam String modelCar, Model carr )
    {
        if(bindingResult.hasErrors()) {
            Iterable<modelUser> modelUsers = userRepository.findAll();
            orderr.addAttribute("user", modelUsers);
            Iterable<Car> cars = carRepository.findAll();
            carr.addAttribute("car", cars);
            return "order-add";
        }

    /*    order.setModelUser(userRepository.findByUsername(username));
        order.setCar(carRepository.findByModelCar(modelCar));*/
        orderRepository.save(order);
        return "redirect:/manager/order";
    }

    @GetMapping("/order/{id}/edit")
    public  String orderDetails(@PathVariable(value = "id") long id, Model model, Model orderr)
    {
        Iterable<modelUser> orderDetails = userRepository.findAll();
        orderr.addAttribute("user", orderDetails);
        Order res3 = orderRepository.findById(id).orElseThrow();
        model.addAttribute("order",res3);
        return "order-edit";
    }

    @PostMapping ("/order/{id}/edit")
    public  String orderUpdate(@ModelAttribute("order") @Valid Order order,
                                  BindingResult bindingResult,
                                  @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "order-edit";
        orderRepository.save(order);
        return "/order-main";
    }

    @GetMapping("/order/{id}/remove")
    public  String orderDelete(@PathVariable(value = "id") long id, Model model)
    {
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/manager/order";
    }
}