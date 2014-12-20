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

    Socializer soc = new Socializer(context);
    
There are two ways to obtain contact persons.

1. Implement the listener to load contacts asynchronously.
	
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
	
2. Call the (blocking method) getAllPhoneContacts() to get back a list with all contacts.

	List<PhoneContact> contacts = soc.getAllPhoneContacts();

Alternatively, you can use a filter for the name to obtain a specific contact list, filtered with the filterString.

	List<PhoneContact> filteredContacts = soc.getFilteredContacts(filterString);


