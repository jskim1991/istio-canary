#!/bin/bash

istioctl kube-inject -f workload.yaml > workloadv2-inject.yaml
kubectl create -f workloadv2-inject.yaml
kubectl create -f istio-gateway.yaml
kubectl create -f canary-msg.yaml
