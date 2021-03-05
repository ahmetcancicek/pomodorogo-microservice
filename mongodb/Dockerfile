FROM mongo:3.6

COPY init.sh /docker-entrypoint-initdb.d/init.sh

RUN \
    chmod +x /docker-entrypoint-initdb.d/init.sh
