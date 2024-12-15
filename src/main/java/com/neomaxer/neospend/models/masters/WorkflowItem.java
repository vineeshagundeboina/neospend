package com.neomaxer.neospend.models.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "code"), @UniqueConstraint(columnNames = "name") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class WorkflowItem extends BaseEntity {

	@Column(nullable = false, unique = true, updatable = true, length = 40)
	private String code;

	@Column(nullable = false)
	private String name;

	private String description;

	private Date validFrom;

	private Date validTill;

}
