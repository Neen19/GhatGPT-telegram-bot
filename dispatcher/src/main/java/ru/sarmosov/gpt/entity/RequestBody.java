package ru.sarmosov.gpt.entity;


import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;
import ru.sarmosov.gpt.constants.Constants;

import java.util.ArrayList;


public class RequestBody {

    String model;
    @SerializedName("messages")
    ArrayList<Content> content = new ArrayList<>();

    public RequestBody(String message, String model) {
        this.model = model;
        content.add(new Content(message));
    }
}
