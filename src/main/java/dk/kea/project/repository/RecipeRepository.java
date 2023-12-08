package dk.kea.project.repository;

import dk.kea.project.entity.Recipe;
import dk.kea.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByUser(User user);
}
