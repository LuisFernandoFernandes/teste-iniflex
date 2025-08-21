# Teste Técnico - Iniflex (Projedata)

Este projeto implementa os requisitos do teste prático de programação utilizando Java 21.

## Estrutura do projeto

```
src
├── App.java          # Classe principal (contém o main e regras de negócio)
├── Pessoa.java       # Classe base (nome, data de nascimento)
└── Funcionario.java  # Subclasse de Pessoa (salário, função)
```

## Tecnologias utilizadas
- **Java 21**
- **Manipulação de coleções**
- **BigDecimal** para operações financeiras (precisão monetária)
- **DateTime API (java.time)** para cálculos de idade e datas
- **NumberFormat** e **Locale pt-BR** para formatação numérica

## Funcionalidades implementadas
Conforme solicitado no enunciado, o programa realiza:

1. Inserção da lista inicial de funcionários.  
2. Remoção do funcionário "João".  
3. Impressão da lista com dados formatados (datas e salários).  
4. Aplicação de aumento de 10% nos salários.  
5. Agrupamento dos funcionários por função.  
6. Impressão de aniversariantes dos meses 10 e 12.  
7. Identificação do funcionário mais velho.  
8. Impressão dos funcionários em ordem alfabética.  
9. Cálculo do total da folha salarial.  
10. Cálculo de quantos salários mínimos cada funcionário recebe.  

## Como compilar e executar

### Compilar
Na raiz do projeto, execute:
```
javac -d bin src/*.java
```

### Executar
```
java -cp bin App
```

⚠️ Necessário ter **JDK 21+** instalado.

## Exemplo de saída

```
=== Funcionários ===
Maria        | 18/10/2000 |     2.009,44 | Operador       
Caio         | 02/05/1961 |     9.836,14 | Coordenador
Miguel       | 14/10/1988 |    19.119,88 | Diretor
Alice        | 05/01/1995 |     2.234,68 | Recepcionista  
Heitor       | 19/11/1999 |     1.582,72 | Operador
Arthur       | 31/03/1993 |     4.071,84 | Contador
Laura        | 08/07/1994 |     3.017,45 | Gerente
Heloísa      | 24/05/2003 |     1.606,85 | Eletricista
Helena       | 02/09/1996 |     2.799,93 | Gerente

=== Funcionários (Após aumento de 10%) ===
Maria        | 18/10/2000 |     2.210,38 | Operador
Caio         | 02/05/1961 |    10.819,75 | Coordenador
Miguel       | 14/10/1988 |    21.031,87 | Diretor
Alice        | 05/01/1995 |     2.458,15 | Recepcionista
Heitor       | 19/11/1999 |     1.740,99 | Operador
Arthur       | 31/03/1993 |     4.479,02 | Contador
Laura        | 08/07/1994 |     3.319,20 | Gerente        
Heloísa      | 24/05/2003 |     1.767,54 | Eletricista
Helena       | 02/09/1996 |     3.079,92 | Gerente

=== Funcionários (Agrupados por função) ===

- Operador:
Maria        | 18/10/2000 |     2.210,38 | Operador       
Heitor       | 19/11/1999 |     1.740,99 | Operador

- Coordenador:
Caio         | 02/05/1961 |    10.819,75 | Coordenador

- Diretor:
Miguel       | 14/10/1988 |    21.031,87 | Diretor

- Recepcionista:
Alice        | 05/01/1995 |     2.458,15 | Recepcionista  

- Contador:
Arthur       | 31/03/1993 |     4.479,02 | Contador

- Gerente:
Laura        | 08/07/1994 |     3.319,20 | Gerente
Helena       | 02/09/1996 |     3.079,92 | Gerente

- Eletricista:
Heloísa      | 24/05/2003 |     1.767,54 | Eletricista

=== Aniversariantes (outubro e dezembro) ===
Maria        | 18/10/2000 |     2.210,38 | Operador       
Miguel       | 14/10/1988 |    21.031,87 | Diretor

=== Mais velho ===
Caio         | 64 anos

=== Ordem alfabética ===
Alice        | 05/01/1995 |     2.458,15 | Recepcionista
Arthur       | 31/03/1993 |     4.479,02 | Contador
Caio         | 02/05/1961 |    10.819,75 | Coordenador
Heitor       | 19/11/1999 |     1.740,99 | Operador
Helena       | 02/09/1996 |     3.079,92 | Gerente
Heloísa      | 24/05/2003 |     1.767,54 | Eletricista
Laura        | 08/07/1994 |     3.319,20 | Gerente
Maria        | 18/10/2000 |     2.210,38 | Operador
Miguel       | 14/10/1988 |    21.031,87 | Diretor

=== Total dos salários ===
R$ 50.906,82

=== Salários mínimos por funcionário ===
Maria        | 18/10/2000 |     2.210,38 | Operador        | 1,82 salários mínimos
Caio         | 02/05/1961 |    10.819,75 | Coordenador     | 8,93 salários mínimos
Miguel       | 14/10/1988 |    21.031,87 | Diretor         | 17,35 salários mínimos
Alice        | 05/01/1995 |     2.458,15 | Recepcionista   | 2,03 salários mínimos
Heitor       | 19/11/1999 |     1.740,99 | Operador        | 1,44 salários mínimos
Arthur       | 31/03/1993 |     4.479,02 | Contador        | 3,70 salários mínimos
Laura        | 08/07/1994 |     3.319,20 | Gerente         | 2,74 salários mínimos
Heloísa      | 24/05/2003 |     1.767,54 | Eletricista     | 1,46 salários mínimos
Helena       | 02/09/1996 |     3.079,92 | Gerente         | 2,54 salários mínimos
```
