# Trabalho 1 - Projeto e Arquitetura de Software

Nomes: Eduardo Riboli, Eduardo Garcia, Jocemar Junior, Matheus Fernandes e Leticia

## Lista de funcionalidades:

· Cadastrar/editar/listar a base de aplicativos que disponibiliza

· Cadastrar/editar/listar a base de clientes

· Cadastrar a assinatura de um aplicativo por parte de um cliente

· Atualizar o valor do custo mensal de um aplicativo

· Responder se um par cliente/assinatura continua válido

· Listar as assinaturas de um cliente

· Listar os assinantes de um aplicativo

· Receber a notificação de pagamento de uma assinatura (enviado pelo banco conveniado) e atualizar a data de validade da assinatura

## Lista de ENDPOINTS:

· GET /servcad/clientes

· GET /servcad/aplicativos

· POST /servcad/assinaturas

· POST /servcad/aplicativos/atualizacusto/:idAplicativo

· GET /servcad/assinaturas/{tipo}

· GET /servcad/asscli/:codcli

· GET /servcad/assapp/:codapp

· POST /registrarpagamento

· GET /assinvalida/:codass
