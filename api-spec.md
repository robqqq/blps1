# GET /categories
+ Response 200 (application/json)

    [{"id":0,"name":"Авто"},{"id":1,"name":"Биология"},{"id":2,"name":"Генетика"}]

# GET /subjects
+ Response 200 (application/json)

    [{"id":0,"name":"Авиадвигателестроение"},{"id":1,"name":"Безопасность жизнедеятельности"},{"id":2,"name":"Высшая математика"}]

# GET /articles/0
+ Response 200 (text/html)

# GET /articles/category/0
+ Response 200 (application/json)

    [{"id":0,"name":"Я цифра – модификация"},{"id":1,"name":"Жидкостей автомобильного транспорта"}]

# GET /articles/subject/0
+ Response 200 (application/json)

    [{"id":4,"name":"Общие сведения о технологии авиадвигателестроения"}]

# GET /articles/random
+ Response 200 (text/html)

# POST /order/new
+ Request (text/plain)

    Пример ТЗ

+ Response 200 (application/json)

  {"id": 502, "description": "Пример ТЗ", "status": "UNPAID", "deadline": null, "worker": null}

# POST /payment
+ Request (application/json)

  {
    "cardNumber": "1234 1234 1234 1234",
    "cardExpiration": "09.2025",
    "cardName": "Name Surname",
    "orderId": 502,
    "sum": 2000
  }
+ Response 200

  {
  "id": 102,
  "cardNumber": "1234 1234 1234 1234",
  "cardExpiration": "09.2025",
  "cardName": "Name Surname",
  "orderId": 502,
  "sum": 2000.0,
  "resolved": false
  }

# GET /order/502
+ Response 200

  {
    "id": 502,
    "description": "Пример ТЗ",
    "status": "UNPAID",
    "deadline": null,
    "worker": null
  }

# GET /payment/102
+ Response 200

  {
    "id": 102,
    "cardNumber": "1234 1234 1234 1234",
    "cardExpiration": "09.2025",
    "cardName": "Name Surname",
    "orderId": 502,
    "sum": 2000.0,
    "resolved": false
  }

# PUT /payment/accept?paymentId=152
+ Response 200

  {
    "id": 1,
    "description": "Пример ТЗ",
    "status": "AT_WORK",
    "deadline": "2024-06-22",
    "worker": {
      "id": 3,
      "name": "Worker3",
      "busy": true
    }
  }

# PUT /payment/decline?paymentId=153
+ Response 200

  {
    "id": 2,
    "description": "Пример ТЗ",
    "status": "DECLINED",
    "deadline": null,
    "worker": null
  }

# PUT /order/ready?orderId=1
+ Response 200

  {
    "id": 1,
    "description": "Пример ТЗ",
    "status": "READY",
    "deadline": "2024-06-22",
    "worker": {
      "id": 3,
      "name": "Worker3",
      "busy": false
    }
  }
