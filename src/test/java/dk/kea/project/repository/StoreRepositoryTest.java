package dk.kea.project.repository;

import dk.kea.project.entity.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreRepositoryTest {

    @Mock
    StoreRepository storeRepository;

    Store createStore(){
        Store store = new Store("ID-NO-ONE", "Netto Valby", "Netto",
                "2500", "Valby", "Vigerslevvej 1");
        return store;
    }
    @Test
    void testFindAllByZip() {
        Store testStore = createStore();
        when(storeRepository.findAllByZip("2500")).thenReturn(List.of(testStore));
        List<Store> res = storeRepository.findAllByZip("2500");
        assertEquals(1, res.size());
    }

    @Test
    void testFindStoreById() {
        Store testStore = createStore();
        when(storeRepository.findStoreById("ID-NO-ONE")).thenReturn(testStore);
        Store store = storeRepository.findStoreById("ID-NO-ONE");
        assertEquals("Netto Valby", store.getName());
        assertEquals("Netto", store.getBrand());
        assertEquals("2500", store.getZip());
        assertEquals("Valby", store.getCity());
        assertEquals("Vigerslevvej 1", store.getStreet());
    }
}