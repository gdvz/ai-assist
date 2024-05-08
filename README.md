
![Logo](https://assets-global.website-files.com/59e16042ec229e00016d3a66/6441d5f76d21e1e4dee9ffa2_Gen%20AI%20blog_Blog%20hero.png)
# Generative AI Assist Service

Microservice RESTful API implement with Local Generative AI and OpenAI Embeddings for learning and development


## Tech Stack

**Application** 
- Spring Boot (https://spring.io/projects/spring-boot)
- Spring AI (https://spring.io/projects/spring-ai)
- Ollama (https://ollama.com)
- Docker (https://docker.com)
- PGVector (https://github.com/pgvector/pgvector)
  
**AI Model**
- Llama 3 (Meta) (https://llama.meta.com/llama3)
- Gemma (Google) (https://ai.google.dev/gemma)
- OpenThaiGPT (Thai DEV) (https://openthaigpt.aieat.or.th)
- OpenAI Embeddings (Microsoft) (https://platform.openai.com/docs/guides/embeddings)

## Features

- API for Chat with local LLM (Llama3,Gemma,OpenThaiGPT)
- API for Add data into Vector Database from example dataset (bbc_data.csv)
- API for Search data from Vector Database (Similarity search)

    
## Deployment
```bash
   need to fill application.properties OpenAI api key for Spring AI EmbeddingClient
```
### Local
To deploy this project run on local following these step

Download and install
```bash
   https://github.com/pgvector/pgvector
```
Download and install
```bash
   https://ollama.com/download
```
then download all model project use on terminal by
```bash
  ollama run llama3
```
just wait for download finish and go for next model
```bash
  ollama run gemma
```
just wait for download finish and
```bash
  ollama run pacozaa/openthaigpt
```
now we can start spring boot application on local

------------
### Docker
if you want run on Docker (It extreamly slow maybe need to tuning some problem) 

```bash
  docker compose up
```
then let container download for model by run terminal these step
```bash
  docker exec -it ollama ollama run llama3
```
```bash
  docker exec -it ollama ollama run gemma
```
```bash
  docker exec -it ollama ollama run pacozaa/openthaigpt
```
now complete all for docker just start container and test
## API Reference

#### * Chat with llama3

```http
  GET /assist/llama3/chat
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `prompt` | `string` | **Required**. Your Prompt |

![Logo](https://coinaute.com/wp-content/uploads/2023/11/Design-sans-titre-46.png)

------------

#### * Chat with gemma

```http
  GET /assist/gemma/chat
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `prompt` | `string` | **Required**. Your Prompt |

![Logo](https://helios-i.mashable.com/imagery/articles/00veJ5qeI90cfXdfFzUfCrv/hero-image.fill.size_1248x702.v1708464912.jpg)

------------

#### * Chat with OpenThaiGPT

```http
  GET /assist/thaiOpenGpt/chat
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `prompt` | `string` | **Required**. Your Prompt |

![Logo](https://www.thephuketnews.com/photo/listing/2024/1707375278_1-org.jpg)

------------

#### * Add data into vector database

```http
  GET /vector/addData
```

------------

#### * Similarity search from vector database

```http
  GET /vector/search
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `query` | `string` | **Required**. Your Keyword for search |
| `key` | `string` | **Required**. Your Metadata type |
| `op` | `string` | **Required**. Operators of metadata (eq,gt,ge,lt,le,ne) |
| `value` | `string` | **Required**. Your Metadata value |

## Documentation
[Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/documentation.html)

[Spring AI](https://docs.spring.io/spring-ai/reference/index.html)

[Ollama](https://ollama.com/blog)

[Llama 3](https://ai.meta.com/blog/meta-llama-3)

[Gemma](https://ai.google.dev/gemma/docs/model_card)

[OpenThaiGPT](https://openthaigpt.aieat.or.th)

[PGVector](https://github.com/pgvector/pgvector)

[Vector Database](https://www.vultureprime.com/blogs/vector-database)



## Authors

- Mr. Jakkarin Saelaw 
- EMAIL : mr.chakkarin@hotmail.com
- [@gdvz](https://github.com/gdvz) 

