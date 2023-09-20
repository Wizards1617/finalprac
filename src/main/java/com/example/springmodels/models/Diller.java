package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "diller")
public class Diller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Введите название")
    @Size(min=2, message = "Название не может быть меньше 2 букв")
    private String nameDiller;
    @NotBlank(message = "Введите график работы")
    @Size(min=4, message = "график работы не может быть меньше 4 букв")
    private String WorkSchedule;

    @NotBlank(message = "Введите адрес")
    @Size(min=2, message = "Адрес не может быть меньше 2 букв")
    private String AdressDiller;

    @ManyToMany(mappedBy = "dillers")
    private List<Car> cars;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNameDiller() {
        return nameDiller;
    }

    public void setNameDiller(String nameDiller) {
        this.nameDiller = nameDiller;
    }

    public String getWorkSchedule() {
        return WorkSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        WorkSchedule = workSchedule;
    }

    public String getAdressDiller() {
        return AdressDiller;
    }

    public void setAdressDiller(String adressDiller) {
        AdressDiller = adressDiller;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
