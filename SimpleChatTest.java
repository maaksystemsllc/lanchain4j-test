package maak;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static maak.LLMConstants.*;

public class SimpleChatTest {

    private static void testLLMModel(String modelName) {
        System.out.println("\n\n>>>>>>>Testing Model :" + modelName + "<<<<<<\n");
        ChatLanguageModel chatModel = OllamaChatModel.builder()
                .baseUrl(OLLAMA_BASE_URL)
                .modelName(modelName)
                .logRequests(true)
                .build();

        String answer = chatModel.chat("Provide 3 short bullet points explaining why Java is awesome");
        System.out.println(answer);
    }

    public static void main(String[] args) {

        testLLMModel(LLAMA3_2_MODEL_NAME);
        testLLMModel(MISTRAL_MODEL_NAME);
        testLLMModel(PHI3_MODEL_NAME);
        testLLMModel(GEMMA_MODEL_NAME);
    }
}