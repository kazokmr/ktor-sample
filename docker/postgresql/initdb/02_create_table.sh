#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 -U 'exposed' -d 'exposed' <<<-EOSQL

CREATE TABLE IF NOT EXISTS member (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)  NOT NULL
);

EOSQL