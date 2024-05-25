package com.userSer.modal;

import com.userSer.dto.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_micro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_about")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
