FROM nginx:latest

COPY ./daps/resources/nginx.conf /etc/nginx/templates/default.conf.template
COPY ./daps/resources/keys/TLS/daps.cert /etc/nginx/daps.cert
COPY ./daps/resources/keys/TLS/daps.key /etc/nginx/daps.key