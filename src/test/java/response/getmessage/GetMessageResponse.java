package response.getmessage;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GetMessageResponse {

	@JsonProperty("data")
	public Data data;

	public Data getData(){
		return data;
	}

}