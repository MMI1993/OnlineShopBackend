package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
    @Data
    @Table(name = "users")
    @NoArgsConstructor
    public class User{

        private static final long serialVersionUID = 4887904943282174032L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String email;
        @NaturalId
        private String password;
        @Column
        private String name;
        @Column
        private String phone;
        @Column
        private String address;
        @Column
        private boolean active;
        @Column
        private String role = "ROLE_CUSTOMER";

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnore  // fix bi-direction toString() recursion problem
        private Cart cart;




        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", active=" + active +
                    ", role='" + role + '\'' +
                    '}';
        }

    }

