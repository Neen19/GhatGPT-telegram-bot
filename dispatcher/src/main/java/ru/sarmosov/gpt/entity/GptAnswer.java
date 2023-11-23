package ru.sarmosov.gpt.entity;

import com.google.gson.annotations.SerializedName;

public class GptAnswer {

    @SerializedName("message")
    private Content message;

    @Override
    public String toString() {
        return "GptAnswer{" +
                "message=" + message +
                '}';
    }

    public String getAnswer() {
        return message.getAnswer();
    }

}
