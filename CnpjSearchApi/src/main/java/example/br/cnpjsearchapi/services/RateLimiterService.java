package example.br.cnpjsearchapi.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Service
public class RateLimiterService {
    private static final int LIMIT = 3;
    private final Map<String, Queue<LocalDateTime>> requestMap = new HashMap<>();

    public boolean canMakeRequest(String requestType) {
        LocalDateTime now = LocalDateTime.now();

        // Obtém a fila de timestamps para o tipo de requisição especificado
        Queue<LocalDateTime> requestTimes = requestMap.computeIfAbsent(requestType, k -> new LinkedList<>());

        // Remove timestamps mais antigos que 1 minuto
        while (!requestTimes.isEmpty() && requestTimes.peek().isBefore(now.minusMinutes(1))) {
            requestTimes.poll();
        }

        // Verifica se atingiu o limite de consultas
        if (requestTimes.size() < LIMIT) {
            requestTimes.add(now);
            return true;
        } else {
            return false;
        }
    }
}