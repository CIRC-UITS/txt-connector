#!/bin/bash

read -p "Enter certificate name: " cert_name

echo "Generation of certificate..."

openssl req -x509 -newkey rsa:4096 -keyout "${cert_name}.key" -out "${cert_name}.pem" -sha256 -out "${cert_name}.crt" -days 365

openssl pkcs12 -export -out "${cert_name}.p12" -inkey "${cert_name}.key" -in "${cert_name}.crt" -passout pass:password

openssl pkcs12 -in "${cert_name}.p12" -out "${cert_name}.cert" -nokeys -nodes -passin pass:password

./generate_ski_aki.sh "${cert_name}.p12" password

mkdir -p certs

mv "${cert_name}.key" "${cert_name}.jks" "${cert_name}.p12" "${cert_name}.cert" "${cert_name}.crt" certs/