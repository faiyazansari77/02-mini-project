package in.g77tech.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.g77tech.dto.LoginFormDto;
import in.g77tech.dto.SignupFormDto;
import in.g77tech.dto.UnlockFormDto;
import in.g77tech.entity.UserDtlsEntity;
import in.g77tech.repository.UserDtlsRepository;
import in.g77tech.service.UserService;
import in.g77tech.util.EmailUtils;
import in.g77tech.util.PwdUtils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDtlsRepository repository;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginFormDto loginFormDto) {
		UserDtlsEntity userDtlsEntity = repository.findByEmail(loginFormDto.getEmail());
		
		if(!userDtlsEntity.getAccountStatus().equals("UNLOCKED")) {
			return "Account is Lock";
		}
		if(userDtlsEntity == null || !userDtlsEntity.getPwd().equals(loginFormDto.getPassword())) {
			return "Please enter email and password correct";
		}
		
		return "success";
	}

	@Override
	public boolean signUp(SignupFormDto signupFormDto) {
		
		UserDtlsEntity byEmail = repository.findByEmail(signupFormDto.getEmail());
		if(byEmail != null) {
			return false;
		}
		
		// TODO Copy data from DTO to entity
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(signupFormDto, entity);
		
		// TODO generate random password and set to object
		String passowrd = PwdUtils.generatePassowrd();
		entity.setPwd(passowrd);
				
		// TODO set account status as locked
		entity.setAccountStatus("LOCKED");
		
		// TODO insert records
		repository.save(entity);
		
		// TODO send email to unlock the account
		String to = signupFormDto.getEmail();
		String subject = "Unlock your Account | GUDDU";
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use Below Temporary Password to Unlock your Account</h1>");
		body.append("Temporary Password: "+passowrd);
		body.append("<br />");
		body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click Here to unlock your account</a>");
		
		emailUtils.sendMail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockFormDto unlockFormDto) {
		
		UserDtlsEntity byEmail = repository.findByEmail(unlockFormDto.getEmail());
		if(byEmail==null) {
			return false;
		}
		if(!byEmail.getPwd().equals(unlockFormDto.getTempPwd())) {
			return false;
		}
		byEmail.setPwd(unlockFormDto.getPassword());
		byEmail.setAccountStatus("UNLOCKED");
		repository.save(byEmail);
		
		return true;
	}

	@Override
	public boolean forgotPwd(String email) {
		UserDtlsEntity userDtlsEntity = repository.findByEmail(email);
		if(userDtlsEntity==null) {
			return false;
		}
		
		String password = userDtlsEntity.getPwd();
		String subject = "Recover password";
		StringBuffer body = new StringBuffer();
		body.append("<h3>Here your password, don't share with anyone</h3>");
		body.append("<br />");
		body.append("Password: <span style=\"color:red; \">"+password+"</span>");
		
		emailUtils.sendMail(email, subject, body.toString());
		
		return true;
	}

}
