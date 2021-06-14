CREATE ROLE exposed WITH LOGIN PASSWORD 'exposed';
CREATE DATABASE exposed OWNER exposed template =template0 encoding ='utf-8' lc_collate ='C' lc_ctype ='C';
GRANT ALL PRIVILEGES ON DATABASE exposed TO exposed;