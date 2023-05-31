package com.example.animeservice.util;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("passwordResetClient")
public class PasswordResetClient {
    private static final String API_ENDPOINT = "https://api.sendgrid.com/v3/mail/send";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");
    private static final String SENDGRID_API_KEY = "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";

    private final OkHttpClient client = new OkHttpClient();

    public void sendPasswordResetEmail(String email) throws IOException {
        // Формируем тело запроса
        String requestBody = buildRequestBody(email);
        RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, requestBody);

        // Создаем HTTP-заголовки для аутентификации с помощью API-ключа SendGrid
        Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .addHeader("Authorization", "Bearer " + SENDGRID_API_KEY)
                .post(body)
                .build();

        // Отправляем HTTP-запрос
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Email sent successfully");
            } else {
                System.out.println("Failed to send email. Response: " + response.body().string());
            }
        }
    }

    private String buildRequestBody(String email) {
        // Формируем JSON-объект с информацией для отправки письма
        return "{\"personalizations\": [{\"to\": [{\"email\": \"" + email + "\"}]}]," +
                "\"from\": {\"email\": \"lizagoldova13@gmail.com\"}," +
                "\"subject\": \"Password Reset\"," +
                "\"content\": [{\"type\": \"text/plain\", \"value\": \"Please reset your password\"}]}";
    }
}