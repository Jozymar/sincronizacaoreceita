# sincronizacaoreceita

> Aplicação responsável por processar arquivos no formato **CSV**, realizar a sincronização dos mesmos e gerar um novo arquivo com o resultado do processamento.

---

## Tecnologias

* [Java versão 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Spring Boot versão 2.7.3](https://spring.io/projects/spring-boot/)
* [Opencsv versão 5.6](http://opencsv.sourceforge.net/)
* [Project Lombok versão 1.18.24](https://projectlombok.org/)
* [Junit versão 4.13.2](https://junit.org/junit4/)

### Execução

* Navegue até o diretório: `out/artifacts/sincronizacaoreceita_jar`.
* Copie o arquivo: `sincronizacaoreceita.jar` para um diretório de preferência, ou no mesmo diretório, execute o comando abaixo em um terminal, substituindo `<input-file>` pelo arquivo `CSV` que deseja processar:

~~~java
 java -jar sincronizacaoreceita.jar <input-file>
~~~

* Ao final do processamento no mesmo diretório, deverá ser exibido um arquivo `CSV` de nome: `contas-atualizadas.csv`, contendo uma nova coluna chamada: **resultado**, nessa coluna deverá ser exibido a resposta do processamento.
