package in.g77tech.service;


import in.g77tech.dto.LoginFormDto;
import in.g77tech.dto.SignupFormDto;
import in.g77tech.dto.UnlockFormDto;


public interface UserService {

	public String login(LoginFormDto loginFormDto);
	public boolean signUp(SignupFormDto signupFormDto);
	public boolean unlockAccount(UnlockFormDto unlockFormDto);
	public boolean forgotPwd(String email);
}
