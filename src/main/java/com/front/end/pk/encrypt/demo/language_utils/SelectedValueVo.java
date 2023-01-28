package com.front.end.pk.encrypt.demo.language_utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectedValueVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String label;
	String value;
	String selected;

	
}
