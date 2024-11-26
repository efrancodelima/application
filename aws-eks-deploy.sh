#!/bin/sh
# Bourne-Again shell script, ASCII text executable

# Se o cluster EKS não existir, encerra o script e não faz o deploy
CLUSTER_NAME="lanchonete-eks-cluster"
aws eks describe-cluster --name $CLUSTER_NAME > /dev/null 2>&1

if [ $? -ne 0 ]; then
    echo "Não foi possível fazer o deploy: o EKS Cluster $CLUSTER_NAME não foi localizado na nuvem da AWS."
    exit 1
fi

# Se existir, continua o script e faz o deploy
# Pega a versão do projeto para uma variável de ambiente
export PROJECT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Muda o contexto do kubeconfig para o cluster EKS
aws eks update-kubeconfig --name lanchonete-eks-cluster

# Executa o deployment
envsubst < app-deployment.yaml | kubectl apply -f -

# Executa o service
kubectl apply -f app-service.yaml

# Pega o link do app-service e guarda em uma variável
SERVICE_URL=$(kubectl get svc app-service -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')

# Imprime a mensagem com o link do service
echo "Link direto para a aplicação: http://$SERVICE_URL:8080/api/v2/"
