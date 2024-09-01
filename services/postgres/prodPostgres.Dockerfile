FROM postgres:13

USER root
COPY --chown=postgres postgres.sh /docker-entrypoint-initdb.d/postgres.sh

USER postgres

