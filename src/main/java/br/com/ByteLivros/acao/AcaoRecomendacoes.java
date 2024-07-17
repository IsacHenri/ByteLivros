package br.com.ByteLivros.acao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class AcaoRecomendacoes implements IAcao {

    @Override
    public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        // Substitua pelo seu token de API do OpenAI
        String apiKey = "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";
        String model = "text-davinci-002";
        String url = "https://api.openai.com/v1/engines/" + model + "/completions";

        // Construindo a lista de mensagens
        JSONArray messages = new JSONArray();

        // Obtendo o prompt do parâmetro da requisição
        String prompt = "faça pra mim um resumo sobre windows"; // Você pode substituir pelo prompt desejado

        // Adicionando a mensagem do usuário à lista de mensagens
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.put(userMessage);

        // Enviando requisição para o OpenAI
        HttpURLConnection connection = null;
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", model);
            requestBody.put("messages", messages);
            requestBody.put("max_tokens", 150);

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(requestBody.toString());
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder responseBody = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBody.append(line);
                    }
                }

                JSONObject jsonResponse = new JSONObject(responseBody.toString());
                JSONArray choices = jsonResponse.getJSONArray("choices");

                if (choices.length() > 0) {
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    String content = message.getString("content");
                    response.getWriter().write(content);
                    System.out.println(content);
                } else {
                    response.getWriter().write("No response from the API");
                    System.out.println("No response from the API");
                }
            } else {
                response.getWriter().write("Error making HTTP request: " + responseCode);
                System.out.println("Error making HTTP request: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write("Error making HTTP request: " + e.getMessage());
                System.out.println("Error making HTTP request: " + e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return "forward:paginaInicial.jsp";
    }
}