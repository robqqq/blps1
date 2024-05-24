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
