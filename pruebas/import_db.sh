#!/bin/bash

sql_file="./src/main/resources/db/pruebas.sql"
db_file="./pruebas.db"

if ! command -v sqlite3 &> /dev/null; then
    echo "SQLite3 no está instalado"
    read -p "¿Desea instalarlo? (y/n): " install_sqlite3
    if [ $install_sqlite3 == "y" ]; then
        sudo apt-get install sqlite3
    else
        exit 1
    fi
fi

if [ ! -f "$sql_file" ]; then
    echo "No se encontró el archivo $sql_file"
    exit 1
fi

echo "Creando base de datos $db_file"
sqlite3 $db_file < $sql_file
echo "Base de datos $db_file cargada"
