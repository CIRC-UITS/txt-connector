# Steps to obtain certificates

### 1. Certificate connector X:
`openssl req -x509 -newkey rsa:4096 -keyout connectorX-key.pem -out connectorX.pem -sha256 -out connectorX.crt`


### 2. Certificate conversion
- 2.1. You get the .p12 file from the .crt file and the .key file

    `openssl pkcs12 -export -out connectorX.p12 -inkey connectorX.key -in connectorX.crt -passout pass:password`

- 2.2. From .p12 to .cert

    `openssl pkcs12 -in connectorX.p12 -out connectorX.cert -nokeys -nodes -passin pass:password`


### 3. Client id generation
Cliend Id connector X and .jks file (must be mounted in connector): `./generate_ski_aki.sh connectorX.p12 password`

### 4. Insert certificates (.cert) in the daps/resources/keys folder

### 5. Enter _client_id_ in the DAPS configuration file (clients.yml file)

### 6. In keys/clients enter certificates with base64 encoding as file name

