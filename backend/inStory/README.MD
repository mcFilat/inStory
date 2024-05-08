## PIZZA DELIVERY API
This is a REST API for a Pizza delivery service built for fun and learning with FastAPI, SQLAlchemy and PostgreSQL. The video playlist is 
[here](https://www.youtube.com/playlist?list=PLEt8Tae2spYnLMAf8RGCNYhovIFZHVsPP)


## ROUTES TO IMPLEMENT
| METHOD   | ROUTE                                                             | FUNCTIONALITY                                                               |
|----------|-------------------------------------------------------------------|-----------------------------------------------------------------------------| 
| *POST*   | ```/api/auth/login/```                                            | _Авторизация_                                                               |
| *POST*   | ```/api/auth/token-verify/```                                     | _Верификация сессии_                                                        |
| *POST*   | ```/api/auth/register/```                                         | _Регистрация пользователя_                                                  |
| *PUT*    | ```/api/profile/```                                               | _Редактирование пользователя_                                               |
| *DELETE* | ```/api/profile```                                                | _Удаление профиля пользователя_                                             |
| *GET*    | ```/api/users/```                                                 | _Получение списка пользователей_                                            |
| *GET*    | ```/api/users/<int:userid>/friends/```                            | _Получение списка друзей_                                                   |
| *GET*    | ```/api/users/<int:userid>/albums/```                             | _Получение списка альбомов, ассоциированных с идентификатором пользователя_ |
| *GET*    | ```/api/albums/<int:albumid>/```                                  | _Получение альбома по идентификатору_                                       |
| *POST*   | ```/api/albums/```                                                | _Создание альбома_                                                          |
| *PUT*    | ```/api/albums/<int:albumid>/```                                  | _Редактирование альбома_                                                    |
| *DELETE* | ```/api/albums/<int:albumid>/```                                  | _Удаление альбома_                                                          |
| *GET*    | ```/api/albums/<int:albumid>/telegrams/```                        | _Получение списка телеграмм, ассоциированных с идентификатором альбома_     |
| *GET*    | ```/api/albums/<int:albumid>/participants/```                     | _Получение списка участников альбома_                                       |
| *POST*   | ```/api/albums/<int:albumid>/participants/```                     | _Добавление участника альбома_                                              |
| *DELETE* | ```/api/albums/<int:albumid>/participants/<int:participantid>/``` | _Удаление участника альбома_                                                |
| *GET*    | ```/api/telegrams/<int:telegramid>/```                            | _Получение телеграммы по идентификатору_                                    |
| *POST*   | ```/api/telegrams/```                                             | _Создание телеграммы_                                                       |
| *PUT*    | ```/api/telegrams/<int:telegramid>/```                            | _Редактирование телеграммы_                                                 |
| *DELETE* | ```/api/telegrams/<int:telegramid>/```                            | _Удаление телеграммы_                                                       |
| *GET*    | ```/api/friendships/outgoing/```                                  | _Получение списка исходящих заявок на дружбу_                               |
| *GET*    | ```/api/friendships/incoming/```                                  | _Получение списка входящих заявок на дружбу_                                |
| *POST*   | ```/api/friendships/```                                           | _Создание заявки на дружбу_                                                 |
| *DELETE* | ```/api/friendships/<int:friendshipid>/```                        | _Удаление заявки на дружбу_                                                 |
| *PUT*    | ```/api/friendships/<int:friendshipid>/approve/```                | _Одобрение входящей заявки на дружбу_                                       |
| *PUT*    | ```/api/friendships/<int:friendshipid>/reject/```                 | _Отклонение входящей заявки на дружбу_                                      |

**Авторизация и управление токенами**

**Метод:** POST
**Endpoint:** /api/auth/login/
**Запрос:**
```json
{
  "email": "user@example.com",
  "password": "secretpassword"
}
```

**Ответ:**
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6IlVzZXIgTmFtZSIsImVtYWlsIjoidXNlckBleGFtcGxlLmNvbSJ9.QhLZN-qhxu2Gd_0cNi8i0u_X58Skg3s0o_i2WXbvBYg"
}
```

**Метод:** GET
**Endpoint:** /api/auth/token-verify/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "valid": true
}
```

**Метод:** POST
**Endpoint:** /api/auth/token-renew/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6IlVzZXIgTmFtZSIsImVtYWlsIjoidXNlckBleGFtcGxlLmNvbSJ9.QhLZN-qhxu2Gd_0cNi8i0u_X58Skg3s0o_i2WXbvBYg"
}
```

**Управление пользователями**

**Метод:** PUT
**Endpoint:** /api/profile/
**Заголовки:**
```
Authorization: Bearer <token>
```
**Запрос:**
```json
{
  "username": "new_username",
  "email": "new_email@example.com"
}
```

**Ответ:**
```json
{
  "username": "new_username",
  "email": "new_email@example.com"
}
```

**Метод:** DELETE
**Endpoint:** /api/profile/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```
HTTP 204 No Content
```

**Метод:** GET
**Endpoint:** /api/users/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  },
  {
    "id": 2,
    "username": "user2",
    "email": "user2@example.com"
  }
]
```

