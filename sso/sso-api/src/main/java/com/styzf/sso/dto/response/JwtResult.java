package com.styzf.sso.dto.response;

import com.styzf.core.common.response.LoginResponse;
import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.sso.dto.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class JwtResult extends Response {
	public JwtResult(Boolean success, String jwt) {
		super(success);
		this.jwt = jwt;
	}
	
	private String jwt;
}
