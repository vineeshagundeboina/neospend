package com.neomaxer.neospend.controllers;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Prop {

    private String label;
    
    private String val; 
    private String dataType; 

    private String key; 

}