package response.getmessage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatItem{

	@JsonProperty("user_sender")
	private UserSender userSender;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("user_receiver")
	private UserReceiver userReceiver;

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private String type;

	public String getId(){
		return id;
	}

	public String getMessage(){
		return message;
	}
	public String getType(){
		return type;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public UserSender getUserSender(){
		return userSender;
	}

	public UserReceiver getUserReceiver(){
		return userReceiver;
	}

}