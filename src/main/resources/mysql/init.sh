#!/bin/bash

# Esperar a que MySQL esté listo
while ! mysqladmin ping -h"$MYSQL_HOST" --silent; do
    sleep 1
done

# Ejecutar init.sql
mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /docker-entrypoint-initdb.d/init.sql

# Ejecutar initData.sql
mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /docker-entrypoint-initdb.d/initData.sql
