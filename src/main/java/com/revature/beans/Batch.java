package com.revature.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

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
@Table(name = "CALIBER_BATCH")
public class Batch implements Serializable {
	private static final long serialVersionUID = -4807985624462658242L;

	@Id
	@Column(name = "BATCH_ID")
	@SequenceGenerator(name = "BATCH_ID_SEQUENCE", sequenceName = "BATCH_ID_SEQUENCE", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_ID_SEQUENCE")
	private Integer batchId;
	
	@Column(name = "BATCH_NAME", length = 50)
	private String batchName;
	
	@Column(name = "BATCH_LOCATION_ID")
	private Integer batchLocationId;
	
	@Column(name = "BATCH_START_DATE")
	private Timestamp batchStartDate;
	
	@Column(name = "BATCH_END_DATE")
	private Timestamp batchEndDate;
	
	@Column(name = "CURRICULUM_ID")
	private Integer curriculumId;
	
	@Column(name = "ASSOCIATES_SET")
	private Set<Integer> associates;
}
