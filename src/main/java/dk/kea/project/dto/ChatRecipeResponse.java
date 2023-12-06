package dk.kea.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a chat recipe response.
 * <p>
 * This class is used to transfer data related to a chat recipe response.
 * It includes information such as the ID, object, created timestamp, model,
 * a list of choices, and usage statistics.
 * </p>
 * <p>
 * The inner static classes {@link Choice}, {@link Message}, and {@link Usage}
 * represent choices made, chat messages, and usage statistics respectively.
 * </p>
 *
 * @see ChatRecipeRequest
 */
@Getter
@Setter
public class ChatRecipeResponse {

	/**
	 * The ID associated with the chat recipe response.
	 */
	private String id;

	/**
	 * The object type of the chat recipe response.
	 */
	private String object;

	/**
	 * The timestamp when the chat recipe response was created.
	 */
	private long created;

	/**
	 * The model associated with the chat recipe response.
	 */
	private String model;

	/**
	 * The list of choices made in the chat recipe response.
	 */
	private List<Choice> choices;

	/**
	 * The usage statistics associated with the chat recipe response.
	 */
	private Usage usage;

	/**
	 * Inner static class representing a choice made in the chat recipe response.
	 */
	@Getter
	@Setter
	public static class Choice {

		/**
		 * The index of the choice.
		 */
		private int index;

		/**
		 * The chat message associated with the choice.
		 */
		private Message message;

		/**
		 * The finish reason for the choice.
		 */
		private String finish_reason;
	}

	/**
	 * Inner static class representing a chat message in the chat recipe response.
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
	}

	/**
	 * Inner static class representing usage statistics in the chat recipe response.
	 */
	@Getter
	@Setter
	public static class Usage {

		/**
		 * The number of tokens used for prompts.
		 */
		private int prompt_tokens;

		/**
		 * The number of tokens used for completions.
		 */
		private int completion_tokens;

		/**
		 * The total number of tokens used.
		 */
		private int total_tokens;
	}
}
