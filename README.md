socializer.library
==============

Simple and easy to use library to scrape contacts from your Android phone for you to use in your apps.

PhoneContact is a class containing (almost) all info an Android contact can have.
* displayName : String //Contains both first and last name if the contact has them
* contactPhoto : Bitmap
* emailAddressses : Map<String, String>(emailAddressType, emailAddress)
* phoneNumbers : Map<String, String> (phoneNumberType, phoneNumber)

<b>Setup</b>

Add
```
compile 'com.leslieaerts:socializer:0.1.1@aar'
```
to your project.

<b>Using socializer</b>

Create a Socializer object in your project:
```java
Socializer soc = new Socializer(context);
``` 

The socializer starts a secondary thread automatically, which loads all contacts in the background.

Implement the listener to listen to events from this thread.

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

Once all contacts are loaded, no further events will be fired. You can simply use the method getAllPhoneContacts() to get back the loaded list with all contacts. The socializer object keeps the list of contacts loaded, but the method will block if the loading thread hasn't finished loading yet. You can use isDoneLoadingContacts() if you prefer not to wait for the blocking method and check if the contacts are available already.

```java	
if(soc.isDoneLoadingContacts) {
	List<PhoneContact> contacts = soc.getAllPhoneContacts();
}
``` 
Alternatively, you can use a filter for the name to obtain a specific contact list, filtered with the filterString.
```java	
List<PhoneContact> filteredContacts = soc.getFilteredContacts(filterString);
```

