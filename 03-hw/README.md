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

## 

```shell
minikube start --vm-driver=docker

 minikube status
 
 minikube dashboard

kubectl apply -f 03-hw/manifest/.

kubectl delete -f 03-hw/manifest/.

```

kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.1/deploy/static/provider/baremetal/deploy.yaml
kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.13.10/config/manifests/metallb-native.yaml