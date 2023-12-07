package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object (DTO) representing a custom recipe response.
 * <p>
 * This class is used to transfer data related to a custom recipe response.
 * It includes information such as the answer and a list of messages.
 * </p>
 * <p>
 * The class is annotated with {@link JsonInclude} to exclude fields with null values
 * during JSON serialization.
 * </p>
 * <p>
 * The class provides constructors to create instances with different sets of parameters,
 * and it overrides the {@code toString} method for a human-readable representation.
 * </p>
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyRecipe {

	/**
	 * The answer associated with the custom recipe response.
	 */
	private String answer;

	/**
	 * The list of messages associated with the custom recipe response.
	 */
	private List<Map<String, String>> messages;

	/**
	 * Constructs a {@code MyRecipe} object with the specified answer.
	 *
	 * @param answer The answer of the custom recipe response.
	 */
	public MyRecipe(String answer) {
		this.answer = answer;
	}

	/**
	 * Constructs a {@code MyRecipe} object with the specified answer and messages.
	 *
	 * @param answer   The answer of the custom recipe response.
	 * @param messages The list of messages associated with the custom recipe response.
	 */
	public MyRecipe(String answer, List<Map<String, String>> messages) {
		this.answer = answer;
		this.messages = messages;
	}

	/**
	 * Returns a string representation of the {@code MyRecipe} object.
	 *
	 * @return A string representation of the object.
	 */
	@Override
	public String toString() {
		return "MyRecipe{" +
				"answer='" + answer + '\'' +
				'}';
	}
}
