package dk.kea.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity class representing API usage statistics.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about the usage of an API, including the number of tokens used, associated cost,
 * similar prompts, and the timestamp of when the usage was recorded.
 * </p>
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ApiUsage {

    /**
     * The unique identifier for the API usage record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The number of tokens used in the API request.
     */
    private int tokensUsed;

    /**
     * The cost associated with the API usage.
     */
    private String cost;

    /**
     * Information about the number of similar prompts for the given cost.
     */
    private String similarPrompts;

    /**
     * Constructs an {@code ApiUsage} object with the specified number of tokens used.
     *
     * @param tokensUsed The number of tokens used in the API request.
     */
    public ApiUsage(int tokensUsed) {
        this.tokensUsed = tokensUsed;
        this.cost = "Cost ($0.0015 / 1K tokens) : $" + String.format("%6f", (tokensUsed * 0.0015 / 1000));
        this.similarPrompts = "For 1$, this is the amount of similar requests you can make: "
              + Math.round(1 / (tokensUsed * 0.0015 / 1000));
    }

    /**
     * The timestamp when the API usage record was created.
     */
    @CreationTimestamp
    LocalDateTime created;
}
