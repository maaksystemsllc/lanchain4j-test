package maak;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.output.Response;


import java.util.concurrent.CompletableFuture;

import static maak.LLMConstants.*;

public class OllamaStreamingChatModelTest {

    static void testStreaming(String modelName) {
        System.out.println("\n\n>>>>>>>Testing Model :" + modelName + "<<<<<<\n");
        StreamingChatLanguageModel model = OllamaStreamingChatModel.builder()
                .baseUrl(OLLAMA_BASE_URL)
                .modelName(modelName)
                .build();

        String userMessage = "Write a 100-word poem about Java and AI";

        CompletableFuture<Response<AiMessage>> futureResponse = new CompletableFuture<>();
        model.generate(userMessage, new StreamingResponseHandler<>() {

            @Override
            public void onNext(String token) {
                System.out.print(token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                futureResponse.complete(response);
            }

            @Override
            public void onError(Throwable error) {
                futureResponse.completeExceptionally(error);
            }
        });

        futureResponse.join();
    }
    public static void main(String[] args) {

        testStreaming(LLAMA3_2_MODEL_NAME);
        testStreaming(MISTRAL_MODEL_NAME);
        testStreaming(PHI3_MODEL_NAME);

        /** Gama is failing to produce 100 world. **/
        testStreaming(GEMMA_MODEL_NAME);
    }
}
