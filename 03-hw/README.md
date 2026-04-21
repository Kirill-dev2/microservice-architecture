# Основы работы с Kubernetes

## Цель: В этом ДЗ вы научитесь создавать минимальный сервис.

## Задача
1. Создать минимальный сервис, который
   - Отвечает на порту 8000
   - Имеет HTTP метод: `GET /health/` → `{"status": "OK"}`

2. Cобрать локально образ приложения в докер.
   - Запушить образ в dockerhub
3. Написать манифесты для деплоя в k8s для этого сервиса.
   - Манифесты должны описывать сущности: Deployment, Service, Ingress.
   - В Deployment могут быть указаны Liveness, Readiness пробы.
   - Количество реплик должно быть не меньше 2. Image контейнера должен быть указан с Dockerhub.
   - Хост в ингрессе должен быть arch.homework. В итоге после применения манифестов GET запрос на http://arch.homework/health должен отдавать {“status”: “OK”}.

4. На выходе предоставить
   - ссылку на github c манифестами (в виде pull request). Манифесты должны лежать в одной директории, так чтобы можно было их все применить одной командой kubectl apply -f .
   - url, по которому можно будет получить ответ от сервиса (либо тест в postmanе).

## Запуск Minikube.
```shell
minikube start --driver=docker
minikube status
```
![status.png](img%2Fstatus.png)
## Установка Addons.
```shell
minikube addons list
minikube addons enable ingress
minikube addons enable ingress-dns
```
![addon.png](img%2Faddon.png)
## Проверям установку nginx-ingress.
```shell
kubectl get pods -n ingress-nginx
kubectl get svc -n ingress-nginx
```
![ingress.png](img%2Fingress.png)
## Запуск tunnel, dashboard.
```shell
minikube dashboard
minikube tunnel
```

## Деплой
* [deploy.yaml](manifest%2Fdeploy.yaml)
* [ingress.yaml](manifest%2Fingress.yaml)
* [service.yaml](manifest%2Fservice.yaml)
```shell
kubectl apply -f 03-hw/manifest/.
```
![deploy.png](img%2Fdeploy.png)

## Проверка работоспособности
```shell
curl http://arch.homework/health
```
```text
{"status":"UP","groups":["liveness","readiness"]}
```

## Удаление
```shell
kubectl delete -f 03-hw/manifest/.
minikube stop
```