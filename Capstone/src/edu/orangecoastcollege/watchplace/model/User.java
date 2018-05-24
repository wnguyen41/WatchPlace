package edu.orangecoastcollege.watchplace.model;
/**
 * User class contains the basic information detailing a user of this app.
 * @author Winston Nguyen
 *
 */
public class User {
	private int mId;
	private String mName;
	private String mEmail;
	private String mPassword;

	private String mBillingAddress;
	private String mShippingAddress;
	/**
	 * Initializes a new User.
	 * @param id the id number
	 * @param name the user's name
	 * @param email the user's email
	 * @param password the user's password
	 * @param billing the user's billing address.
	 * @param shipping the user's shipping address.
	 */
	public User(int id, String name, String email, String password, String billing, String shipping) {
		super();
		mId = id;
		mName = name;
		mEmail = String.valueOf(email.charAt(0)).toUpperCase().concat(email.substring(1));
		mPassword = password;
		mBillingAddress = billing;
		mShippingAddress = shipping;
	}
	/**
	 * Gets the billing address.
	 * @return String of billing address.
	 */
	public String getBillingAddress() {
		return mBillingAddress;
	}
	/**
	 * Sets the billing address
	 * @param billingAddress new billing address
	 */
	public void setBillingAddress(String billingAddress) {
		mBillingAddress = billingAddress;
	}
	/**
	 * Gets the shipping address.
	 * @return String of shipping address.
	 */
	public String getShippingAddress() {
		return mShippingAddress;
	}
	/**
	 * Sets the shipping address.
	 * @param shippingAddress new shipping address
	 */
	public void setShippingAddress(String shippingAddress) {
		mShippingAddress = shippingAddress;
	}
	/**
	 * Gets the password.
	 * @return String of password
	 */
	public String getPassword() {
		return mPassword;
	}

	

	/**
	 * Gets the name.
	 * @return String of name.
	 */
	public String getName() {
		return mName;
	}
	/**
	 * Sets the name.
	 * @param name new name
	 */
	public void setName(String name) {
		mName = name;
	}
	/**
	 * Gets the email.
	 * @return String of email.
	 */
	public String getEmail() {
		return mEmail;
	}
	/**
	 * Sets the email to new email.
	 * @param email new email.
	 */
	public void setEmail(String email) {
		mEmail = email;
	}
	/**
	 * Gets the id.
	 * @return int of id.
	 */
	public int getId() {
		return mId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (mEmail == null) {
			if (other.mEmail != null)
				return false;
		} else if (!mEmail.equals(other.mEmail))
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return mEmail.substring(0,mEmail.indexOf("@"));
	}
	


	
}
