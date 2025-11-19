#!/bin/bash

# Ruta de trabajo (con comillas por los espacios en "Program Files")
RUTA="/Program Files/Git/SGRNPC"

echo "📂 Creando carpeta en: $RUTA"
mkdir -p "$RUTA"
chmod 777 "$RUTA"

# Crear la red personalizada con subnet (si no existe)
NETWORK_NAME="network-sic-rnpc"
SUBNET="172.18.0.0/24"

echo "🌐 Verificando red Docker..."
docker network inspect $NETWORK_NAME >/dev/null 2>&1 || \
docker network create --subnet=$SUBNET $NETWORK_NAME

# ============================
# Configuración del servicio
# ============================
IMAGE_NAME="sic-sgrnpc-api-unidades"
CONTAINER_NAME="sic-sgrnpc-api-unidades"

# IP fija dentro de la red (ajústala si ya usas esta en otro micro)
CONTAINER_IP="172.18.0.20"

# Puerto interno = server.port de unidadesApi
PORT_CONTAINER=8085

# Puerto externo en tu máquina (localhost)
PORT_DOCKER=8085

# Eliminar contenedor si existe
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "🛑 Deteniendo y eliminando contenedor $CONTAINER_NAME..."
    docker stop $CONTAINER_NAME >/dev/null 2>&1
    docker rm $CONTAINER_NAME >/dev/null 2>&1
fi
# Eliminar imagen si existe
if [ "$(docker images -q $IMAGE_NAME)" ]; then
    echo "🧹 Eliminando imagen $IMAGE_NAME..."
    docker rmi $IMAGE_NAME >/dev/null 2>&1
fi

# Construir imagen
echo "🔨 Construyendo la imagen $IMAGE_NAME..."
docker build -t $IMAGE_NAME . > docker_build.log 2>&1

# Iniciar contenedor con IP fija
echo "🚀 Iniciando contenedor $CONTAINER_NAME..."
docker run -d --restart always \
    -v "$RUTA:/SGRNPC" \
    --network $NETWORK_NAME \
    --ip $CONTAINER_IP \
    -p $PORT_DOCKER:$PORT_CONTAINER \
    --name $CONTAINER_NAME \
    $IMAGE_NAME


echo "🚀 Iniciando contenedor $CONTAINER_NAME..."

echo "✅ Contenedor $CONTAINER_NAME iniciado correctamente."
