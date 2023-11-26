package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		int requestId;

		@JoinColumn(name = "storeId")
		String storeId;
		@CreationTimestamp
		LocalDateTime created;

		public LocalDateTime getExpires() {
			return created.plusMinutes(15);
		}
	}
