package database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import model.MatchModel;
import model.UserModel;
import config.ConfigManager.Genre;
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
		UserModel user1 = createUser("Matt", "Martin", "MM", "password");
		UserModel user2 = createUser("Houde", "Louis-Jos??", "LH", "password");
		UserModel user3 = createUser("Baddouri", "Rachid", "RB", "password");
		UserModel user4 = createUser("Petit", "Martin", "MP", "password");
		
		users.put(nextUserId.incrementAndGet(), user1);
		users.put(nextUserId.incrementAndGet(), user2);
		users.put(nextUserId.incrementAndGet(), user3);
		users.put(nextUserId.incrementAndGet(), user4);
		
		//Populate DB with matchs
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 12, 11);
		MatchModel match1 = createMatch(nextMatchId.incrementAndGet(), Sports.Football, Genre.M, cal.getTime(), "UQAM", "Québec", "ULaval", 100, 500);
		cal.set(2013, 12, 9);
		MatchModel match2 = createMatch(nextMatchId.incrementAndGet(), Sports.Rugby, Genre.F, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", 4, 600);
		cal.set(2013, 12, 8);
		MatchModel match3 = createMatch(nextMatchId.incrementAndGet(), Sports.Volleyball, Genre.F, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", 0, 100);
		
		matchs.put(match1.getMatchID(), match1);
		matchs.put(match2.getMatchID(), match2);
		matchs.put(match3.getMatchID(), match3);
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
	
	public MatchModel getMatchFromId(int id){
		return matchs.get(id);
	}
	
	private MatchModel createMatch(int matchId, Sports sport, Genre gender, Date date, String adversaire, String city, String terrain, int nbVIPTickets, int nbNormalTickets) {
		MatchModel match = new MatchModel(sport, gender, matchId, date, adversaire, city, terrain, nbVIPTickets, nbNormalTickets);
		return match;
	}
 }
