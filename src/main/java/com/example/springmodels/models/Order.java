package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Введите дату")
    private String date;

    @ManyToOne
    @JoinColumn(name = "modeluser_id")
    private modelUser modelUser;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Order(Long id, String date, modelUser modelUser, Car car) {
        Id = id;
        this.date = date;
        this.modelUser = modelUser;
        this.car = car;
    }

    public Order() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public com.example.springmodels.models.modelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(com.example.springmodels.models.modelUser modelUser) {
        this.modelUser = modelUser;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}


