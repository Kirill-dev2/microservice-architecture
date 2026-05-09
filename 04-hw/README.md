# Инфраструктурные паттерны

## Сделать простейший RESTful CRUD по созданию, удалению, просмотру и обновлению пользователей..

## Задача

* Сделать простейший RESTful CRUD по созданию, удалению, просмотру и обновлению пользователей.
* Добавить базу данных для приложения.
* Конфигурация приложения должна хранится в Configmaps.
* Доступы к БД должны храниться в Secrets.
* Первоначальные миграции должны быть оформлены в качестве Job-ы, если это требуется.
* Ingress-ы должны также вести на url arch.homework/ (как и в прошлом задании)


На выходе должны быть предоставлена

1. ссылка на директорию в github, где находится директория с манифестами кубернетеса (в виде pull request)
2. инструкция по запуску приложения.
   * команда установки БД из helm, вместе с файлом values.yaml.
   * команда применения первоначальных миграций
   * команда kubectl apply -f, которая запускает в правильном порядке манифесты кубернетеса
3. Postman коллекция, в которой будут представлены примеры запросов к сервису на создание, получение, изменение и удаление пользователя. Важно: в postman коллекции использовать базовый url - arch.homework.
4. Проверить корректность работы приложения используя созданную коллекцию newman run коллекция_постман и приложить скриншот/вывод исполнения корректной работы


## Предварительные требования [HW-03](..%2F03-hw)
1. Minikube запущен
2. nginx-ingress установлен
3. minikube tunnel запущен


## Сборка образа
docker build --platform linux/amd64 -t kornakovkv/otus-user-service:v1 -f user-service/Dockerfile .

## Публикация образа в dockerhub
```bash
docker login
docker push kornakovkv/otus-user-service:v1
```

## Установка PostgreSQL
```bash
# Установить PostgreSQL
kubectl apply -f 04-hw/manifest/postgres/.

# Проверить статус
kubectl get pods -l app=postgres
```
![1.png](img%2F1.png)

> #### Alt Helm
> ```bash
> helm install postgres oci://registry-1.docker.io/bitnamicharts/postgresql -f 04-hw/manifest/helm/values.yaml
> ```

## Применение ConfigMap и Secret
```bash
kubectl apply -f 04-hw/manifest/conf/.
```
![conf-map.png](img%2Fconf-map.png)
![conf-map-1.png](img%2Fconf-map-1.png)
![secret.png](img%2Fsecret.png)

## Деплой приложения
```bash
kubectl apply -f 04-hw/manifest/deploy/.
```
![service.png](img%2Fservice.png)

## Проверка
```bash
# Health check
curl http://arch.homework/health

# Создать пользователя
curl -X POST http://arch.homework/api/v1/user \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "firstName": "John", "lastName": "Doe", "email": "bestjohn@doe.com", "phone": "+71002003041"}'
```

## Тест коллекции Postman в Newman
[otus_microservice-architecture.postman_collection.json](otus_microservice-architecture.postman_collection.json)
```text

otus_microservice-architecture

→ /user
POST http://arch.homework/api/v1/user [200 OK, 123B, 43ms]

→ /user/userId
GET http://arch.homework/api/v1/user/1 [200 OK, 276B, 56ms]

→ /user/userId
DELETE http://arch.homework/api/v1/user/1 [200 OK, 123B, 16ms]

→ /user/userId
PUT http://arch.homework/api/v1/user/2 [200 OK, 123B, 8ms]

┌─────────────────────────┬──────────────────┬──────────────────┐
│                         │         executed │           failed │
├─────────────────────────┼──────────────────┼──────────────────┤
│              iterations │                1 │                0 │
├─────────────────────────┼──────────────────┼──────────────────┤
│                requests │                4 │                0 │
├─────────────────────────┼──────────────────┼──────────────────┤
│            test-scripts │                0 │                0 │
├─────────────────────────┼──────────────────┼──────────────────┤
│      prerequest-scripts │                0 │                0 │
├─────────────────────────┼──────────────────┼──────────────────┤
│              assertions │                0 │                0 │
├─────────────────────────┴──────────────────┴──────────────────┤
│ total run duration: 162ms                                     │
├───────────────────────────────────────────────────────────────┤
│ total data received: 112B (approx)                            │
├───────────────────────────────────────────────────────────────┤
│ average response time: 30ms [min: 8ms, max: 56ms, s.d.: 19ms] │
└───────────────────────────────────────────────────────────────┘
```

## Удаление
```bash 
# Удалить приложение
kubectl delete -f 04-hw/manifest/deploy/.

# Удалить ConfigMap и Secret приложения
kubectl delete -f 04-hw/manifest/conf/.

# Удалить PostgreSQL
kubectl delete -f 04-hw/manifest/postgres/.
```