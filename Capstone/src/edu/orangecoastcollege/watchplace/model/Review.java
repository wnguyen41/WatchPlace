package edu.orangecoastcollege.watchplace.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
	
	/**
	 * Review class that contains review, rate, and user's name that added this review.
	 * @author YB Kim
	 */
	
	private int mId;
	private String mReview;
	private double mRate;
	private String mUserName;
	/**
	 * Initializes a new Review
	 * @param mId
	 * @param mReview
	 * @param mRate
	 */
	public Review(int mId, String mReview, double mRate, String mUserName) {
		super();
		this.mId = mId;
		this.mReview = mReview;
		this.mRate = mRate;
		this.mUserName = mUserName;
	}
	/**
	 * Gets the id
	 * @return the mId
	 */
	public int getId() {
		return mId;
	}
	
	/**
	 * Gets the review
	 * @return the mReview
	 */
	public String getReview() {
		return mReview;
	}
	/**
	 * Sets the review
	 * @param mReview the mReview to set
	 */
	public void setReview(String mReview) {
		this.mReview = mReview;
	}
	/**
	 * Gets the rate
	 * @return the mRate
	 */
	public double getRate() {
		return mRate;
	}
	/**
	 * Sets the rate
	 * @param mRate the mRate to set
	 */
	public void setRate(double mRate) {
		this.mRate = mRate;
	}
	
	
	/**
	 * Gets the username
	 * @return the mUserName
	 */
	public String getUserName() {
		return mUserName;
	}
	/**
	 * Sets the username
	 * @param mUserName the mUserName to set
	 */
	public void setUserName(String mUserName) {
		this.mUserName = mUserName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		long temp;
		temp = Double.doubleToLongBits(mRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mReview == null) ? 0 : mReview.hashCode());
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
		Review other = (Review) obj;
		if (mId != other.mId)
			return false;
		if (Double.doubleToLongBits(mRate) != Double.doubleToLongBits(other.mRate))
			return false;
		if (mReview == null) {
			if (other.mReview != null)
				return false;
		} else if (!mReview.equals(other.mReview))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		
		return "Review " + mId + " by " + mUserName + " : " + dateFormat.format(date);
	}

}
