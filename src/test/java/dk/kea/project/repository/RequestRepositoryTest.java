package dk.kea.project.repository;

import dk.kea.project.entity.Request;
import dk.kea.project.entity.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestRepositoryTest {

    @Mock
    RequestRepository requestRepository;

    Request createRequest(){
        Store store = new Store("ID-NO-ONE", "Netto Valby", "Netto",
                "2500", "Valby", "Vigerslevvej 1");
        Request request = new Request();
        request.setStore(store);
        return request;
    }

    @Test
    void testExistsByStoreId() {
        Request testRequest = createRequest();
        when(requestRepository.existsByStoreId("ID-NO-ONE")).thenReturn(Boolean.TRUE);
        assertTrue(requestRepository.existsByStoreId("ID-NO-ONE"));
    }

    @Test
    void testFindByStoreId() {
        Request testRequest = createRequest();
        when(requestRepository.findByStoreId("ID-NO-ONE")).thenReturn(testRequest);
        Request res = requestRepository.findByStoreId("ID-NO-ONE");
        assertEquals(0, res.getId());
    }
}