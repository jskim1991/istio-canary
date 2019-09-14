#!/bin/bash

istioctl kube-inject -f workload.yaml > workloadv2-inject.yaml
kubectl apply -f workloadv2-inject.yaml
kubectl apply -f istio-gateway.yaml
kubectl apply -f canary-msg.yaml
