import java.util.ArrayList;

class User {
	private String name;
	private String password;
	private int id;

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "[" + id + "] " + name + " | " + password;
	}
}

class UserRepository {
	private ArrayList<User> users = new ArrayList<>();

	void saveEntity(User user) {
		users.add(user);
	}

	void remove(User user) {
		users.remove(user);
	}

	User findById(int id) {
		for (User u : users) {
			if (id == u.getId()) {
				return u;
			}
		}
		return null;
	}

	void show() {
		if (users.isEmpty()) {
			System.out.println("No User added");
		}
		for (User u : users) {
			System.out.println(u);
		}
	}
}

class UserService {
	UserRepository repo = new UserRepository();

	void createUser(User user) {
		repo.saveEntity(user);
	}

	boolean isPassable(User user) {
		return user.getName() != "" && user.getPassword() != "";
	}

	void deleteUser(int id) {
		User u = repo.findById(id);
		if (u != null) {
			repo.remove(u);
		}
		else {
			System.out.println("Invalid");
		}
	}

	void getUser() {
		repo.show();
	}
}



public class Main {
	private int idQounter = 1;
	UserService service = new UserService();

	void registerUser() {
		User user = new User(idQounter++, "d", "");
		if (service.isPassable(user)) {
			service.createUser(user);
		}
		else {
			System.out.println("Invalid");
		}
	}

	void unregistUser() {
		service.deleteUser(1);
	}

	void displayUser() {
		service.getUser();
	}

	public static void main(String[] args) {
		Main n = new Main();
		n.registerUser();
		n.registerUser();
		n.registerUser();
		n.displayUser();
		n.unregistUser();
		n.displayUser();
		System.out.println("under construction!");
	}
}
