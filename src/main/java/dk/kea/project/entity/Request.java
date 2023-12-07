package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity class representing a request.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about requests, including the associated store, creation timestamp, and expiration timestamp.
 * </p>
 *
 * @see Store
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {

	/**
	 * The unique identifier for the request.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * The store associated with the request.
	 */
	@ManyToOne
	@JoinColumn(name = "store_id")
	Store store;

	/**
	 * The timestamp when the request was created.
	 */
	@CreationTimestamp
	LocalDateTime created;

	/**
	 * Constructs a {@code Request} object with the specified store.
	 *
	 * @param store The store associated with the request.
	 */
	public Request(Store store) {
		this.store = store;
	}

	/**
	 * Gets the expiration timestamp for the request, calculated as 15 minutes from the creation timestamp.
	 *
	 * @return The expiration timestamp for the request.
	 */
	public LocalDateTime getExpires() {
		return created.plusMinutes(15);
	}
}
