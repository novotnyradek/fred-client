[![FRED](https://fred.nic.cz/documentation/html/_static/fred-logo.png)](https://fred.nic.cz)
# fred-client 
> A Java EPP client for FRED (Free Registry for ENUM and Domains)

FRED is open-source software for running a domain and ENUM Registry, developed by CZ.NIC, the .CZ and 0.2.4.e164.arpa domain Registry.

Documentation for the whole FRED project is available on-line, visit https://fred.nic.cz/documentation.

[![Build Status](https://travis-ci.com/novotnyradek/fred-client.svg?branch=master)](https://travis-ci.com/novotnyradek/fred-client)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/novotnyradek/fred-client/master/LICENSE)

Version: 0.1.0
* Possibility to connect to epp.demo.regtest.nic.cz from main method
* Done 25 use cases out of 45
> Completed:
* Session management commands
    * Login
    * Logout
    * Hello
* Query commands
    * Check
    * Info
* Transform commands
    * Create
* Custom commands
    * Listing