package dk.kea.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a chat recipe request.
 * <p>
 * This class is used to transfer data related to a chat recipe request.
 * It includes information such as the model, a list of messages, temperature, max tokens,
 * top p, frequency penalty, and presence penalty.
 * </p>
 * <p>
 * The inner static class {@link Message} represents a chat message with a role and content.
 * </p>
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ChatRecipeRequest {

	/**
	 * The model associated with the chat recipe request.
	 */
	private String model;

	/**
	 * The list of messages in the chat recipe request.
	 */
	private List<Message> messages = new ArrayList<>();

	/**
	 * The temperature parameter for the chat recipe request.
	 */
	private double temperature;

	/**
	 * The max tokens parameter for the chat recipe request.
	 */
	private int max_tokens;

	/**
	 * The top p parameter for the chat recipe request.
	 */
	private double top_p;

	/**
	 * The frequency penalty parameter for the chat recipe request.
	 */
	private double frequency_penalty;

	/**
	 * The presence penalty parameter for the chat recipe request.
	 */
	private double presence_penalty;

	/**
	 * Inner static class representing a chat message.
	 */
	@Getter
	@Setter
	public static class Message {

		/**
		 * The role associated with the chat message.
		 */
		private String role;

		/**
		 * The content of the chat message.
		 */
		private String content;

		/**
		 * Constructs a {@code Message} object with the specified role and content.
		 *
		 * @param role    The role of the chat message.
		 * @param content The content of the chat message.
		 */
		public Message(String role, String content) {
			this.role = role;
			this.content = content;
		}
	}
}
