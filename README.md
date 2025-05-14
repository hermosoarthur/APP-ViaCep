# 📦 APP-ViaCep

Aplicativo Android desenvolvido em **Kotlin** para consulta de endereços brasileiros a partir do **CEP**, utilizando a API pública do [ViaCEP](https://viacep.com.br/). O app também armazena o histórico de buscas localmente usando a biblioteca **Room**.

---

## ✨ Funcionalidades

- 🔍 Busca de endereços a partir de um CEP
- 💾 Armazenamento de histórico de buscas com **Room**
- 🔄 Botão para atualizar os dados do endereço
- 🗑️ Opção de deletar entradas do histórico
- ⚠️ Tratamento de erros para CEPs inválidos ou inexistentes
- 📜 Histórico com **ScrollView** ou **RecyclerView**
- 🖼️ Layout com imagem no topo e título personalizado

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin** — Linguagem principal
- **Android Studio** — Ambiente de desenvolvimento
- **Retrofit** — Para realizar requisições HTTP
- **Room** — Banco de dados local para persistência de dados
- **ViewBinding** — Para acesso seguro às views
- **ConstraintLayout** — Para organização responsiva do layout

---

## 📲 Como Executar o Projeto

### ✅ Pré-requisitos

- Android Studio instalado (versão mais recente recomendada)
- SDK Android 21 ou superior
- Emulador ou dispositivo físico com Android

### 🚀 Passos para execução

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/hermosoarthur/APP-ViaCep.git
## 2. Abra o projeto no Android Studio

- Vá em `File > Open` e selecione a pasta `APP-ViaCep`.

## 3. Sincronize o projeto com o Gradle

- O Android Studio fará isso automaticamente ao abrir.
- Verifique se todas as dependências foram baixadas com sucesso.

## 4. Execute o app

- Conecte um dispositivo Android via USB (com depuração ativada) ou inicie um emulador.
- Clique no botão **Run** (ícone de ▶️) ou pressione `Shift + F10`.

---

## 🧪 Como Usar

1. Digite um **CEP válido** no campo de busca (somente números).
2. Pressione o botão de busca para consultar o endereço.
3. Os dados (logradouro, bairro, localidade, UF, etc.) serão exibidos na tela.
4. O histórico da busca é salvo automaticamente e pode ser visualizado abaixo.
5. Você pode **atualizar** os dados ou **deletar** uma entrada do histórico.

---

## 🧱 Estrutura do Projeto

- `MainActivity.kt` 
- `Endereco.kt` 
- `ViaCepService.kt` 
- `EnderecoDao.kt` 
- `AppDatabase.kt` 
- `EnderecoAdapter.kt` 
- `activity_main.xml` 

---

## 🧰 Bibliotecas Utilizadas

| Biblioteca | Função |
|------------|--------|
| Retrofit | Consumo da API ViaCEP |
| Room | Banco de dados local |
| ViewBinding | Acesso direto às views do XML |



## 👨‍💻 Autor

Desenvolvido por [Arthur Hermoso](https://github.com/hermosoarthur)

