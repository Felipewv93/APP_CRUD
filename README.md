# 📱 App de Cadastro de Alunos

Um aplicativo Android desenvolvido em **Kotlin** que implementa um sistema completo de **CRUD** (Create, Read, Update, Delete) para gerenciamento de cadastro de alunos, utilizando banco de dados **SQLite** local.

## 🎯 Funcionalidades

- ✅ **Cadastrar** novos alunos com nome, RA e duas notas
- 📖 **Listar** todos os alunos cadastrados
- ✏️ **Editar** informações de alunos existentes
- 🗑️ **Excluir** alunos do sistema
- 🧮 **Cálculo automático** da média das notas
- 📊 **Visualização** completa dos dados em lista

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **IDE**: Android Studio
- **Banco de Dados**: SQLite
- **UI**: XML com ConstraintLayout
- **Versão SDK**: 
  - Mínima: 24 (Android 7.0)
  - Alvo: 36
  - Compilação: 36

## 📋 Estrutura do Projeto

```
app/
├── src/main/
│   ├── java/com/example/crudtutorial/
│   │   └── MainActivity.kt          # Atividade principal com lógica CRUD
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml    # Layout da interface
│   │   └── values/
│   │       └── strings.xml          # Strings do app
│   └── AndroidManifest.xml
└── build.gradle.kts                 # Configurações do módulo
```

## 💾 Banco de Dados

### Estrutura da Tabela `Pessoas`

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | INTEGER | Chave primária (auto incremento) |
| `nome` | TEXT | Nome do aluno |
| `ra` | INTEGER | Registro Acadêmico |
| `nota1` | FLOAT | Nota da primeira avaliação (AP1) |
| `nota2` | FLOAT | Nota da segunda avaliação (AP2) |
| `media` | FLOAT | Média calculada automaticamente |

## 🎨 Interface do Usuário

A interface possui:
- Campos de entrada para nome, RA, AP1 e AP2
- Botões para cada operação CRUD:
  - **Inserir**
  - **Atualizar** 
  - **Deletar**
  - **Listar**
- Lista scrollable com todos os alunos cadastrados

## 🚀 Como Executar

### Pré-requisitos
- Android Studio (versão mais recente)
- JDK 11 ou superior
- Dispositivo Android ou Emulador (API 24+)

### Passos para execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/Felipewv93/APP_CRUD.git
   ```

2. **Abra o projeto no Android Studio**:
   - File → Open → Selecione a pasta do projeto

3. **Sincronize as dependências**:
   - O Android Studio fará isso automaticamente ou clique em "Sync Now"

4. **Execute o app**:
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique no botão "Run" (▶️) ou use `Shift + F10`

## 📱 Como Usar

1. **Cadastrar Aluno**:
   - Preencha todos os campos (Nome, RA, AP1, AP2)
   - Clique em "Inserir"
   - A média será calculada automaticamente

2. **Visualizar Alunos**:
   - Clique em "Listar Alunos"
   - Todos os cadastros aparecerão na lista

3. **Editar Aluno**:
   - Clique em um item da lista para selecioná-lo
   - Os campos serão preenchidos automaticamente
   - Modifique as informações desejadas
   - Clique em "Atualizar"

4. **Excluir Aluno**:
   - Selecione um aluno da lista
   - Clique em "Deletar"

## 🏗️ Arquitetura

- **BancoHelper**: Classe que gerencia o banco SQLite
- **MainActivity**: Activity principal com toda a lógica CRUD
- **Layout responsivo**: Interface adaptável a diferentes tamanhos de tela

## 🎓 Conceitos Abordados

Este projeto é ideal para aprender:
- **SQLite** no Android
- **CRUD Operations** básicas
- **Kotlin** para Android
- **UI/UX** design com XML
- **Gerenciamento de estado** da aplicação
- **Tratamento de eventos** de click
- **Validação de dados** de entrada

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!