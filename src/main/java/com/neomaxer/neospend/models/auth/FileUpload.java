package com.neomaxer.neospend.models.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file_upload")
public class FileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fileName;
	
	@Lob
    private byte[] fileData;


//	@Column(name = "file_path")
//	private String filePath;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "expense_request_id")
//	private ExpenseRequest expenseRequest;

}
