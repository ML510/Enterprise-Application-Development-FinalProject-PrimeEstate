package com.realestate.management.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionContentPart;
import com.openai.models.chat.completions.ChatCompletionContentPartText;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.realestate.management.Dto.PropertyDto;
import com.realestate.management.service.ModelService;
import com.realestate.management.service.PropertyService;
import com.realestate.management.util.ApiResponse;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final PropertyService propertyService;

    private static final String ENDPOINT = "https://models.github.ai/inference";
    private static final String GITHUB_TOKEN_ENV = "GITHUB_TOKEN";
    private static final String DEFAULT_PROMPT = "hello where is this";
    private static final String SYSTEM_MESSAGE = "You are a chat agent exclusively for a real estate company named Prime State based in UAE. Your role is to provide accurate, clear, and concise information only about Prime State's real estate properties, services, listings, pricing, locations, and relevant details within Dubai.\n\nAlways focus your responses strictly on Dubai real estate topics related to Prime State. If asked about unrelated subjects, politely inform the user that you only provide information about Prime State and its Dubai properties.\n\n# Steps\n\n- Understand the user's query related to real estate in Dubai.\n- Provide factual, up-to-date information about Prime State's listings, services, pricing, locations, and policies.\n- Avoid discussing topics outside Dubai real estate or unrelated matters.\n- If a question is outside your scope, respond politely explaining your limited focus.\n\n# Output Format\n\nRespond in clear, professional, and concise English paragraphs. Use bullet points for enumerations if appropriate. Keep responses user-friendly and informative.";

    @SuppressWarnings("unchecked")
    private String buildPropertyContext() {
        ApiResponse response = propertyService.getAll();
        if (response == null || response.getData() == null) {
            return "No property data available.";
        }

        List<PropertyDto> properties = (List<PropertyDto>) response.getData();

        StringBuilder sb = new StringBuilder();
        sb.append("Available properties:\n");

        for (PropertyDto p : properties) {
            sb.append("- ")
                    .append(p.getName()).append(", ")
                    .append("State: ").append(p.getState()).append(", ")
                    .append("Price: ").append(p.getPrice()).append(", ")
                    .append("Status: ").append(p.getStatus()).append(", ")
                    .append("Beds: ").append(p.getMinBedCount()).append("-").append(p.getMaxBedCount()).append(", ")
                    .append("Sqft: ").append(p.getSqft()).append(", ")
                    .append("URL: ").append(p.getUrl())
                    .append("\n");
        }
        return sb.toString();
    }


    private Map<String, Object> parseJsonString(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private String getGithubToken() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String token = dotenv.get(GITHUB_TOKEN_ENV);
        if (token == null || token.isBlank()) {
            token = System.getenv(GITHUB_TOKEN_ENV);
        }
        return token;
    }

    private String getUserText(String[] args) {
        return args != null && args.length > 0 ? String.join(" ", args) : DEFAULT_PROMPT;
    }

    private String normalizeUserText(String userText) {
        return userText == null || userText.isBlank() ? DEFAULT_PROMPT : userText;
    }

    private ChatCompletionCreateParams createParams(String userText) {
        return ChatCompletionCreateParams.builder()
                .model("openai/gpt-4.1-mini")
                .addSystemMessage(SYSTEM_MESSAGE + "\n\n" + buildPropertyContext())
                .addUserMessageOfArrayOfContentParts(List.of(
                        ChatCompletionContentPart.ofText(
                                ChatCompletionContentPartText.builder().text(userText).build()
                        )
                ))
                .temperature(1d)
                .topP(1d)
                .build();

    }

    public String chat(String userText) {
        String token = getGithubToken();
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Missing GitHub token. Add " + GITHUB_TOKEN_ENV + " to .env or set it as an environment variable.");
        }

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .baseUrl(ENDPOINT)
                .apiKey(token)
                .build();

        ChatCompletion completion = client.chat().completions().create(createParams(normalizeUserText(userText)));
        String response = completion.choices().get(0).message().content().orElse("");
        return parseAiText(response);
    }

    @Override
    public String send(String userText) {
        return chat(userText);
    }

    @Override
    public String enhanceDescription(String description) {
        if (description == null || description.isBlank()) {
            return "";
        }

        String token = getGithubToken();
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Missing GitHub token. Add " + GITHUB_TOKEN_ENV + " to .env or set it as an environment variable.");
        }

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .baseUrl(ENDPOINT)
                .apiKey(token)
                .build();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("openai/gpt-4.1-mini")
                .addSystemMessage("""
                        You are a professional UAE real-estate copywriter.

                        Your task is to improve property descriptions by making them:
                        - Concise
                        - Clear
                        - Persuasive
                        - Factually accurate

                        Do not invent or add any details.

                        Provide the improved property description in a professional and readable style.
                        """)
                .addUserMessage("Enhance this property description in professional English:\n\n" + description)
                .temperature(0.7)
                .topP(1d)
                .build();

        ChatCompletion completion = client.chat().completions().create(params);
        String response = completion.choices().get(0).message().content().orElse("");
        return parseAiText(response);
    }

    public String parseAiText(String content) {
        if (content == null || content.isBlank()) {
            return "";
        }

        Map<String, Object> parsed = parseJsonString(content);
        Object textValue = parsed.get("text");
        return textValue != null ? String.valueOf(textValue) : content;
    }

}
