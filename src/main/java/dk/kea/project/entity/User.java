package dk.kea.project.entity;

import dk.kea.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends UserWithRoles {
    @Column(nullable = false, length = 55)
    private String firstName;
    @Column(nullable = false, length = 55)
    private String lastName;
    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();
    public User(String username, String email, String password, String firstName, String lastName){
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
