# lab-springboot

API back-end para projeto de testes com spring-boot

## Apresentação

Ao rodar esse projeto com o Maven, você terá um serviço REST rodando localmente em 8080
Para saber quais serviços estão disponíveis, depois de subir a aplicação,
você pode acessar http://localhost:8080/lab-springboot/swagger-ui.html

### Pré-requisitos

Basta entrar na pasta do projeto e rodar `mvn clean package` 
e em seguida `java -jar lab-springboot.jar` na pasta onde for gerado.

### Sobre a autenticação

O sistema utiliza autenticação/autorização por token OAuth2.

Para obter um token de acesso ao sistema ao portador (bearer-token) faz-se necessário utilizar-se do endpoint `/oauth/token`.
Nessa chamada deve-se utilizar o `Authorization Basic` passando como **usuário** e **senha** o **client** e **secret** configurados no `AuthorizationServerConfigurerAdapter`.
Além disso, deve ser enviado no corpo da chamada `POST` no formato `x-www-form-urlencoded` os parametros: **client**=seuclientid, **grant_type**=password além do **username** e **password** de login do usuário.

Para obter um token à partir do refresh_token, deve-se acessar o mesmo endpoint `/oauth/token` porém com dados diferentes.
O `Authorization Basic` igual à chamada anterior, porém no corpo POST `x-www-form-urlencoded` os seguintes parametros: **client**=seuclientid, **grant_type**=refresh_token e **refresh_token**=seurefreshtoken


### Serviços disponíveis:

```
 Categoria 
```
- [Listar Categorias] (http://localhost:8080/lab-springboot/categorias)