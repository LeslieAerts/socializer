socializer.library
==============

Simple and easy to use library to scrape contacts from your Android phone for you to use in your apps.

PhoneContact is a class containing (almost) all info an Android contact can have.
* firstName : String
* lastName : String
* contactPhoto : Bitmap
* emailAddressses : Map<String, String>(emailAddressType, emailAddress)
* phoneNumbers : Map<String, String> (phoneNumberType, phoneNumber)

<b>How to use</b>

//Adding to gradle explanation goes here

Create a Socializer object in your project:
```java
    Socializer soc = new Socializer(context);
``` 

<b>Configuration</b>

1. Implement the listener to load contacts asynchronously.
```java	
	soc.setContactListener(new ContactListener() {
	
			@Override
            public void onContactLoaded(PhoneContact contact) {
			//Gets called each time a contact is loaded.
			}
			
			@Override
            public void onAllContactsLoaded(List<PhoneContact> contacts) {
			//Gets called when socializer is finished loading.
			}
			
	});
```

This listener listens to events sent from the loading thread. If all contacts are loaded however, you can simply use the (blocking method) getAllPhoneContacts() to get back a list with all contacts. The socializer object keeps the list of contacts loaded, so this method will not block once the contacts are all loaded already. You can use isDoneLoadingContacts() if you prefer not to wait for the blocking method and check if the contacts are available already.
```java	
if(soc.isDoneLoadingContacts) {
	List<PhoneContact> contacts = soc.getAllPhoneContacts();
}
``` 
Alternatively, you can use a filter for the name to obtain a specific contact list, filtered with the filterString.
```java	
	List<PhoneContact> filteredContacts = soc.getFilteredContacts(filterString);
```

