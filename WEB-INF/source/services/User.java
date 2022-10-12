package services;

import javax.jws.WebService;

@WebService
public class User extends UserImpl{
	
	public String findByID(String UserID) throws Exception {
		
		User obj = new User();
		String prueba = obj.findUser(UserID);
		return prueba;
		
	}
	

}
