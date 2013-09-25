package helper;

import javax.servlet.http.Cookie;

import database.DbHelper;

public class CookieHelper {
	public Boolean isConnected(Cookie cookie) {
		String username = cookie.getValue();
		
		return DbHelper.getInstance().userExist(username);
	}
}
