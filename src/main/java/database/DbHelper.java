package database;

import model.MatchModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import model.AbstractTicketCategory;
import model.TicketCategoryFactory;
import model.UserModel;
import config.ConfigManager;
import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class DbHelper {
	
	private AtomicInteger nextUserId = new AtomicInteger(0);
	private Map<Integer, UserModel> users = new HashMap<Integer, UserModel>();
	private AtomicInteger nextMatchId = new AtomicInteger(0);
	private Map<Integer, MatchModel> matchs = new HashMap<Integer, MatchModel>();
	private static DbHelper db = new DbHelper();
	
	
	
	private DbHelper() {
		populate();
	}
	
	public static DbHelper getInstance() {
		return db;
	}
	
	public UserModel getUserByUsername(String username) {
		for (UserModel myUser : users.values()) {
			if (myUser.getUsername().contentEquals(username)) {
				return myUser;			
			}			
		}
		
		return null;
	}
	
	public static void replaceDb(DbHelper dbHelper) {
		db = dbHelper;
	}
	
	public static DbHelper getNewDb() {
		return new DbHelper();
	}
	
	public void dropAndRepopulate() {
		nextUserId = new AtomicInteger(0);
		nextMatchId = new AtomicInteger(0);
		
		users.clear();
		matchs.clear();
		
		populate();
	}
	
	private void populate() {
		
		//Populate DB with users
		UserModel user1 = createUser("Matt", "Martin", "MM", "password", false);
		UserModel user2 = createUser("Houde", "Louis-Jos??", "LH", "password", false);
		UserModel user3 = createUser("Baddouri", "Rachid", "RB", "password", false);
		UserModel user4 = createUser("Petit", "Martin", "MP", "password", false);
		UserModel admin1 = createUser("Admin", "Admin", "admin", "admin", true);
		
		users.put(nextUserId.incrementAndGet(), user1);
		users.put(nextUserId.incrementAndGet(), user2);
		users.put(nextUserId.incrementAndGet(), user3);
		users.put(nextUserId.incrementAndGet(), user4);
		users.put(nextUserId.incrementAndGet(), admin1);
		
		//Populate DB with matchs

		ArrayList<AbstractTicketCategory> billetsMatch1 = new ArrayList<AbstractTicketCategory>();
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(ConfigManager.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(ConfigManager.FREE_TICKET, "Debout", 200, 0, 10));

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchModel match0 = createMatch(nextMatchId.incrementAndGet(), Sports.Football, Gender.M, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 11);
		MatchModel match1 = createMatch(nextMatchId.incrementAndGet(), Sports.Football, Gender.M, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 9);
		MatchModel match2 = createMatch(nextMatchId.incrementAndGet(), Sports.Rugby, Gender.F, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch1);
		cal.set(2013, 11, 8);
		MatchModel match3 = createMatch(nextMatchId.incrementAndGet(), Sports.Volleyball, Gender.F, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		MatchModel match4 = createMatch(nextMatchId.incrementAndGet(), Sports.Volleyball, Gender.M, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		
		matchs.put(match0.getId().intValue(), match0);
		matchs.put(match1.getId().intValue(), match1);
		matchs.put(match2.getId().intValue(), match2);
		matchs.put(match3.getId().intValue(), match3);
		matchs.put(match4.getId().intValue(), match4);
	}
	
	// Users
	
	public Collection<UserModel> getAllUsers() {
		return users.values();
	}
	
	public void addUser(UserModel user) {
		users.put(nextUserId.incrementAndGet(), user);
	}
	
	public Boolean userExist(String username) {
		Boolean exist = false;
		for (UserModel myUser : users.values()) {
			if (myUser.getUsername().contentEquals(username)) {
				exist = true;			
			}			
		}
		return exist;
	}
	
	public Boolean isLoginValid(String username, String password) {
		Boolean found = false;
		for(UserModel user : users.values()) {
			if(user.getUsername().contentEquals(username)) {
				if(user.getPassword().contentEquals(password)) {
					found = true;
				}
			}
		}
		
		return found;
	}
		
	private UserModel createUser(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserModel user= new UserModel();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);
		user.setIsAdmin(isAdmin);
		return user;
	}
	
	// Matchs
	
	public Collection<MatchModel> getAllMatchs() {
		return matchs.values();
	}
	
	public Collection<MatchModel> getAllMatchsBySport(Sports sport) {
		Collection<MatchModel> resultCollection = new ArrayList<MatchModel>();
		
		for (MatchModel myMatch : matchs.values()) {
			if (myMatch.getSport() == sport) {
				resultCollection.add(myMatch);		
			}			
		}
		return resultCollection;
	}
	
	public void addMatch(MatchModel match) {
		matchs.put(nextMatchId.incrementAndGet(), match);
	}
	
	public MatchModel getMatchFromId(int id){
		return matchs.get(id);
	}
	
	private MatchModel createMatch(int matchId, Sports sport, Gender gender, Date date, String adversaire, String city, String terrain, ArrayList<AbstractTicketCategory> cat) {
		MatchModel match = new MatchModel(sport, gender, Long.valueOf(matchId), date, adversaire, city, terrain, cat);
		return match;
	}
 }
