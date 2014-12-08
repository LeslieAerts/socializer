socializer.library
==============

Simple and easy to use library to scrape contacts from your Android phone for you to use in your apps.

<b>How to use</b>

//Adding to gradle explanation goes here

Create a Socializer object in your project:

    Socializer soc = new Socializer(context);
    List<PhoneContact> contacts = soc.getAllPhoneContacts();

PhoneContact is a class containing (almost) all info an Android contact can have.
* firstName : String
* lastName : String
* contactPhoto : Bitmap
* emailAddressses : Map<String, String>(emailAddressType, emailAddress)
* phoneNumbers : Map<String, String> (phoneNumberType, phoneNumber)
