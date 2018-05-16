package edu.orangecoastcollege.watchplace.model;

public class Watch {
	private int mId;
	private String mReference;
	private String mBrand;
	private String mName;
	private String mCaseMaterial;
	private String mCaseGlass;
	private String mCaseBackType;
	private String mCaseShape;
	private double mCaseDiameter;
	private double mCaseHeight;
	private double mCaseWaterResistance;
	private String mDialColor;
	private String mDialIndex;
	private String mMovement;
	private double mPrice;
	
	/**
	 * @param id
	 * @param reference
	 * @param brand
	 * @param name
	 * @param caseMaterial
	 * @param caseGlass
	 * @param caseBackType
	 * @param caseShape
	 * @param caseDiameter
	 * @param caseHeight
	 * @param caseWaterResistance
	 * @param dialColor
	 * @param dialIndex
	 * @param movement
	 * @param price
	 */
	public Watch(int id, String reference, String brand, String name, String caseMaterial, String caseGlass,
			String caseBackType, String caseShape, double caseDiameter, double caseHeight, double caseWaterResistance,
			String dialColor, String dialIndex, String movement, double price) {
		super();
		mId = id;
		mReference = reference;
		mBrand = brand;
		mName = name;
		mCaseMaterial = caseMaterial;
		mCaseGlass = caseGlass;
		mCaseBackType = caseBackType;
		mCaseShape = caseShape;
		mCaseDiameter = caseDiameter;
		mCaseHeight = caseHeight;
		mCaseWaterResistance = caseWaterResistance;
		mDialColor = dialColor;
		mDialIndex = dialIndex;
		mMovement = movement;
		mPrice = price;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return mId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		mId = id;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return mReference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		mReference = reference;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return mBrand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		mBrand = brand;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * @return the caseMaterial
	 */
	public String getCaseMaterial() {
		return mCaseMaterial;
	}

	/**
	 * @param caseMaterial the caseMaterial to set
	 */
	public void setCaseMaterial(String caseMaterial) {
		mCaseMaterial = caseMaterial;
	}

	/**
	 * @return the caseGlass
	 */
	public String getCaseGlass() {
		return mCaseGlass;
	}

	/**
	 * @param caseGlass the caseGlass to set
	 */
	public void setCaseGlass(String caseGlass) {
		mCaseGlass = caseGlass;
	}

	/**
	 * @return the caseBackType
	 */
	public String getCaseBackType() {
		return mCaseBackType;
	}

	/**
	 * @param caseBackType the caseBackType to set
	 */
	public void setCaseBackType(String caseBackType) {
		mCaseBackType = caseBackType;
	}

	/**
	 * @return the caseShape
	 */
	public String getCaseShape() {
		return mCaseShape;
	}

	/**
	 * @param caseShape the caseShape to set
	 */
	public void setCaseShape(String caseShape) {
		mCaseShape = caseShape;
	}

	/**
	 * @return the caseDiameter
	 */
	public double getCaseDiameter() {
		return mCaseDiameter;
	}

	/**
	 * @param caseDiameter the caseDiameter to set
	 */
	public void setCaseDiameter(double caseDiameter) {
		mCaseDiameter = caseDiameter;
	}

	/**
	 * @return the caseHeight
	 */
	public double getCaseHeight() {
		return mCaseHeight;
	}

	/**
	 * @param caseHeight the caseHeight to set
	 */
	public void setCaseHeight(double caseHeight) {
		mCaseHeight = caseHeight;
	}

	/**
	 * @return the caseWaterResistance
	 */
	public double getCaseWaterResistance() {
		return mCaseWaterResistance;
	}

	/**
	 * @param caseWaterResistance the caseWaterResistance to set
	 */
	public void setCaseWaterResistance(double caseWaterResistance) {
		mCaseWaterResistance = caseWaterResistance;
	}

	/**
	 * @return the dialColor
	 */
	public String getDialColor() {
		return mDialColor;
	}

	/**
	 * @param dialColor the dialColor to set
	 */
	public void setDialColor(String dialColor) {
		mDialColor = dialColor;
	}

	/**
	 * @return the dialIndex
	 */
	public String getDialIndex() {
		return mDialIndex;
	}

	/**
	 * @param dialIndex the dialIndex to set
	 */
	public void setDialIndex(String dialIndex) {
		mDialIndex = dialIndex;
	}

	/**
	 * @return the movement
	 */
	public String getMovement() {
		return mMovement;
	}

	/**
	 * @param movement the movement to set
	 */
	public void setMovement(String movement) {
		mMovement = movement;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return mPrice;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		mPrice = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mBrand == null) ? 0 : mBrand.hashCode());
		result = prime * result + ((mCaseBackType == null) ? 0 : mCaseBackType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(mCaseDiameter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mCaseGlass == null) ? 0 : mCaseGlass.hashCode());
		temp = Double.doubleToLongBits(mCaseHeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mCaseMaterial == null) ? 0 : mCaseMaterial.hashCode());
		result = prime * result + ((mCaseShape == null) ? 0 : mCaseShape.hashCode());
		temp = Double.doubleToLongBits(mCaseWaterResistance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mDialColor == null) ? 0 : mDialColor.hashCode());
		result = prime * result + ((mDialIndex == null) ? 0 : mDialIndex.hashCode());
		result = prime * result + mId;
		result = prime * result + ((mMovement == null) ? 0 : mMovement.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		temp = Double.doubleToLongBits(mPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mReference == null) ? 0 : mReference.hashCode());
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
		Watch other = (Watch) obj;
		if (mBrand == null) {
			if (other.mBrand != null)
				return false;
		} else if (!mBrand.equals(other.mBrand))
			return false;
		if (mCaseBackType == null) {
			if (other.mCaseBackType != null)
				return false;
		} else if (!mCaseBackType.equals(other.mCaseBackType))
			return false;
		if (Double.doubleToLongBits(mCaseDiameter) != Double.doubleToLongBits(other.mCaseDiameter))
			return false;
		if (mCaseGlass == null) {
			if (other.mCaseGlass != null)
				return false;
		} else if (!mCaseGlass.equals(other.mCaseGlass))
			return false;
		if (Double.doubleToLongBits(mCaseHeight) != Double.doubleToLongBits(other.mCaseHeight))
			return false;
		if (mCaseMaterial == null) {
			if (other.mCaseMaterial != null)
				return false;
		} else if (!mCaseMaterial.equals(other.mCaseMaterial))
			return false;
		if (mCaseShape == null) {
			if (other.mCaseShape != null)
				return false;
		} else if (!mCaseShape.equals(other.mCaseShape))
			return false;
		if (Double.doubleToLongBits(mCaseWaterResistance) != Double.doubleToLongBits(other.mCaseWaterResistance))
			return false;
		if (mDialColor == null) {
			if (other.mDialColor != null)
				return false;
		} else if (!mDialColor.equals(other.mDialColor))
			return false;
		if (mDialIndex == null) {
			if (other.mDialIndex != null)
				return false;
		} else if (!mDialIndex.equals(other.mDialIndex))
			return false;
		if (mId != other.mId)
			return false;
		if (mMovement == null) {
			if (other.mMovement != null)
				return false;
		} else if (!mMovement.equals(other.mMovement))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (Double.doubleToLongBits(mPrice) != Double.doubleToLongBits(other.mPrice))
			return false;
		if (mReference == null) {
			if (other.mReference != null)
				return false;
		} else if (!mReference.equals(other.mReference))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Watch [mId=" + mId + ", mReference=" + mReference + ", mBrand=" + mBrand + ", mName=" + mName
				+ ", mCaseMaterial=" + mCaseMaterial + ", mCaseGlass=" + mCaseGlass + ", mCaseBackType=" + mCaseBackType
				+ ", mCaseShape=" + mCaseShape + ", mCaseDiameter=" + mCaseDiameter + ", mCaseHeight=" + mCaseHeight
				+ ", mCaseWaterResistance=" + mCaseWaterResistance + ", mDialColor=" + mDialColor + ", mDialIndex="
				+ mDialIndex + ", mMovement=" + mMovement + ", mPrice=" + mPrice + "]";
	}
}