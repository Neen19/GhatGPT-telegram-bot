package ru.sarmosov.gpt.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sarmosov.gpt.entity.RequestBody;
import ru.sarmosov.gpt.entity.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Log4j
public class GptService {

    @Value("${gpt.model}")
    private String gptModel;

    @Value("${gpt.url}")
    private String gptUrl;

    @Value("${gpt.auth}")
    private String auth;

    @Value("${gpt.content_type}")
    private String contentType;

    private final Gson converter = new Gson();


    public String gptAnswer(String message) {
        HttpResponse<String> response = genResponse(genRequest(genJsonBody(message)));
        log.debug(converter.fromJson(response.body(), ResponseBody.class));
        return converter.fromJson(response.body(), ResponseBody.class).getAnswer();
    }

    private HttpRequest genRequest(String body) {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(gptUrl))
                    .header("Content-Type", contentType)
                    .header("Authorization", auth)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return request;
    }

    String genJsonBody(String message) {
        log.debug(message);
        return converter.toJson(new RequestBody(message, gptModel));
    }


    HttpResponse<String> genResponse(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
