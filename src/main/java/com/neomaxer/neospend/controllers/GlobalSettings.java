package com.neomaxer.neospend.controllers;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor

public class GlobalSettings {

	private List<PaymentMethod> methods;

}
