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

## Запуск Minikube
```shell
minikube start --vm-driver=docker
minikube status
```

## Установка Addons
```shell
minikube addons list
minikube addons enable ingress
minikube addons enable ingress-dns
```

## Установка nginx-ingress
```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```
## Проверям установку.
```shell
kubectl get pods -n ingress-nginx
kubectl get svc -n ingress-nginx
```

## Запуск tunnel, dashboard
```shell
minikube dashboard
minikube tunnel
```

## Деплой приложения
[deploy.yaml](manifest%2Fdeploy.yaml)

[ingress.yaml](manifest%2Fingress.yaml)

[service.yaml](manifest%2Fservice.yaml)
```shell
kubectl apply -f 03-hw/manifest/.
```


## Проверка работоспособности
```shell
curl http://arch.homework/health
```

## Удаление
```shell
kubectl delete -f 03-hw/manifest/.

minikube delete --purge
```