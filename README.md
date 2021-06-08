# GROPES API
API da aplicação [IODev](https://github.com/thiagojacinto/gropes-ui), que utiliza da metodologia GROPES,implantada no [BD](gropes.sql), para avaliação da obsolescência relativa entre pessoas desenvolvedoras. A api roda um cronjob à cada 5 minutos para executar as procedures do BD e atualizar os dados(caso haja alteração), além de cadastrar os desenvolvedores, etc.

# Requisitos
Para construir e executar o aplicativo, você precisa de:

* [JDK 11](https://openjdk.java.net/projects/jdk/11/)
* Maven,embutido na aplicação
* SGBD PostgreSql

# Executando o aplicativo localmente
Existem várias maneiras de executar um aplicativo Spring Boot em sua máquina local. Uma maneira é executar o método main na classe br.unit.pe.GropesApplication da sua IDE.

#Plugin Maven do Spring Boot
Como alternativa, você pode usar o plugin Maven do Spring Boot da seguinte maneira:

>mvn spring-boot:run

#Detalhamento da API
[Clique aqui](https://gropes.herokuapp.com/swagger-ui/)
#Mais detalhes
Para mais detalhes , vá para [Ajuda](HELP.md)