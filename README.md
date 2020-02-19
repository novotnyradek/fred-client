[![FRED](https://fred.nic.cz/documentation/html/_static/fred-logo.png)](https://fred.nic.cz)
# fred-client 
> A Java EPP client for FRED (Free Registry for ENUM and Domains)

FRED is open-source software for running a domain and ENUM Registry, developed by CZ.NIC, the .CZ and 0.2.4.e164.arpa domain Registry.

Documentation for the whole FRED project is available on-line, visit https://fred.nic.cz/documentation.

[![Build Status](https://travis-ci.com/novotnyradek/fred-client.svg?branch=master)](https://travis-ci.com/novotnyradek/fred-client)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.novotnyradek/fred-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.novotnyradek/fred-client)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/novotnyradek/fred-client/master/LICENSE)
[![Javadocs](https://www.javadoc.io/badge/io.github.novotnyradek/fred-client.svg)](https://www.javadoc.io/doc/io.github.novotnyradek/fred-client)

**Version: `0.2.3`**
> Changes
###### 0.2.3
* Add option turn on validation of responses and requests separately
* Add support for multithread systems
* Other small improvements and fixes
###### 0.2.2
* RELEASED to central repository.
* More mapping tests.
* Created poll responses pojos for contact, keyset and nsset update.
* Regenerated class diagram.
###### 0.2.1
* Generated class diagram.
* Changed schemas to accept object update poll message.
###### 0.2.1
* Created remaining methods (without object update poll message yet).
* Possibility to configure client from outside, via properties file, see Settings section.
* Possibility to turn off validation of requests and responses (via properties file).
* Speed improvements, sharing one connection to client.
* And much more.
###### 0.1.0
* Removed clientTransactionId attribute from constructors. Can be set using setClientTransactionId method on any request, otherwise will be generated automatically.
* First version.
* Possibility to connect to epp.demo.regtest.nic.cz from main method or your application

> Commands
* Session management commands
    * Login
    * Logout
* Query commands
    * Check
    * Info
    * Update
    * Polling
* Transform commands
    * Create
    * Transfer
    * Delete
    * Renew domain
* Custom commands
    * Credit info
    * Send authInfo
    * Test nsset
    * Listing

> Installation

Add as maven dependency to your project.

```xml
<dependency>
    <groupId>io.github.novotnyradek</groupId>
    <artifactId>fred-client</artifactId>
    <version>0.2</version>
</dependency>
```

> Settings

Customize `fred-client.properties` file. You have to provide properties file when initiating client.

```properties
###############################################################################
#                     FRED Client Configuration File                          #
###############################################################################
# This is an example of configuration.
#
# Connection settings
# Open instance registrar account
# The client identifier
apiKey.id = REG-FRED_B
# The client’s plain-text password
apiKey.secret = passwd

# Server name
host = epp.demo.regtest.nic.cz
# Server port
port = 700
# Socket timeout (miliseconds)
timeout = 20000

# SSL Properties
# Path to Java keystore with private and public certificate
certificate.file = conf/fred.jks
# Java keystore password
certificate.secret = changeit
# Other settings
keystore.instance = JKS
sslsocket.instance = TLSv1.2
keymanager.instance = SunX509

# Enable/disable the validation of messages (true enabled, false disabled) - for requests and responses
schema.validation.request = true
schema.validation.response = false
```

> Creating your own java keystore file
* Please read https://www.nic.cz/page/744/registracni-system/.
* At first you need your own private certificate and key and public certificate of the server. For open instance you can use this private key https://www.nic.cz/files/nic/doc/pristupy_openinstance.zip.
* Credits to https://www.wowza.com/docs/how-to-import-an-existing-ssl-certificate-and-private-key#convert-the-certificate-and-private-key-to-pkcs-12.

###### Step 1 - Convert the certificate and private key to PKCS 12
`openssl pkcs12 -export -in Cert_openinstance.pem -inkey privatekey_openinstance.pem -out private_key.p12 -password pass:changeit`

###### Step 2 - Import the certificate to the keystore 
`keytool -importkeystore -srcstorepass changeit -deststorepass changeit -destkeystore fred.jks -srckeystore private_key.p12 -srcstoretype PKCS12`
###### Step 3 - Get server certificate
Note: works only for open instance environment - on production instance you'll get certificate from CZ.NIC.

`openssl s_client -connect epp.demo.regtest.nic.cz:443 2>/dev/null </dev/null |  sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > server.pem`

###### Step 4 - import downloaded certificate to keystore
`keytool -import -alias server -file server.pem -storepass changeit -keystore fred.jks`

> Usage
* Add as Maven dependency (or use .jar) to your project. Create instance of FredClient class, properties file as parameter.
* Feel free to call any command, no need to call login separately, because:
  * Every command checks for connection via hello EPP command,
  * if connection is not established, it creates new ssl connection to server,
  * proceeds login EPP command and try to login,
  * proceeds command you wanted.
* If you keep your current instance of FredClient class, it reuses created connection.

> Enjoy!
