package com.revature.beans;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Beans for BatchLocation table
 * @author Omowumi
 *
 */
@Entity
@Table(name = "CALIBER_BATCH_LOCATION")
public class BatchLocation implements Serializable {
	private static final long serialVersionUID = 9110421700932292732L;

	/**
	 * Id of the batch location
	 */
	@Id
	@Column(name = "BATCH_LOCATION_ID")
	@SequenceGenerator(name = "BATCH_LOCATION_ID_SEQUENCE", sequenceName = "BATCH_LOCATION_ID_SEQUENCE", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_LOCATION_ID_SEQUENCE")
	private Integer batchLocationId;
	
	/**
	 * Name of a batch location
	 */
	@Column(name = "BATCH_LOCATION_NAME")
	private String batchLocationName;
	
	private HashSet<Batch> batches = new HashSet<Batch>(0);

	public BatchLocation() {
		super();
	}
	
	public BatchLocation(Integer batchLocationId, String batchLocationName) {
		super();
		this.batchLocationId = batchLocationId;
		this.batchLocationName = batchLocationName;
	}

	public BatchLocation(Integer batchLocationId, String batchLocationName, HashSet<Batch> batches) {
		super();
		this.batchLocationId = batchLocationId;
		this.batchLocationName = batchLocationName;
		this.batches = batches;
	}

	public Integer getBatchLocationId() {
		return batchLocationId;
	}

	public void setBatchLocationId(Integer batchLocationId) {
		this.batchLocationId = batchLocationId;
	}

	public String getBatchLocationName() {
		return batchLocationName;
	}

	public void setBatchLocationName(String batchLocationName) {
		this.batchLocationName = batchLocationName;
	}

	public HashSet<Batch> getBatches() {
		return batches;
	}

	public void setBatches(HashSet<Batch> batches) {
		this.batches = batches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchLocationId == null) ? 0 : batchLocationId.hashCode());
		result = prime * result + ((batchLocationName == null) ? 0 : batchLocationName.hashCode());
		result = prime * result + ((batches == null) ? 0 : batches.hashCode());
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
		BatchLocation other = (BatchLocation) obj;
		if (batchLocationId == null) {
			if (other.batchLocationId != null)
				return false;
		} else if (!batchLocationId.equals(other.batchLocationId))
			return false;
		if (batchLocationName == null) {
			if (other.batchLocationName != null)
				return false;
		} else if (!batchLocationName.equals(other.batchLocationName))
			return false;
		if (batches == null) {
			if (other.batches != null)
				return false;
		} else if (!batches.equals(other.batches))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BatchLocation [batchLocationId=" + batchLocationId + ", batchLocationName=" + batchLocationName
				+ ", batches=" + batches + "]";
	}
	
	
}
