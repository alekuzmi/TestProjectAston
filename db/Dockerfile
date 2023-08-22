FROM postgres:alpine
ADD scripts/init_schema.sql /docker-entrypoint-initdb.d
ADD scripts/add_atm.sql /docker-entrypoint-initdb.d
RUN chmod a+r /docker-entrypoint-initdb.d/*
EXPOSE 6666