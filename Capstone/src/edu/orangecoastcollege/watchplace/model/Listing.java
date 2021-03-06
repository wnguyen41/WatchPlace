/**
 * 
 */
package edu.orangecoastcollege.watchplace.model;

/**
 * Listing class contains information of what a Listing contains.
 * @author Winston Nguyen
 *
 */
public class Listing {
	private int mId;
	private Watch mWatch;
	private User mUser;
	private int mQuantity;
	
	/**
	 * Instantiates a new Listing
	 * @param id the id
	 * @param watch the Watch
	 * @param user the User
	 * @param quantity the quantity
	 */
	public Listing(int id, Watch watch, User user, int quantity) {
		super();
		mId = id;
		mWatch = watch;
		mUser = user;
		mQuantity = quantity;
	}
	/**
	 * Gets the id.
	 * @return the id
	 */
	public int getId() {
		return mId;
	}
	/**
	 * Gets the Watch
	 * @return the watch
	 */
	public Watch getWatch() {
		return mWatch;
	}
	/**
	 * Gets the User.
	 * @return the user
	 */
	public User getUser() {
		return mUser;
	}
	/**
	 * Get the quantity.
	 * @return the quantity
	 */
	public int getQuantity() {
		return mQuantity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		result = prime * result + mQuantity;
		result = prime * result + ((mUser == null) ? 0 : mUser.hashCode());
		result = prime * result + ((mWatch == null) ? 0 : mWatch.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Listing other = (Listing) obj;
		if (mId != other.mId)
			return false;
		if (mQuantity != other.mQuantity)
			return false;
		if (mUser == null) {
			if (other.mUser != null)
				return false;
		} else if (!mUser.equals(other.mUser))
			return false;
		if (mWatch == null) {
			if (other.mWatch != null)
				return false;
		} else if (!mWatch.equals(other.mWatch))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append("[").append(mWatch).append(", Sold By: ").append(mUser).append(", Quantity: ").append(mQuantity).append("]").toString();
	}
	
	
}
