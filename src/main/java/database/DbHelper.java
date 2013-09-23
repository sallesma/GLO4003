package database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import config.ConfigManager.Genre;
import config.ConfigManager.Sports;

import model.UserModel;
import model.MatchModel;

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
	
	public static void replaceDb(DbHelper dbHelper) {
		db = dbHelper;
	}
	
	public DbHelper getNewDb() {
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
		UserModel user1 = createUser("Matt", "Martin", "MM", "password");
		UserModel user2 = createUser("Houde", "Louis-Jos??", "LH", "password");
		UserModel user3 = createUser("Baddouri", "Rachid", "RB", "password");
		UserModel user4 = createUser("Petit", "Martin", "MP", "password");
		
		users.put(nextUserId.incrementAndGet(), user1);
		users.put(nextUserId.incrementAndGet(), user2);
		users.put(nextUserId.incrementAndGet(), user3);
		users.put(nextUserId.incrementAndGet(), user4);
		
		//Populate DB with matchs
		MatchModel match1 = createMatch(Sports.football, Genre.M, new Date(2013, 12, 11), "Québec", "ULaval", 100, 500);
		
		matchs.put(nextMatchId.incrementAndGet(), match1);
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
		
	private UserModel createUser(String lastName, String firstName, String username, String password) {
		UserModel user= new UserModel();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);	
		
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
	
	private MatchModel createMatch(Sports sport, Genre gender, Date date, String city, String terrain, int nbVIPTickets, int nbNormalTickets) {
		MatchModel match = new MatchModel ();
		match.setSport(sport);
		match.setGender(gender);
		match.setDate(date);
		match.setCity(city);
		match.setTerrain(terrain);
		match.setVipRemainingTickets(nbVIPTickets);
		match.setNormalRemainingTickets(nbNormalTickets);
		
		return match;
	}
	
 }
