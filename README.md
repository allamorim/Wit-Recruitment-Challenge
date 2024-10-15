
# Wit Recruitment Challenge

This challenge was made to evaluate Java developers applying to WIT. The result was a REST API that provides the basic functionalities of a calculator, using RabbitMQ and Spring AMQP for communication intermodule.

## API's Documentation


#### Sum (+)


```http
  GET /sum/a={a}&b={b}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `a`         | `BigDecimal` | **Obrigatório**. First Value              |
| `b`         | `BigDecimal` | **Obrigatório**. Second Value              |



#### Subtraction (-)


```http
  GET /subtraction/a={a}&b={b}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `a`         | `BigDecimal` | **Obrigatório**. First Value              |
| `b`         | `BigDecimal` | **Obrigatório**. Second Value              |


#### Multiplication (*)


```http
  GET /multiplication/a={a}&b={b}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `a`         | `BigDecimal` | **Obrigatório**. First Value              |
| `b`         | `BigDecimal` | **Obrigatório**. Second Value              |


#### Division (/)


```http
  GET /division/a={a}&b={b}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `a`         | `BigDecimal` | **Obrigatório**. First Value              |
| `b`         | `BigDecimal` | **Obrigatório**. Second Value              |


## Autores

- [@allamorim](https://github.com/allamorim/)

