package dk.kea.project.repository;

import dk.kea.project.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer,Integer> {

}
