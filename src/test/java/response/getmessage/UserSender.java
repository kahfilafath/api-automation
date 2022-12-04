package response.getmessage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSender{

	@JsonProperty("user_picture")
	private Object userPicture;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("sugar_id")
	private String sugarId;
}