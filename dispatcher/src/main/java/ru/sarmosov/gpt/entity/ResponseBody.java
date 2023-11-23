package ru.sarmosov.gpt.entity;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;


public class ResponseBody {
    @Override
    public String toString() {
        return "ResponseBody{" +
                "answer=" + answer +
                '}';
    }

    @SerializedName("choices")
    ArrayList<GptAnswer> answer = new ArrayList<>();

    public String getAnswer() {
        return answer.get(0).getAnswer();
    }
}
