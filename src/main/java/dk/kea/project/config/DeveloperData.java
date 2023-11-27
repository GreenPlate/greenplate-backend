package dk.kea.project.config;

import dk.kea.project.entity.Recipe;
import dk.kea.project.entity.User;
import dk.kea.project.repository.RecipeRepository;
import dk.kea.project.repository.UserRepository;
import dk.kea.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserRepository userRepository;
    RecipeRepository recipeRepository;

    public DeveloperData(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

    User admin1 = new User("admin", "admin@admin", "password123", "firstName", "lastName");
    admin1.addRole(Role.ADMIN);
    userRepository.save(admin1);

    User user1 = new User("user", "user@user", "password123", "firstName", "lastName");
    user1.addRole(Role.USER);
    userRepository.save(user1);

    Recipe recipe = new Recipe("Ostehapser", "Ostehapser er en lækker snack", "Ost, mel, æg, rasp", user1);
    recipeRepository.save(recipe);
    }
}
