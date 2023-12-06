package dk.kea.project.repository;

import dk.kea.project.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
    List<ShoppingList> findAllByUserUsername(String username);
}
