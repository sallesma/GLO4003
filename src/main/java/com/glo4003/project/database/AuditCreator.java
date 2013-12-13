package com.glo4003.project.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.dto.MatchDto.Gender;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;
import com.glo4003.project.user.dao.UserModelDao;

public class AuditCreator {
	
	UserModelDao userDao = new UserModelDao();
	MatchModelDao matchDao = new MatchModelDao();
	
	@Test
	public void CreateAudits() throws PersistException, ConvertException {
		createUserAudits();
		createMatchAudits();
	}
	
	@Test	
	public void createUserAudits() throws PersistException, ConvertException {
		userDao.save(createUser("Matt", "Martin", "MM", "password", false));
		userDao.save(createUser("Houde", "Louis-Jos??", "LH", "password", false));
		userDao.save(createUser("Baddouri", "Rachid", "RB", "password", false));
		userDao.save(createUser("Petit", "Martin", "MP", "password", false));
		userDao.save(createUser("Admin", "Admin", "admin", "admin", true));
	}
	
	@Test
	public void createMatchAudits() throws PersistException, ConvertException {
		ArrayList<AbstractTicketCategory> billetsMatch1 = new ArrayList<AbstractTicketCategory>();		
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Admission générale", 200, 0, 10));
		
		ArrayList<AbstractTicketCategory> billetsMatch2 = new ArrayList<AbstractTicketCategory>();		
		billetsMatch2.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 50, 0, 25));
		billetsMatch2.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Admission générale", 100, 0, 10));
		
		ArrayList<AbstractTicketCategory> billetsMatch3 = new ArrayList<AbstractTicketCategory>();
		billetsMatch3.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 55, 0, 18));
		billetsMatch3.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Admission générale", 150, 0, 10));
		
		ArrayList<AbstractTicketCategory> billetsMatch4 = new ArrayList<AbstractTicketCategory>();
		billetsMatch4.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 30, 0, 34));
		billetsMatch4.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Admission générale", 200, 0, 24));

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchDto match0 = createMatch(Sports.Football, Gender.M, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 11);
		MatchDto match1 = createMatch(Sports.Football, Gender.M, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 9);
		MatchDto match2 = createMatch(Sports.Rugby, Gender.F, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch2);
		cal.set(2013, 11, 8);
		MatchDto match3 = createMatch(Sports.Volleyball, Gender.F, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch3);
		cal.set(2013, 11, 23);
		MatchDto match4 = createMatch(Sports.Volleyball, Gender.M, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch4);
		
		matchDao.save(match0);
		matchDao.save(match1);
		matchDao.save(match2);
		matchDao.save(match3);
		matchDao.save(match4);
	}
	
	private UserDto createUser(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserDto user= new UserDto();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);
		user.setIsAdmin(isAdmin);
		return user;
	}
	
	private MatchDto createMatch(Sports sport, Gender gender, Date date, String adversaire, String city, String terrain, ArrayList<AbstractTicketCategory> cat) {
		MatchDto match = new MatchDto(sport, gender, 0L, date, adversaire, city, terrain, cat);
		return match;
	}
}
