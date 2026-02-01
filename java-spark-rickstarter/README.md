
# Java + Spark Framework — Rick and Morty Backend Starter

Este repositório é o esqueleto do teste para vaga de **Desenvolvedor Backend Java Júnior**.
O candidato deverá implementar **persistência**, **repositórios**, e finalizar as regras dos endpoints.

Framework utilizado: **Spark Java**  
API externa utilizada: **https://rickandmortyapi.com/**

### Objetivo do Teste
O candidato deverá:
1. Implementar persistência local.
2. Importar personagens e episódios da API externa.
3. Implementar paginação.
4. Implementar CRUD completo de personagens e episódios.
5. Organizar código em camadas (controller → service → repository → model).

### Como rodar
```
mvn package
java -jar target/java-spark-rickstarter.jar
```

### Endpoints a serem implementados

#### GET /import
Importar todos os personagens e episódios relacionados.

#### GET /personagem?page=x&size=y
Listar personagens com paginação.

#### POST /personagem
Criar novo personagem.

#### PUT /personagem/:id
Atualizar personagem.

#### DELETE /personagem/:id
Excluir personagem.

#### GET /episodio?page=x&size=y
Listar episódios com paginação.

#### POST /episodio
Criar episódio.

#### PUT /episodio/:id
Atualizar episódio.

#### DELETE /episodio/:id
Excluir episódio.
