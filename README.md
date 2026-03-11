# Conversor de Moedas em Java 🪙

Este é um projeto de um Conversor de Moedas desenvolvido em Java. A aplicação realiza requisições a uma API de taxas de câmbio para obter dados atualizados em tempo real, manipula os dados JSON recebidos e exibe o resultado da conversão para o usuário através de uma interface interativa no console.

## 🎯 Objetivo
Oferecer uma interação textual amigável via console, permitindo que o usuário escolha entre pelo menos 6 opções distintas de conversões de moedas de forma dinâmica.

## 🛠️ Tecnologias Utilizadas
* **Java (JDK 11+)**
* **Biblioteca GSON:** Para manipulação e análise (parsing) dos dados em formato JSON.
* **ExchangeRate-API:** API externa utilizada para obter as taxas de conversão em tempo real.
* **HttpClient, HttpRequest e HttpResponse:** Classes nativas do Java para consumo da API.

## 💱 Moedas Suportadas (Filtro)
Foram selecionadas as seguintes moedas (Currency Codes) para as conversões:
* `ARS` - Peso argentino
* `BRL` - Real brasileiro
* `COP` - Peso colombiano
* `USD` - Dólar americano

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Java** e uma IDE (como **IntelliJ IDEA**) instalados na sua máquina.
2. Clone este repositório ou baixe o código fonte.
3. Importe a biblioteca **GSON** para o seu projeto (via Maven ou adicionando o `.jar` manualmente em `Project Structure > Modules > Dependencies`).
4. Execute a classe `Principal.java`.
5. Interaja com o menu no console digitando o número da opção desejada e o valor a ser convertido!