[![FRED](https://fred.nic.cz/documentation/html/_static/fred-logo.png)](https://fred.nic.cz)
# fred-client 
> A Java EPP client for FRED (Free Registry for ENUM and Domains)

FRED is open-source software for running a domain and ENUM Registry, developed by CZ.NIC, the .CZ and 0.2.4.e164.arpa domain Registry.

Documentation for the whole FRED project is available on-line, visit https://fred.nic.cz/documentation.

[![Build Status](https://travis-ci.com/novotnyradek/fred-client.svg?branch=master)](https://travis-ci.com/novotnyradek/fred-client)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/novotnyradek/fred-client/master/LICENSE)

Version: 0.2.0 (0.2-SNAPSHOT)
* Possibility to connect to epp.demo.regtest.nic.cz from main method
* Done 38 use cases out of 45
> Completed:
* Session management commands (unable to call them separately)
    * Login
    * Logout
    * Hello
* Query commands
    * Check
    * Info
    * _Update (wip)_
    * Polling (without object update message)
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
    
> Changes
###### 0.2.0
* Created remaining methods (without object update poll message yet).
* Possibility to configure client from outside, via properties file, see Settings section.
* Possibility to turn off validation of requests and responses (via properties file).
* Speed improvements, sharing one connection to client.
* And much more.
###### 0.1.0
* Removed clientTransactionId attribute from constructors. Can be set using setClientTransactionId method on any request, otherwise will be generated automatically.
* First version.

> Installation (Not avaliable yet)

Add as maven dependency to your project.

```xml
<dependency>
    <groupId>cz.nic.clients</groupId>
    <artifactId>fred-client</artifactId>
    <version>0.2-SNAPSHOT</version>
</dependency>
```

> Settings

Customize `fred-client.properties` file. You have to provide properties file when initiating client.

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


