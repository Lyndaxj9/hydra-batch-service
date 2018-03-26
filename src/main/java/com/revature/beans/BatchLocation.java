package com.revature.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class BatchLocation implements Serializable {
	private static final long serialVersionUID = 9110421700932292732L;

	@Id
	@Column(name = "BATCH_LOCATION_ID")
	private Integer batchLocationId;
	
	@Column(name = "BATCH_LOCATION_NAME")
	private String batchLocationName;
	
	private HashSet<Batch> batches = new HashSet<Batch>(0);
}
