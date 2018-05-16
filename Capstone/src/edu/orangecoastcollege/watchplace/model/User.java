package edu.orangecoastcollege.watchplace.model;

public class User {
	private int mId;
	private String mName;
	private String mEmail;
	private String mPassword;

	private String mBillingAddress;
	private String mShippingAddress;
	public User(int id, String name, String email, String password, String billing, String shipping) {
		super();
		mId = id;
		mName = name;
		mEmail = email;
		mPassword = password;
		mBillingAddress = billing;
		mShippingAddress = shipping;
	}
	public String getBillingAddress() {
		return mBillingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		mBillingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return mShippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		mShippingAddress = shippingAddress;
	}

	public String getPassword() {
		return mPassword;
	}

	


	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

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
		return "User [Id=" + mId + ", Name=" + mName + ", Email=" + mEmail + "]";
	}
	


	
}
