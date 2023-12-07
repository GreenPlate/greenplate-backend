package dk.kea.project.repository;

import dk.kea.project.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Integer> {
	List<Offer> findAllByRequest_Id(int requestId);
	Offer findAllById(Integer id);
	@Query("SELECT o.id, o.discount, o.newPrice, o.originalPrice, o.percentDiscount, o.product.ean, o.request.id, o.product.description " +
			"FROM Offer o JOIN o.product " +
			"ORDER BY o.id ASC")
	List<Object[]> getAllOfferDetailsWithProductDescription();
}
