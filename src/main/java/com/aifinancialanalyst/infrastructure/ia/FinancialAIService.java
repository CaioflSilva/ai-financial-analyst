package com.aifinancialanalyst.infrastructure.ia;

import com.aifinancialanalyst.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialAIService {

    private final ChatClient.Builder chatClientBuilder;

    public List<String> analyzeTransactions(List<Transaction> transactions) {
        ChatClient chatClient = chatClientBuilder.build();

        StringBuilder transactionData = new StringBuilder();
        transactions.forEach(t -> transactionData.append(
                String.format("- %s: R$ %.2f (%s) em %s\n",
                        t.getDescription(),
                        t.getAmount(),
                        t.getType(),
                        t.getDate())
        ));

        String prompt = String.format("""
                Você é um consultor financeiro pessoal.
                Analise as transações abaixo e responda EXATAMENTE neste formato, uma informação por linha:
                
                RESUMO: [uma frase resumindo o padrão de gastos]
                ATENÇÃO 1: [ponto de atenção 1]
                ATENÇÃO 2: [ponto de atenção 2]
                ATENÇÃO 3: [ponto de atenção 3]
                RECOMENDAÇÃO 1: [recomendação 1]
                RECOMENDAÇÃO 2: [recomendação 2]
                RECOMENDAÇÃO 3: [recomendação 3]
                
                Transações:
                %s
                
                Responda em português. Seja direto e objetivo. Não adicione texto extra.
                """, transactionData);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        if (response == null || response.isBlank()) {
            return List.of("Não foi possível gerar a análise.");
        }

        return Arrays.stream(response.trim().split("\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }

    public List<String> chat(String userMessage, List<Transaction> transactions) {
        ChatClient chatClient = chatClientBuilder.build();

        StringBuilder transactionData = new StringBuilder();
        transactions.forEach(t -> transactionData.append(
                String.format("- %s: R$ %.2f (%s) em %s\n",
                        t.getDescription(),
                        t.getAmount(),
                        t.getType(),
                        t.getDate())
        ));

        String prompt = String.format("""
                Você é um assistente financeiro pessoal.
                
                Dados financeiros do usuário:
                %s
                
                Pergunta: %s
                
                Responda em português. Máximo 3 linhas, uma informação por linha.
                Não adicione texto extra, apenas as linhas de resposta.
                """, transactionData, userMessage);

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        if (response == null || response.isBlank()) {
            return List.of("Não foi possível processar sua pergunta.");
        }

        return Arrays.stream(response.trim().split("\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }
}