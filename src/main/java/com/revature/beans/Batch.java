package com.revature.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Beans for Batch table
 * @author Omowumi
 *
 */
@Entity
@Table(name = "TF_BATCH")
public class Batch implements Serializable {
	private static final long serialVersionUID = -4807985624462658242L;

	/**
	 * Id of a batch
	 */
	@Id
	@Column(name = "BATCH_ID")
	@SequenceGenerator(name = "BATCH_ID_SEQUENCE", sequenceName = "BATCH_ID_SEQUENCE", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_ID_SEQUENCE")
	private Integer batchId;
	
	/**
	 * Name of a batch
	 */
	@Column(name = "BATCH_NAME", length = 50)
	private String batchName;
	
	/**
	 * Location id to link to batch locations from BatchLocation Table
	 */
	@Column(name = "BATCH_LOCATION_ID")
	private Integer batchLocationId;
	
	/**
	 * Date batch started
	 */
	@Column(name = "BATCH_START_DATE")
	private Timestamp batchStartDate;
	
	/**
	 * Date batch ended
	 */
	@Column(name = "BATCH_END_DATE")
	private Timestamp batchEndDate;
	
	/**
	 * Curriculum id to link to curriculums from Curriculum Microservice
	 */
	@Column(name = "CURRICULUM_ID")
	private Integer curriculumId;
	
	/**
	 * Set of associates that belong to a specific batch
	 * Not in use
	 */
	@Column(name = "ASSOCIATES_SET")
	private HashSet<Integer> associates;
	
	public Batch() {
		super();
	}

	public Batch(Integer batchId, String batchName, Integer batchLocationId, Timestamp batchStartDate,
			Timestamp batchEndDate, Integer curriculumId, HashSet<Integer> associates) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.batchLocationId = batchLocationId;
		this.batchStartDate = batchStartDate;
		this.batchEndDate = batchEndDate;
		this.curriculumId = curriculumId;
		this.associates = associates;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Integer getBatchLocationId() {
		return batchLocationId;
	}

	public void setBatchLocationId(Integer batchLocationId) {
		this.batchLocationId = batchLocationId;
	}

	public Timestamp getBatchStartDate() {
		return batchStartDate;
	}

	public void setBatchStartDate(Timestamp batchStartDate) {
		this.batchStartDate = batchStartDate;
	}

	public Timestamp getBatchEndDate() {
		return batchEndDate;
	}

	public void setBatchEndDate(Timestamp batchEndDate) {
		this.batchEndDate = batchEndDate;
	}

	public Integer getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Integer curriculumId) {
		this.curriculumId = curriculumId;
	}

	public HashSet<Integer> getAssociates() {
		return associates;
	}

	public void setAssociates(HashSet<Integer> associates) {
		this.associates = associates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + ((batchEndDate == null) ? 0 : batchEndDate.hashCode());
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((batchLocationId == null) ? 0 : batchLocationId.hashCode());
		result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
		result = prime * result + ((batchStartDate == null) ? 0 : batchStartDate.hashCode());
		result = prime * result + ((curriculumId == null) ? 0 : curriculumId.hashCode());
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
		Batch other = (Batch) obj;
		if (associates == null) {
			if (other.associates != null)
				return false;
		} else if (!associates.equals(other.associates))
			return false;
		if (batchEndDate == null) {
			if (other.batchEndDate != null)
				return false;
		} else if (!batchEndDate.equals(other.batchEndDate))
			return false;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (batchLocationId == null) {
			if (other.batchLocationId != null)
				return false;
		} else if (!batchLocationId.equals(other.batchLocationId))
			return false;
		if (batchName == null) {
			if (other.batchName != null)
				return false;
		} else if (!batchName.equals(other.batchName))
			return false;
		if (batchStartDate == null) {
			if (other.batchStartDate != null)
				return false;
		} else if (!batchStartDate.equals(other.batchStartDate))
			return false;
		if (curriculumId == null) {
			if (other.curriculumId != null)
				return false;
		} else if (!curriculumId.equals(other.curriculumId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", batchLocationId=" + batchLocationId
				+ ", batchStartDate=" + batchStartDate + ", batchEndDate=" + batchEndDate + ", curriculumId="
				+ curriculumId + ", associates=" + associates + "]";
	}
	
	
}
