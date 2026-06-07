package com.aifinancialanalyst.infrastructure.ai;

import com.aifinancialanalyst.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialAIService {

    private final ChatClient.Builder chatClientBuilder;

    public String analyzeTransactions(List<Transaction> transactions) {
        ChatClient chatClient = chatClientBuilder.build();

        StringBuilder transactionData = new StringBuilder();
        transactions.forEach(t -> transactionData.append(
                String.format("- %s: R$ %.2f (%s) em %s%n",
                        t.getDescription(),
                        t.getAmount(),
                        t.getType(),
                        t.getDate())
        ));

        String prompt = String.format("""
                Você é um consultor financeiro pessoal especializado.
                Analise as seguintes transações financeiras e forneça:
                1. Um resumo do padrão de gastos
                2. Pontos de atenção
                3. Recomendações práticas para melhorar a saúde financeira
                
                Transações:
                %s
                
                Responda em português de forma clara, objetiva e amigável.
                Máximo 5 parágrafos.
                """, transactionData);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}