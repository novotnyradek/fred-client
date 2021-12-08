ChangeLog
=========

2.4.2 (2021-09-08)
------------------

* Add new contact states to inform that some of the contact attributes are locked and cannot be changed

2.4.1 (2021-06-09)
------------------

* Add optional ``authinfo`` parameter to ``info_contact`` command
  to pass contact authorization information in request.
  If it is not provided or if it is invalid, server policy determines
  if the command is rejected or what data in the response will be returned
  to the client.

2.4.0 (2017-11-09)
------------------

* add extra address extension to support additional contact
  address types (for now it's just mailing address)

  * can be used / appear in following operations:

    * create contact
    * update contact (set/remove)
    * response of info contact request

2.3.2 (2012-10-28)
------------------

* update contact.xsd, domain.xsd, nsset.xsd, keyset.xsd

  * add poll message about object update (info data about
    new and old object)
  * fix number of visible states

* update contact.xsd, nsset.xsd, keyset.xsd

  * new object handles restricted by type objIDCreateType

* update contact.xsd

  * new state can appear in info result:

    * validatedContact

  * disclose address change enabled in update contact command

    * more checks done in registry

2.3.1 (2012-04-30)
------------------

* update contact.xsd

  * info result can contain addr in disclose flags
  * new states can appear in info result:

    * conditionallyIdentifiedContact
    * identifiedContact
    * deleteCandidate

* update domain.xsd

  * new states can appear in info result:

    * deleteCandidate

* update nsset.xsd

  * new states can appear in info result:

    * deleteCandidate

* update keyset.xsd

  * new states can appear in info result:

    * deleteCandidate

2.3.0 (2011-06-08)
------------------

* update fred.xsd from 1.4 to 1.5

  * added requestFeeInfo poll message datatype

* update fredcom.xsd from 1.1 to 1.2

  * changed objIDType (contact, nsset, keyset handle type)
    minimal lenght to 1

* valExDate element is optional in enumval extension
* update all.xsd from 2.2 to 2.3

2.2.0 (2009-11-26)
------------------

* update contact.xsd from 1.5 to 1.6

  * emailType replaced by emailCommaListType
  * emailUpdType replaced by emailUpdCommaListType

* update keyset.xsd from 1.2 to 1.3

  * dsrecords completely removed from keyset schema

* update enumval.xsd from 1.1 to 1.2

  * boolean 'publish` flag added to enumval extension for enum domains
    identifying whether or not enum domain should be visible in enum
    phone number dictionary

* update all.xsd from 2.1 to 2.2

  * due to changes above

2.1.0 (2008-10-19)
------------------

* updateded keyset.xsd from 1.1 to 1.2

  * new types dnskeyT (DNSKEY RR) and keyT (key data)
  * changing structures crType, addType, infDataType to add dnskey
  * removing lower limits for ds records to allow keyset withou ds
  * adding upper limits to tech contacts

* update fred.xsd from 1.3 to 1.4

  * removing reference to keyset schema (not needed)

* update all.xsd from 2.0 to 2.1

  * changing reference to keyset.xsd and fred.xsd to new versions

2.0.0 (2008-08-13)
------------------

* fixing removal of keysetByDs function in fred-1.3.xsd. schemas
  were invalid.
* updating overall schema version number to 2.0
* secDNS.xsd removed (it was never used)

2008-08-07
----------

* schema for keysets added: functions check, create, delete, info,
  transfer, update, list, sendAuthInfo, test. Keyset contains
  a set of DS records which can be modified by the update function.
  In case of deleting DS records the specific records are identified
  by the entire content (same type as for adding)
* in contact schema: postalInfoReadType and addrReadType added.
  postalInfo in infoDataType changed to the type postalInfoReadType.
  Fields name and addr in response to info_contact command are
  not mandatory now.
* keyset functions added to domain schema: listKeysets, domainsByKeyset,
  keysetsByContact, keysetsByDs
* new command keysetByNs in fred schema

1.4.2 (2006-07-31)
------------------

* Removing of disclose options for (name,org,address).
* New status flag serverRegistrantChangeProhibited for domain
  was added.
* Street and PC(postal code) were made *required* in Contact.
* New poll message about low credit was added to fred.
* Version was bumped up on contact, fred and "all" schemas.

1.4.1 (2006-06-26)
------------------

* New status flags for domain object were added (serverBlocked,
  serverOutzoneManual, serverInzoneManual, expired, outzone,
  notValidated).
* Identification type "birth number" (in czech: rodne cislo) was
  removed.

1.4.0 (2006-05-16)
------------------

* It's possible to control disclosure of vat, ident and notifyEmail
  attributes of contact in addition to previously supported attributes.
* New ident type 'birthday' (date of birth) was added.
* New domain specific poll message informing client about removal
  of domain from register.
* There are more usefull comments in schemas.
* New info functions (listDomains, listContacts, listNssets,
  domainsByNsset, domainsByContact, nssetsByContact, nssetsByNs,
  getResults) in schema fred-1.0 were implemented. Old list functions,
  which were in object specific schemas, are deprecated and will be
  removed soon.

1.3.1 (2006-05-16)
------------------

* New email pattern was defined. Now every email address has to
  fulfill quite basic criteria.
* NotifyEmail could not be deleted, because it was not allowed to
  enter empty NotifyEmail tag in update. This is fixed now.
* Inteface for technical checks has changed. The name element
  may be specified multipletimes and is optional. Nsset's report
  level can be overriden by level element. Poll message, which
  is the answer to technical check, contains additional element
  testid which is clTRID of technical check request and name of
  the test in result structure was renamed to testname.
* In order to ease migration from old to new register, new contact
  type 'tempcontact' was introduce. This contact can be only deleted
  and queried by info-domain command.
* Element oldID was substituted by element clID in transfer poll
  message. clID is ID of new registrar owning the object.
* Definition of poll messages' structure informing client about
  impending expiration, expiration, dns zone outage of domain object;
  validation expiration and impending validation expiration of ENUM
  domain object; deletion of not used contact and deletion of not used
  nsset.
* Version was bumped up on domain, contact, nsset and "all" schemas.

1.2.0 (2006-01-02)
------------------

* New foundation for extending EPP protocol by new commands was layed
  out. The new commands have similar structure as classic EPP commands,
  but they are nested in 'extension' element. The foundation is defined
  in fred-1.0.xsd.
* Type of domain registrant in update was corrected.
* New command for sending of authinfo to an owner of object was
  defined. This change is part of new model of transfer.
* Since now a client is not able to set status on objects. The schemas
  were adapted to this change.
* ssn element was renamed to 'ident'. ssn element was left in
  schemas for compatibility reasons, but its use is discouraged.
* Technical contact is now mandatory when creating nsset.
* ExDate is not of type datetime but just date.
* authInfo type is now mandatory when transfering an object.
* authInfo element was simplified and now contains directly
  the plain text password instead of subelement identifying type
  of password.
* The not used feature of entering authinfo in command info was
  canceled. AuthInfo element in info command is forbidden.
* New command for invocation of technical check on nsset was added.
  The results of technical check are returned asynchronously by means
  of poll message. The structure of poll message was defined too.
* New element 'reportlevel' was added to nsset schema. Element sets
  level of technical tests performed on nsset and is part of create,
  update and info commands on nsset.
* Structure of new poll message was defined * message about completed
  transfer.
* Version on all changed schemas was bumped up. This is first time
  we changed version of schemas.

1.1.1 (2006-09-26)
------------------

* authInfo element is optional since now upon creation of object.
  In case that it is empty, the server will generate random authinfo
  for object.
* pwAuthInfoType has now limited length to 300 characters. This was
  accomplished by move of definition from eppcom to fredcom schema.

1.1.0 (2006-09-21)
------------------

* This ChangeLog was created in order to document changes in schemas.
* Official specification of register says, that there can be from 2
  to 10 nameservers in nsset. The upper bound was 9 till now, so it
  was incremented by one.
* New schema fredcom-1.0.xsd was introduced in order to gather
  datatypes common for multiple schemas. It is an equivalent of
  eppcom-1.0, but since this schema is part of EPP standard, it cannot
  be modified.
* Data type of nsset and contact id was changed. Original type from
  eppcom was to short. New type 'objIDType' is defined in fredcom.
  As consequence the type of client's identifier is no more the same
  as identifier of object.
* Not used parts inherited from original standard schemas, which were
  commented out, were deleted, in order to prevent confusion when
  reading the schemas.
