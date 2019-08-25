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
    * Update (wip)
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
* At first you need your own private certificate and public certificate of the server. For open instance you can use these certificates https://www.nic.cz/files/nic/doc/pristupy_openinstance.zip.

> Usage


