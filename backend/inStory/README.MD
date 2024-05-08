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

## How to run the Project
- Install Postgreql
- Install Python
- Git clone the project with ``` git clone https://github.com/jod35/Pizza-Delivery-API.git```
- Create your virtualenv with `Pipenv` or `virtualenv` and activate it.
- Install the requirements with ``` pip install -r requirements.txt ```
- Create you database with `python manage.py runserver` 
- Finally run the API 
``` python manage.py runserver ``

**Авторизация и управление токенами**

**Метод:** POST
**Endpoint:** /api/auth/login
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
  "auth_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6IlVzZXIgTmFtZSIsImVtYWlsIjoidXNlckBleGFtcGxlLmNvbSJ9.QhLZN-qhxu2Gd_0cNi8i0u_X58Skg3s0o_i2WXbvBYg",
  "refresh_token": "0x9876543210FEDCBA9876543210FEDCBA"
}
```

**Метод:** GET
**Endpoint:** /api/auth/token-verify
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
**Endpoint:** /api/auth/token-renew
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6IlVzZXIgTmFtZSIsImVtYWlsIjoidXNlckBleGFtcGxlLmNvbSJ9.QhLZN-qhxu2Gd_0cNi8i0u_X58Skg3s0o_i2WXbvBYg"
}
```

**Регистрация пользователя**

**Метод:** POST
**Endpoint:** /api/auth/register
**Запрос:**
```json
{
  "first_name": "New",
  "last_name": "User",
  "username": "newuser",
  "email": "newuser@example.com",
  "phone": "+79371707130",
  "password": "secretpassword"
}
```
**Ответ:**
```json
{
  "id": 2,
  "first_name": "New",
  "last_name": "User",
  "username": "newuser",
  "email": "newuser@example.com",
  "phone": "+79371707130"
}
```


**Альбомы**

**Получение списка альбомов, ассоциированных с идентификатором пользователя**

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
    "name": "Album 1",
    "user_id": 1
  },
  {
    "id": 2,
    "name": "Album 2",
    "user_id": 1
  }
]
```

**Получение альбома по идентификатору**

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
  "name": "Album 1",
  "description": "This is album 1.",
  "user_id": 1
}
```

**Создание альбома**

**Метод:** POST
**Endpoint:** /api/albums
**Заголовки:**
```
Authorization: Bearer <token>
```
**Запрос:**
```json
{
  "name": "New Album",
  "description": "This is a new album."
}
```
**Ответ:**
```json
{
  "id": 3,
  "title": "New Album",
  "description": "This is a new album.",
  "creator_id": 1
}
```

**Редактирование альбома**

**Метод:** PUT
**Endpoint:** /api/albums/<album_id>
**Заголовки:**
```
Authorization: Bearer <token>
```
**Запрос:**
```json
{
  "title": "Edited Album",
  "description": "This is an edited album."
}
```
**Ответ:**
```json
{
  "id": 3,
  "title": "Edited Album",
  "description": "This is an edited album.",
  "creator_id": 1
}
```

**Удаление альбома**

**Метод:** DELETE
**Endpoint:** /api/albums/<album_id>
**Заголовки:**
```
Authorization: Bearer <token>
```



**Телеграммы**

**Получение списка телеграмм**

**Метод:** GET
**Endpoint:** /api/telegrams
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
[
  {
    "id": 1,
    "title": "My Telegram",
    "description": "This is my telegram.",
    "user_id": 1
  },
  {
    "id": 2,
    "title": "Another Telegram",
    "description": "This is another telegram.",
    "user_id": 1
  }
]
```

**Создание телеграммы**

**Метод:** POST
**Endpoint:** /api/telegrams
**Заголовки:**
```
Authorization: Bearer <token>
```
**Запрос:**
```json
{
  "title": "New Telegram",
  "description": "This is a new telegram."
}
```
**Ответ:**
```json
{
  "id": 3,
  "title": "New Telegram",
  "description": "This is a new telegram.",
  "user_id": 1
}
```

**Редактирование телеграммы**

**Метод:** PUT
**Endpoint:** /api/telegrams/<telegram_id>
**Заголовки:**
```
Authorization: Bearer <token>
```
**Запрос:**
```json
{
  "title": "Edited Telegram",
  "description": "This is an edited telegram."
}
```
**Ответ:**
```json
{
  "id": 3,
  "title": "Edited Telegram",
  "description": "This is an edited telegram.",
  "user_id": 1
}
```

**Удаление телеграммы**

**Метод:** DELETE
**Endpoint:** /api/telegrams/<telegram_id>
**Заголовки:**
```
Authorization: Bearer <token>
```

**Ответ:**
```json
{
  "message": "Telegram deleted."
}
```

**Ответ:**
```json
{
  "message": "Album deleted."
}
```

**Пользователи**

**Получение списка пользователей**

**Метод:** GET
**Endpoint:** /api/users
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