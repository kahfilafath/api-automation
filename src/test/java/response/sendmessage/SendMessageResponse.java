package response.sendmessage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageResponse{

	@JsonProperty("data")
	public String data;

	public String getData(){
		return data;
	}
}