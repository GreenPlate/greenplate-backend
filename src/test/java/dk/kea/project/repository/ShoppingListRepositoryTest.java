package dk.kea.project.repository;

import dk.kea.project.entity.ShoppingList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingListRepositoryTest {

    @Mock
    ShoppingListRepository shoppingListRepository;

    ShoppingList createShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(1);
        return shoppingList;
    }

    @Test
    void findAllByUserUsername() {
        ShoppingList shoppingList = createShoppingList();
        when(shoppingListRepository.findAllByUserUsername("username")).thenReturn(List.of(shoppingList));
        assertEquals(1, shoppingListRepository.findAllByUserUsername("username").size());
    }
}