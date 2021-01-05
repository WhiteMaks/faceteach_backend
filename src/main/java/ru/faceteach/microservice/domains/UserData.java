package ru.faceteach.microservice.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("user_data")
public class UserData {
    @Id
    private Long id;
    private String first_name;
    private String second_name;
    private String middle_name;
    private String description;
    private Integer level;
    private Date bird_date;

    private Long user_id;

    public UserData() {

    }

    public UserData(String first_name, String second_name, String middle_name, String description, Integer level, Date bird_date, Long user_id) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.description = description;
        this.level = level;
        this.bird_date = bird_date;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getBird_date() {
        return bird_date;
    }

    public void setBird_date(Date bird_date) {
        this.bird_date = bird_date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
