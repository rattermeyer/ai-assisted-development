#!/bin/bash
psql -v ON_ERROR_STOP=1 --username pagila_admin -d "pagila" < /docker-entrypoint-initdb.d/0070-pagila.sql.dmp
