## 🌟 Sobre o Projeto

Este projeto consiste em um gerenciador de tarefas simples, desenvolvido em formato de interface de linha de comando (CLI) e construído puramente com Java 21. A aplicação incorpora conceitos modernos de desenvolvimento de software, como injeção de dependências, para garantir um código mais limpo, modular e eficiente.

### ✨ Principais Funcionalidades
Banco de Dados Flexível: A aplicação utiliza um arquivo JSON como banco de dados, permitindo que o usuário especifique tanto o nome quanto o caminho do arquivo.

Autoconfiguração Inteligente: Caso o usuário não forneça um nome ou caminho para o banco de dados, o sistema é capaz de se autoconfigurar. Ele cria automaticamente um diretório padrão (Resources) e os arquivos necessários: database.properties para as configurações e database.json para os dados.

Gerenciamento de Múltiplos Arquivos: Os usuários podem alterar as configurações a qualquer momento para apontar para um novo arquivo de dados. Se o arquivo especificado já existir no caminho indicado, a aplicação o utilizará. Essa funcionalidade permite que múltiplos usuários compartilhem a mesma aplicação, cada um com seu próprio arquivo de tarefas, ou que um mesmo usuário mantenha diferentes listas de tarefas.

Testes Abrangentes: O projeto conta com uma suíte de testes unitários para validar o comportamento dos métodos e garantir que todas as funcionalidades operem conforme o esperado.

### Arquitetura em Camadas
A aplicação foi projetada seguindo uma arquitetura multicamadas, onde cada componente possui uma responsabilidade clara e bem definida:

config: Responsável por instanciar as classes e gerenciar a injeção de dependências.

database: Gerencia as operações de leitura, escrita, atualização e exclusão de tarefas no arquivo de dados. Também lida com a leitura e gravação das configurações do banco de dados.

model: Contém as entidades (como a classe Task) e os enums utilizados no sistema.

mapper: Realiza a conversão (mapeamento) de objetos Java para o formato JSON e vice-versa.

repository: Camada de acesso a dados que interage diretamente com o arquivo database.json.

service: Contém a lógica de negócio da aplicação, comunicando-se com os repositórios e adicionando regras de negócio quando necessário.

controller: Orquestra as chamadas para a camada de serviço, podendo conter lógicas adicionais de fluxo da aplicação.

ui: Camada de interação com o usuário. É responsável por apresentar as opções, capturar as entradas de dados e repassá-las para as demais camadas.

### Estrutura de Suporte
exceptions: Define exceções customizadas e específicas para diferentes cenários de erro, tornando as mensagens mais claras e auxiliando na depuração.

resources: Diretório padrão utilizado para armazenar os arquivos de configuração e o banco de dados quando a autoconfiguração é ativada.

test: Contém todos os testes da aplicação, garantindo a qualidade e a estabilidade do código.

### 💻 Tecnologias Utilizadas

  * Java 21;


## 🚀 Começando

Siga estas instruções para obter uma cópia local do projeto e executá-la.

### ✅ Pré-requisitos

Antes de começar, certifique-se de que você tem as seguintes ferramentas instaladas em sua máquina:

  * [Java 21](https://www.oracle.com/br/java/technologies/downloads/#java21) (versão 21 ou superior)
  * [Git](https://git-scm.com/)

### 🛠️ Instalação

Em um terminal:

1.  **Clone o repositório**

```sh
git clone https://github.com/jean-batista/task-tracker-cli.git
```

2.  **Acesse o diretório do projeto**

```sh
cd task-tracker-cli
```

3. **Abra o projeto no VS Code**

```sh
code .
```

4. **Acesse a classe principal da aplicação**

```
src > main > App.java
```

Após acessar a classe principal, inicie o projeto.

OBS: Caso esteja usando outra IDE, abra o projeto conforme o processo de sua IDE.

## ⚙️ Uso

Para utilizar a aplicação, basta seguir as sugestões da UI e executar a ação que desejar:

### Tela de menu

* Opção 00: Sair
* Opção 01: Criar uma tarefa
* Opção 02: Atualizar uma tarefa
* Opção 03: Excluir uma tarefa
* Opção 04: Buscar uma tarefa pelo id
* Opção 05: Buscar todas as tarefas
* Opção 06: Buscar todas as tarefas a fazer
* Opção 07: Buscar todas as tarefas em progresso
* Opção 08: Buscar todas as tarefas concluídas
* Opção 09: Ver configurações do banco de dados
* Opção 10: Alterar configurações do arquivo de dados 

## 📄 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.