package com.neosoft.springbootsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
	
	private Integer projid;
	private String projname;
	private String duration;

}
