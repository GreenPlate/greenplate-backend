package dk.kea.project.repository;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OfferRepositoryTest {

    @Mock
    OfferRepository offerRepository;

    Offer createOffer(){
        Request request = new Request();
        Offer offer = new Offer(20.0, 10.0, 10.0, 50.0);
        offer.setRequest(request);
        return offer;
    }

    @Test
    void testFindAllByRequest_Id() {
        Offer testOffer = createOffer();
        when(offerRepository.findAllByRequest_Id(1)).thenReturn(List.of(testOffer));
        List<Offer> res = offerRepository.findAllByRequest_Id(1);
        assertEquals(1, res.size());
    }

    @Test
    void testFindAllById() {
        Offer testOffer = createOffer();
        when(offerRepository.findAllById(1)).thenReturn(testOffer);
        Offer res = offerRepository.findAllById(1);
        assertEquals(20.0, res.getOriginalPrice());
        assertEquals(10.0, res.getNewPrice());
        assertEquals(10.0, res.getDiscount());
        assertEquals(50.0, res.getPercentDiscount());
    }
}