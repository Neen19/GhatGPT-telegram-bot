package ru.sarmosov.gpt.entity;


import com.google.gson.annotations.SerializedName;

public class Content {
    private String role;
    @SerializedName("content")
    private String answer;

    public Content(String content) {
        this.role = "user";
        this.answer = content;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Content{" +
                "role='" + role + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
