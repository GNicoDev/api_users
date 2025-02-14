package com.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    @Id
    @Column(nullable = false,length = 20, unique = true)
    private String userName;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(unique = true,nullable = false,length = 30)
    private String email;

    @Column(nullable = true)
    private Boolean locked = false;

    @Column(nullable = true)
    private Boolean disabled = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
