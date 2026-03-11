import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private static final String API_KEY = "4bf6aa32d1545bda4c8b4883";

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        // Força o Java a aceitar ponto (.) ao invés de vírgula (,)
        leitura.useLocale(Locale.US);

        int opcao = 0;

        String menu = """
                *****************************************************
                Seja bem-vindo/a ao Conversor de Moedas =]
                
                1) Dólar (USD) => Peso argentino (ARS)
                2) Peso argentino (ARS) => Dólar (USD)
                3) Dólar (USD) => Real brasileiro (BRL)
                4) Real brasileiro (BRL) => Dólar (USD)
                5) Dólar (USD) => Peso colombiano (COP)
                6) Peso colombiano (COP) => Dólar (USD)
                7) Sair
                
                Escolha uma opção válida:
                *****************************************************
                """;

        while (opcao != 7) {
            System.out.println(menu);

            try {
                opcao = leitura.nextInt();

                if (opcao == 7) {
                    System.out.println("Saindo do Conversor de Moedas. Até logo!");
                    break;
                }

                if (opcao < 1 || opcao > 7) {
                    System.out.println("Opção inválida! Tente novamente.\n");
                    continue; // Volta para o início do while
                }

                System.out.println("Digite o valor que deseja converter (ex: 25 ou 25.50):");
                double valor = leitura.nextDouble();

                switch (opcao) {
                    case 1 -> converterMoeda("USD", "ARS", valor);
                    case 2 -> converterMoeda("ARS", "USD", valor);
                    case 3 -> converterMoeda("USD", "BRL", valor);
                    case 4 -> converterMoeda("BRL", "USD", valor);
                    case 5 -> converterMoeda("USD", "COP", valor);
                    case 6 -> converterMoeda("COP", "USD", valor);
                }

            } catch (InputMismatchException e) {
                // Se o usuário digitar algo que não seja número, cai aqui
                System.out.println("\n[ERRO] Valor inválido! Por favor, digite apenas números. Use ponto (.) para decimais.");

                // Limpa o que o usuário digitou de errado para não dar loop infinito
                leitura.nextLine();
            }
        }
        leitura.close();
    }

    private static void converterMoeda(String moedaBase, String moedaAlvo, double valor) {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + moedaBase + "/" + moedaAlvo;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonElement elemento = JsonParser.parseString(response.body());
            JsonObject objectRoot = elemento.getAsJsonObject();

            double taxaDeConversao = objectRoot.get("conversion_rate").getAsDouble();
            double resultado = valor * taxaDeConversao;

            System.out.printf("\nValor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]\n\n",
                    valor, moedaBase, resultado, moedaAlvo);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar converter a moeda: " + e.getMessage());
        }
    }
}