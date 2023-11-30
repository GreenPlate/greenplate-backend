package dk.kea.project.repository;

import dk.kea.project.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Integer> {

	List<Offer> findAllByRequest_Id();
}
