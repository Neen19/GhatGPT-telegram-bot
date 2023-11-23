package ru.sarmosov.gpt.entity;

public class Message {
    Content content;
    public String getAnswer() {
        return content.getAnswer();
    }

    @Override
    public String toString() {
        return "Message{" +
                "content=" + content +
                '}';
    }
}
