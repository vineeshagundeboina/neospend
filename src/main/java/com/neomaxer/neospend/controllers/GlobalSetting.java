package com.neomaxer.neospend.controllers;

import java.util.List;

import javax.persistence.ManyToOne;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor

public class GlobalSetting {

	// here we are setting cooling period
	@ManyToOne
	

	private List<Prop> props;

}
