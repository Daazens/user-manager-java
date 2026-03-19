import java.util.ArrayList;

class User {
	private String name;
	private String password;
	private int id;
	private String email;

	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public String toString() {
		return "==============\nid   : " + id + "\nname : " + 
			name + "\nemail: " + email + "\npass : " + password;
	}
}
class UserResponseDTO {
	private String name;
	private String email;

	UserResponseDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String toString() {
		return "===========\nname : " + name + "\nemail: " + email;
	}
}

class UserRequestDTO {
	private int id;
	private String name;
	private String email;
	private String password;

	UserRequestDTO(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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

	void showUser(int id) {
		User u = findById(id);
		if (u != null) {

			UserResponseDTO dto = new UserResponseDTO(u.getName(), u.getEmail());
			System.out.println(dto);
		}

		else {
			System.out.println("Invalid");
		}
	}


	void show() {
		if (users.isEmpty()) {
			System.out.println("No User added");
		}
		for (User u : users) {
			UserResponseDTO dto = new UserResponseDTO(u.getName(), u.getEmail());
			System.out.println(dto);
		}
	}
}

class UserService {
	UserRepository repo = new UserRepository();

	void createUser(UserRequestDTO users) {
		if (isPassable(users)) {
			User user = new User(users.getId(), users.getName(), users.getEmail(), users.getPassword());
			repo.saveEntity(user);
		}
		else {
			System.out.println("Invalid");
		}
	}

	boolean isPassable(UserRequestDTO user) {
		return  !user.getName().isEmpty() &&
			!user.getPassword().isEmpty() &&
			user.getPassword().length() >= 5 &&
			user.getEmail().contains("@");
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

	void getThatOneUser(int id) {
		repo.showUser(id);
	}
}



public class Main {
	private int idQounter = 1;
	UserService service = new UserService();

	void registerUser() {
		String name = "Bot";
		String email = "daz@.com";
		String password = "12iii2";
		UserRequestDTO userDTO = new UserRequestDTO(idQounter++, name, email, password);
		service.createUser(userDTO);
	}

	void unregistUser() {
		service.deleteUser(1);
	}

	void displayUser() {
		service.getUser();
	}

	void searchUser() {
		service.getThatOneUser(3);
	}

	public static void main(String[] args) {
		Main n = new Main();
		n.registerUser();
		n.registerUser();
		n.registerUser();
		n.displayUser();
		n.unregistUser();
		n.displayUser();
		n.searchUser();
		System.out.println("under construction!");
	}
}
