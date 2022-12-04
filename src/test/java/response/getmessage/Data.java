package response.getmessage;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("chat")
	public List<ChatItem> chat;

	public List<ChatItem> getChat(){
		return chat;
	}

}