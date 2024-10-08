package in.g77tech.dto;

import lombok.Data;

@Data
public class UnlockFormDto {
	
	private String email;
	private String password;
	private String confirmPwd;
	private String tempPwd;

}
