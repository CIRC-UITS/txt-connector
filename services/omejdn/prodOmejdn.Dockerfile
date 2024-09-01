FROM ghcr.io/fraunhofer-aisec/omejdn-server:1.6.0

COPY ./daps/resources/config /opt/config
COPY ./daps/resources/keys /opt/keys