**Метод:** GET
**Endpoint:** /api/users/<int:userid>/friends/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "username": "friend1",
    "email": "friend1@example.com"
  },
  {
    "id": 2,
    "username": "friend2",
    "email": "friend2@example.com"
  }
]
```

**Метод:** GET
**Endpoint:** /api/users/<int:userid>/albums/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "title": "Album 1",
    "description": "This is the first album."
  },
  {
    "id": 2,
    "title": "Album 2",
    "description": "This is the second album."
  }
]
```

**Управление альбомами**

**Метод:** GET
**Endpoint:** /api/albums/<int:albumid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "id": 1,
  "title": "Album 1",
  "description": "This is the first album."
}
```

**Метод:** POST
**Endpoint:** /api/albums/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "title": "New Album",
  "description": "This is a new album."
}
```

**Ответ:**
```json
{
  "id": 3,
  "title": "New Album",
  "description": "This is a new album."
}
```

**Метод:** PUT
**Endpoint:** /api/albums/<int:albumid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "title": "Updated Album",
  "description": "This is an updated album."
}
```

**Ответ:**
```json
{
  "id": 1,
  "title": "Updated Album",
  "description": "This is an updated album."
}
```

**Метод:** DELETE
**Endpoint:** /api/albums/<int:albumid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```
HTTP 204 No Content
```

**Метод:** GET
**Endpoint:** /api/albums/<int:albumid>/telegrams/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "title": "Telegram 1",
    "content": "This is the first telegram."
  },
  {
    "id": 2,
    "title": "Telegram 2",
    "content": "This is the second telegram."
  }
]
```

**Метод:** GET
**Endpoint:** /api/albums/<int:albumid>/participants/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "username": "participant1",
    "email": "participant1@example.com"
  },
  {
    "id": 2,
    "username": "participant2",
    "email": "participant2@example.com"
  }
]
```

**Метод:** POST
**Endpoint:** /api/albums/<int:albumid>/participants/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "user_id": 3
}
```

**Ответ:**
```json
{
  "id": 3,
  "username": "participant3",
  "email": "participant3@example.com"
}
```

**Метод:** DELETE
**Endpoint:** /api/albums/<int:albumid>/participants/<int:participantid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```
HTTP 204 No Content
```

**Управление телеграммами**

**Метод:** GET
**Endpoint:** /api/telegrams/<int:telegramid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "id": 1,
  "title": "Telegram 1",
  "content": "This is the first telegram."
}
```

**Метод:** POST
**Endpoint:** /api/telegrams/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "title": "New Telegram",
  "content": "This is a new telegram."
}
```

**Ответ:**
```json
{
  "id": 3,
  "title": "New Telegram",
  "content": "This is a new telegram."
}
```

**Метод:** PUT
**Endpoint:** /api/telegrams/<int:telegramid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "title": "Updated Telegram",
  "content": "This is an updated telegram."
}
```

**Ответ:**
```json
{
  "id": 1,
  "title": "Updated Telegram",
  "content": "This is an updated telegram."
}
```

**Метод:** DELETE
**Endpoint:** /api/telegrams/<int:telegramid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```
HTTP 204 No Content
```

**Управление заявками на дружбу**

**Метод:** GET
**Endpoint:** /api/friendships/outgoing/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "sender": {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com"
    },
    "receiver": {
      "id": 2,
      "username": "user2",
      "email": "user2@example.com"
    },
    "status": "pending"
  },
  {
    "id": 2,
    "sender": {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com"
    },
    "receiver": {
      "id": 3,
      "username": "user3",
      "email": "user3@example.com"
    },
    "status": "pending"
  }
]
```

**Метод:** GET
**Endpoint:** /api/friendships/incoming/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 3,
    "sender": {
      "id": 2,
      "username": "user2",
      "email": "user2@example.com"
    },
    "receiver": {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com"
    },
    "status": "pending"
  },
  {
    "id": 4,
    "sender": {
      "id": 3,
      "username": "user3",
      "email": "user3@example.com"
    },
    "receiver": {
      "id": 1,
      "username": "user1",
      "email": "user1@example.com"
    },
    "status": "pending"
  }
]
```

**Метод:** POST
**Endpoint:** /api/friendships/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Запрос:**
```json
{
  "receiver_id": 2
}
```

**Ответ:**
```json
{
  "id": 5,
  "sender": {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  },
  "receiver": {
    "id": 2,
    "username": "user2",
    "email": "user2@example.com"
  },
  "status": "pending"
}
```

**Метод:** DELETE
**Endpoint:** /api/friendships/<int:friendshipid>/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```
HTTP 204 No Content
```

**Метод:** PUT
**Endpoint:** /api/friendships/<int:friendshipid>/approve/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "id": 3,
  "sender": {
    "id": 2,
    "username": "user2",
    "email": "user2@example.com"
  },
  "receiver": {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  },
  "status": "approved"
}
```

**Метод:** PUT
**Endpoint:** /api/friendships/<int:friendshipid>/reject/
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "id": 3,
  "sender": {
    "id": 2,
    "username": "user2",
    "email": "user2@example.com"
  },
  "receiver": {
    "id": 1,
    "username": "user1",
    "email": "user1@example.com"
  },
  "status": "rejected"
}
```