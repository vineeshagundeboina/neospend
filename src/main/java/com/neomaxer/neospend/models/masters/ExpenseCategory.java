package com.neomaxer.neospend.models.masters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ExpenseCategory extends BaseEntity {

	@Column(nullable = false)
	private String expenseName;
	
	private String searchKey;

	private String code;

	private String description;
	
	
	@Enumerated(value = EnumType.STRING)
	private ExpenseType expenseType;

	@ManyToOne
	private ExpenseCategory parent;

}
