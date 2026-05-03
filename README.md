# 📱 Prova1Treino2 - Aplicativo Android

Aplicativo Android desenvolvido como prática para manipulação de dados, navegação entre fragments, uso de ViewModel compartilhado e integração com recursos do dispositivo (como câmera e mapa).

---

## 🚀 Funcionalidades

- 📋 Listagem de alunos
- 🔍 Consulta por ID
- 📊 Cálculo de acertos com base em respostas
- 📈 Visualização de tentativas e desempenho
- 📷 Captura de imagem (uso da câmera)
- 🗺️ Integração com mapa (em desenvolvimento)
- 📌 Menu lateral (Navigation Drawer)
- ⋮ Menu de opções (Overflow)

---

## 🧠 Arquitetura

O projeto utiliza:

- **MVVM (Model-View-ViewModel)**
- **ViewModel compartilhado** entre fragments
- **LiveData** para atualização reativa da UI
- **Navigation Component** para controle de navegação
- **Repository Pattern** para acesso aos dados

---

## 📊 Exemplo de dados (db.json)

```json
{
  "number": 1,
  "person": "Lucas Ferreira",
  "count": 3,
  "responses": [
    ["V", "F", "V", "V", "F"]
  ],
  "solution": ["V", "F", "V", "V", "F"]
}
## 📊 Exemplo de dados (db.json)

```json
{
  "number": 1,
  "person": "Lucas Ferreira",
  "count": 3,
  "responses": [
    ["V", "F", "V", "V", "F"]
  ],
  "solution": ["V", "F", "V", "V", "F"]
}
