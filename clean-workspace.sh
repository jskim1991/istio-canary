#!/bin/bash
kubectl delete deployment --all
kubectl delete svc --all
kubectl delete gateway --all
kubectl delete virtualservice --all
kubectl delete destinationrule --all
