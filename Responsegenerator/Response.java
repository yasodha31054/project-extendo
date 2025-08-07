package com.example.prj.Responsegenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response{

private String ststus;
private String message;
private Object data;

public Response(String status, String message) {
	this.ststus=status;
	this.message=message;
}

}
