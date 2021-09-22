### Requisitos mínimos

Utilizar Java

Tem que compilar

Testes unitários

### Regras de negócio

1. Fazer um sistema que receba o nome da pessoa e o valor do credito desejado.

2. Esse sistema devera consultar uma api que retorna uma lista de pessoas cadastradas (nome, idade e salário).

3. Buscar nessa lista a pessoa passada no input da api e calcular quanto a pessoa poderá receber de credito, qual o valor da parcela e em quantas parcelas será feito .

4. Dependendo da idade da pessoa ela poderá receber apenas uma porcentagem do salário dela
   Idade maior que 80, poderá receber até 20% do saĺário
   Idade maior que 50, poderá receber até 70% do saĺário
   Idade maior que 30, poderá receber até 90% do saĺário
   Idade maior que 20, poderá receber até 100% do saĺário

5. Dependendo da faixa salarial da pessoa a parcela poderá comprometer apenas uma porcentagem do salário dela
   De 1000 a 2000 reais, a parcela poderá comprometer 5% do saĺário
   De 2001 a 3000 reais, a parcela poderá comprometer 10% do saĺário
   De 3001 a 4000 reais, a parcela poderá comprometer 15% do saĺário
   De 4001 a 5000 reais, a parcela poderá comprometer 20% do saĺário
   De 5001 a 6000 reais, a parcela poderá comprometer 25% do saĺário
   De 6001 a 7000 reais, a parcela poderá comprometer 30% do saĺário
   De 7001 a 8000 reais, a parcela poderá comprometer 35% do saĺário
   De 8001 a 9000 reais, a parcela poderá comprometer 40% do saĺário
   A partir de 9001 reais, a parcela poderá comprometer 45% do saĺário

6. Não permitir que a api receba valor negativo.

7. Não permitir que a api receba valor zerado.

8. A api terá um endpoint de entrada que obedece o contrato abaixo.

Path: api/credito/{nome}/{valorPedido}

Response:
{
"nome": "string",
"saĺário": 1.0, -- decimal
"valorPedido": 1.0, -- decimal
"valorEmprestado": 2.0, -- decimal
"quantidadeParcelas": 4,  -- int
"valorParcela": 5.0 -- decimal
}


9. A api terá que consultar uma api que responde na URL " http://www.mocky.io/v2/5e5ec624310000b738afd85a" e tem como response um objeto no formato abaixo

[
{
"Nome": "string",
"Idade": 0, -- int
"saĺário": 1.0 -- decimal
}
]


Obs:
1. Utilizar o tipo decimal ao invés de double.
2. Se precisar, arredondar para cima e manter duas casas decimais.