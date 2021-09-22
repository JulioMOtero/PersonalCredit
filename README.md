### Requisitos mínimos

Utilizar Java

Tem que compilar

Testes unitários

### Regras de negócio

1. Fazer um sistema que receba o nome da pessoa e o valor do credito desejado.

2. Esse sistema devera consultar uma api que retorna uma lista de pessoas cadastradas (nome, idade e salário).

3. Buscar nessa lista a pessoa passada no input da api e calcular quanto a pessoa poderá receber de credito, qual o valor da parcela e em quantas parcelas será feito .

4. Dependendo da idade da pessoa ela poderá receber apenas uma porcentagem do salário dela
   - Idade maior que 80, poderá receber até 20% do salário
   - Idade maior que 50, poderá receber até 70% do salário
   - Idade maior que 30, poderá receber até 90% do salário
   - Idade maior que 20, poderá receber até 100% do salário

5. Dependendo da faixa salarial da pessoa a parcela poderá comprometer apenas uma porcentagem do salário dela
   - De 1000 a 2000 reais, a parcela poderá comprometer 5% do salário
   - De 2001 a 3000 reais, a parcela poderá comprometer 10% do salário
   - De 3001 a 4000 reais, a parcela poderá comprometer 15% do salário
   - De 4001 a 5000 reais, a parcela poderá comprometer 20% do salário
   - De 5001 a 6000 reais, a parcela poderá comprometer 25% do salário
   - De 6001 a 7000 reais, a parcela poderá comprometer 30% do salário
   - De 7001 a 8000 reais, a parcela poderá comprometer 35% do salário
   - De 8001 a 9000 reais, a parcela poderá comprometer 40% do salário
   - A partir de 9001 reais, a parcela poderá comprometer 45% do salário
   

6. Não permitir que a api receba valor negativo.

7. Não permitir que a api receba valor zerado.

8. A api terá um endpoint de entrada que obedece o contrato abaixo.

Path: api/credito/{name}/{requestedValue}

Response:
{
   "name": "string",
   "salary": 1.0, -- BigDecimal
   "requestedValue (valor pedido)": 1.0, -- BigDecimal
   "loan (valorEmprestado)": 2.0, -- BigDecimal
   "installments (quantidadeParcelas)": 4,  -- int
   "value of installments (valorParcela)": 5.0 -- BigDecimal
}


9. A api terá que consultar uma api que responde na URL " http://www.mocky.io/v2/5e5ec624310000b738afd85a" e tem como response um objeto no formato abaixo

[
   {
      "Name "string",
      "Age": 0, -- int
      "Salary": 1.0 -- decimal
   }
]


Obs:
1. Utilizar o tipo decimal ao invés de double.
2. Se precisar, arredondar para cima e manter duas casas decimais.