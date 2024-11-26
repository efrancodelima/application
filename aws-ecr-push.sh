#!/bin/sh
# Bourne-Again shell script, ASCII text executable

# Pega a versão do projeto para uma variável de ambiente
JAVA_HOME="/opt/hostedtoolcache/Java_Temurin-Hotspot_jdk/17.0.13-11/x64"
FULL_PATH_IMAGE="${AWS_ACCOUNT_ID}.dkr.ecr.us-east-1.amazonaws.com/app-lanchonete"
PROJECT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Verifica se a imagem com a tag já existe na AWS ECR
EXISTING_IMAGE=$(aws ecr describe-images --repository-name app-lanchonete --image-ids imageTag=$PROJECT_VERSION --query 'imageDetails[0].imageTags' --output text 2>/dev/null)

# Se existe lança um erro e encerra o script
if [ -n "$EXISTING_IMAGE" ]; then
    echo "Erro ao fazer o upload da imagem docker para o repositório da Amazon: \
    a versão $PROJECT_VERSION do projeto já existe no repositório ECR."
    exit 1
fi

# Coloca a tag na imagem docker (construída em um step anterior do action)
docker tag app-lanchonete $FULL_PATH_IMAGE:$PROJECT_VERSION

# Faz o upload da imagem docker para a AWS ECR
docker push $FULL_PATH_IMAGE:$PROJECT_VERSION
