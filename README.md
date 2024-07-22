# Microserviço AssCache - Trabalho Prático 2

## Introdução
Neste projeto, o sistema de controle de assinaturas de aplicativos (SCAA) desenvolvido como um monolito no trabalho 1 será refatorado para um ambiente de microserviços. Um novo microserviço chamado **AssCache** será criado para melhorar a performance do sistema, especialmente na validação de assinaturas dos aplicativos.

## Objetivo
Construir o microsserviço **AssCache** e refatorar o código do SCAA para funcionar em conjunto com ele. O AssCache atuará como uma cache para melhorar a performance, respondendo rapidamente às consultas sobre a validade das assinaturas dos aplicativos.

## Arquitetura
A arquitetura do sistema consiste em:
- **SCAA**: Backend principal que gerencia as assinaturas.
- **AssCache**: Microsserviço de cache que armazena informações de validade das assinaturas para respostas rápidas.

## Funcionalidades
- **AssCache**:
  - Armazena a validade das assinaturas dos aplicativos.
  - Consulta o SCAA caso a informação não esteja na cache.
  - Atualiza sua cache ao receber notificações do SCAA sobre mudanças na validade das assinaturas.

- **SCAA**:
  - Gerencia o banco de dados principal das assinaturas.
  - Notifica todas as instâncias do AssCache sobre atualizações nas assinaturas usando filas de mensagens.

## Tecnologias Utilizadas
- **Docker** e **Docker Compose** para gerenciamento de containers.
- **RabbitMQ** para comunicação por filas entre o SCAA e as instâncias do AssCache.
- Linguagem de programação escolhida pelo grupo (ex. Java, Python).

## Como Executar

### Requisitos
- Docker e Docker Compose instalados.

### Passos para Compilação e Execução
1. Clone este repositório.
2. Navegue até o diretório raiz do projeto.
3. Construa e inicie os serviços usando Docker Compose: docker compose up --build

@Configuration
public class RabbitMQConfig {
    ...
    public static final String QUEUENAME = "scaa.v1.subscription-update.save-signature" + Math.random() * 1000;
    ...
}

@RabbitListener(queues = "#{rabbitMQConfig.getQueueName()}")
public void receive(Subscription entity) {
    logger.info("Mensagem recebida com a atualização da assinatura: {}", entity);
    // Código de atualização da assinatura
    logger.info("Data do fim da assinatura guardada na cache.");
}

