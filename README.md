## Configuração de Ambiente Pipeline
1º Copia e cola o arquivo aplication properties com as modificações necessárias.<br>
2º Usando na máquina local, por default já pega o application.properties padrão.<br>
3º Usando para teste local em outros ambiente:<br>
 Vai no menu em Run > Run Configurations > na aba Arguments > no campo VM Argments copia o código com o ambiente
 
```
-Dspring.profiles.active=prod
```

Para passar como variável de ambiente no CMD Docker e etc.

```
CMD [ "sh", "-c", "java -Dspring.profiles.active=prod -jar spring-app.jar" ]
```

##### Usar valores do arquivo application.properties
1º Cria a variável no arquivo

```
valor.exemplo=valueAqui
```

2º Para usar no código usa anotation @Value("${valor.exemplo:valorDefault}")

```
@Value("${valor.exemplo}")
private String value;
```

OBS: Pode pegar variáveis de ambiente no arquivo application.properties -> valor.exemplo=${VARIAVEL_AMBIENTE}<br>

## Incluindo Documentação da API com Swegger
Vídeo que usei: https://www.youtube.com/watch?v=NJ_-voEbIbI<br>
Vídeo que tem outras configurações e campo Authorization: https://www.youtube.com/watch?v=9cyCT43T5SE<br>
1º Insere as dependêmcias no poom:

```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>
```

2º Criar a classe bean de configuração no diretório core/config/SpringFoxConfig.java com o código:

```
@Configuration
public class SpringFoxConfig 
{
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}
```

3º Para colocar descrição no endpoint, coloque a anotação em cada controller

```
@ApiOperation(value="Retorna lista com todas as contas financeiras")
```

4º Para acessar ir na url, ex. local:
 
```
http://localhost:8080/swagger-ui/index.html
```




<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

## Implementação
Este projeto foi implementado usando spring boot com padrão MVC contendo as seguintes dependências:<br>
Spring Web<br>
Spring Data JPA<br>
Spring Boot DevTools<br>
Spring Security<br>
Postgres Driver<br>
Lombok<br>
Model Mapper<br>
Spring Security<br>

IDE: Spring Tool Suite 4


## Iniciando o projeto
Clone o repositório

```
git clone https://github.com/DannyRodrigues15/serie-api.git
```
Crie a base de dados postgres com o nome nome_banco.

```
create database nome_banco;
```

Atualize username e password no arquivo resources/application.properties.

```
spring.datasource.username=USUARIO-BANCO
spring.datasource.password=SENHA-BANCO

```

1. Instale o lombok na IDE
2. Import como Existing Maven Projects
3. Selecione o diretório e finish
4. Clique com o botão direito no projeto e run > java application

## Obter Autorização (Spring Security)
Utilizado Basic Auth para autorização.<br>
Digite as credenciais para obter autorização:<br>
Username: **luiz**<br>
Password: **123**<br>

