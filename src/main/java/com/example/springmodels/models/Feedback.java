package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Введите описание")
    @Size(min=2, message = "Описание не может быть меньше 2 букв")
    private String description;
    @NotBlank(message = "Введите оценку")
    private String rate;

    @ManyToOne
    private modelUser modelUser;

    @OneToMany(mappedBy = "modelUser")
    private List<Feedback> feedbacks;

    public Feedback() {

    }

    public Feedback(Long id, String description, String rate, com.example.springmodels.models.modelUser modelUser, List<Feedback> feedbacks) {
        Id = id;
        this.description = description;
        this.rate = rate;
        this.modelUser = modelUser;
        this.feedbacks = feedbacks;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public com.example.springmodels.models.modelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(com.example.springmodels.models.modelUser modelUser) {
        this.modelUser = modelUser;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
