package ru.sarmosov.gpt.entity;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class RequestBody {

    @SerializedName("model")
    String model;
    @SerializedName("messages")
    ArrayList<Content> content = new ArrayList<>();

    public RequestBody(String message, String model) {
        this.model = model;
        content.add(new Content(message));
    }
}
