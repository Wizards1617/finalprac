package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Введите модель")
    @Size(min=2, message = "Модель не может быть меньше 2 букв")
    private String modelCar;
    @NotBlank(message = "Введите серию")
    @Size(min=2, message = "Серия не может быть меньше 2 букв")
    private String SeriesCar;

    @NotBlank(message = "Укажите ссылку на фото")
    private String UrlPhoto;




    @ManyToMany
    @JoinTable(
            name = "Car_Diller",
            joinColumns = @JoinColumn(name = "diller_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Diller> dillers;

    @OneToMany(mappedBy = "car")
    private List<Order> orders;

    public Car(Long Id, String modelCar, String seriesCar, String urlPhoto) {
        this.Id = Id;
        this.modelCar = modelCar;
        this.SeriesCar = seriesCar;
        this.UrlPhoto = urlPhoto;
    }

    public Car() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getSeriesCar() {
        return SeriesCar;
    }

    public void setSeriesCar(String seriesCar) {
        SeriesCar = seriesCar;
    }

    public String getUrlPhoto() {
        return UrlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        UrlPhoto = urlPhoto;
    }

    public List<Diller> getDillers() {
        return dillers;
    }

    public void setDillers(List<Diller> dillers) {
        this.dillers = dillers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
