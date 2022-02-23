# Serviço de validar contas
## Especificações
Versão do Java: 17

## Como realizar o build do jar?
```
./mvnw package
```
O jar vai estar localizado na pasta target.

## Como executar?
```
	java -jar desafio-db-0.0.2-SNAPSHOT.jar caminho/do/arquivo.csv
```

## Como executar os testes?
```
./mvnw test
```

## Estrutura do Projeto
O sistema possui 3 serviços para validar as informações de conta.
* CsvService: Lida com leitura e escrita do arquivo csv;
* ReceitaService: Realiza as validações de conta;
* InformaçãoContaService: Utiliza o CsvService e o ReceitaService para realizar as validações e salvar o resultado.

## Como foi implementado
O sistema recupera os dados do csv, realiza o pré-processamento e utiliza um parallelstream para atualizar as contas de forma paralela.
Quando finalizada a atualização de todas contas, o resultado é salvo em um novo csv.

O csv com as atualizações possui uma coluna que indica se ocorreu um erro ao atualizar a conta.

## Débitos técnicos
* O csv salvo não tem virgula quando o número for inteiro, na coluna de saldo.