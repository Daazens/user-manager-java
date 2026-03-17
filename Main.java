import java.util.ArrayList;

class User {
	String name;
	int id;

	User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {
		return "[" + id + "] " + name;
	}
}

class UserRepository {
	ArrayList<User> users = new ArrayList<>();

	void save(User user) {
		users.add(user);
	}

	void show() {
		for (User u : users) {
			System.out.println(u);
		}
	}
}

class UserService {
	UserRepository repo = new UserRepository();

	void createUser(User user) {
		repo.save(user);
	}

	void getUser() {
		repo.show();
	}
}



public class Main {
	UserService service = new UserService();

	void registerUser() {
		User user = new User(1, "Bot");
		service.createUser(user);
	}

	void displayUser() {
		service.getUser();
	}

	public static void main(String[] args) {
		Main n = new Main();
		n.registerUser();
		n.displayUser();
		System.out.println("under construction!");
	}
}
