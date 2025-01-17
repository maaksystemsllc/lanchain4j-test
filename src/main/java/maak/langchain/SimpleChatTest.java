package maak.langchain;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import static maak.LLMConstants.*;

public class SimpleChatTest {

    private static void testLLMModel(String modelName) {

        ChatLanguageModel chatModel = OllamaChatModel.builder()
                .baseUrl(OLLAMA_BASE_URL)
                .modelName(modelName)
                .logRequests(true)
                .build();

        String answer = chatModel.chat("Provide 3 short bullet points explaining why Java is awesome");
        System.out.println(answer);
    }

    public static void main(String[] args) {
        for(String modelName : TEST_MODELS) {
            System.out.println("\n\n>>>>>>>Testing Model :" + modelName + "<<<<<<\n");
            testLLMModel(modelName);
            System.out.println("\n\n>>>>>>>Completed Testing Model :" + modelName + "<<<<<<\n");

        }
    }
}