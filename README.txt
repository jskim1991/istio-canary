Install istio into k8s
$ kubectl create -f install/kubernetes/istio-demo.yaml

List the running components installed into k8s
$ kubectl get pod -n istio-system

proxy-config to retrieve current mesh config
$ kubectl run -i --rm --restart=Never dummy --image=tutum/curl:alpine \
-n istio-system --command \
-- curl -v 'http://istio-pilot.istio-system:8080/v1/registration'

To create configuration stored in file named 'catalog-service.yaml', one of the two commands can be used.
$ istioctl create -f catalog-service.yaml
$ kubectl create -f catalog-service.yaml

== Chapter 2 ==
To create a namespace in k8s
$ kubectl create namespace istioinaction
$ kubectl config set-context $(kubectl config current-context) \
 --namespace=istioinaction

Before we deploy this, we want to inject the Istio service proxy so that this service can participate in the service mesh
$ istioctl kube-inject -f services/catalog/catalog-deployment.yaml
* kube-inject command takes a k8s resource file and enriches it with the sidecar deployment of the Istio proxy. kube-inject adds another container to pod declaration.
$ kubectl create -f <(istioctl kube-inject -f catalog-deployment.yaml)

Query the catalog service within the k8s cluster
$ kubectl run -i --rm --restart=Never dummy \
--image=dockerqa/curl:ubuntu-trusty --command \
-- sh -c 'curl -s catalog:8080/api/catalog'

GRAFANA
$ GRAFANA=$(kubectl -n istio-system get pod | grep -i running | \
grep grafana | cut -d ' ' -f 1)
$ kubectl port-forward -n istio-system $GRAFANA 8080:3000

OPEN TRACING
$ TRACING=$(kubectl -n istio-system get pod | grep istio-tracing | cut -d ' ' -f 1)
$ kubectl port-forward -n istio-system $TRACING 8181:16686

== INGRESS GATEWAY ==

istioctl proxy-config listener $INGRESS_POD -n istio-system
istioctl proxy-config route $INGRESS_POD -o json -n istio-system


$  kubectl create namespace istioinaction
$  kubectl config set-context $(kubectl config current-context) \
 --namespace=istioinaction

$  kubectl delete deployment --all
$  kubectl delete svc --all
$  kubectl delete gateway --all
$  kubectl delete virtualservice --all
$  kubectl delete destinationrule --all

==== canary app ====

To build:
$ mvn clean install dockerfile:build

To push:
$ docker push guykorean/hello-kube:2.0
$ docker push guykorean/hello-msg:1.0
$ docker push guykorean/hello-msg:2.0

To run:
$ docker run -d -p 8080:8080 --name hello-kube guykorean/hello-kube:2.0
$ docker run -d -p 8080:8080 --name hello-msg guykorean/hello-msg:2.0
$ docker run -d -p 8080:8080 --name hello-msg guykorean/hello-msg:2.0

To stop:
$ docker rm -f hello-msg

./clean-workspace.sh
istioctl kube-inject -f workload.yaml > workloadv2-inject.yaml
kubectl apply -f workloadv2-inject.yaml
kubectl apply -f istio-gateway.yaml
kubectl apply -f canary-msg.yaml

curl -X GET http://localhost/kube-canary-app/welcome
curl -X GET http://localhost/kube-canary-app/init

istioctl proxy-config route $INGRESS_POD -o json -n istio-system


## Kiali ##
kubectl port-forward \
    $(kubectl get pod -n istio-system -l app=kiali \
    -o jsonpath='{.items[0].metadata.name}') \
    -n istio-system 20001
