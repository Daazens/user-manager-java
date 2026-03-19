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
		return "[" + id + "] " + name + " | " + password;
	}
}

class UserDTO {
	private int idx;
	private String name;
	private String email;

	UserDTO(int idx, String name, String email){
		this.idx = idx;
		this.name = name;
		this.email = email;
	}

	public String toString() {
		return "=================\nid   : " + idx + "\nname : " + name + "\nemail: " + email;
	}
}
class UserRepository {
	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<UserDTO> usersDTO = new ArrayList<>();
	void saveEntity(User user) {
		users.add(user);
	}

	void saveDTO(UserDTO DTO) {
		usersDTO.add(DTO);
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
		if (usersDTO.isEmpty()) {
			System.out.println("No User added");
		}
		for (UserDTO u : usersDTO) {
			System.out.println(u);
		}
	}
}

class UserService {
	UserRepository repo = new UserRepository();

	void createUser(User user, UserDTO DTO) {
		if (isPassable(user)) {
			repo.saveDTO(DTO);
			repo.saveEntity(user);
		}
		else {
			System.out.println("Invalid");
		}
	}

	boolean isPassable(User user) {
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
}



public class Main {
	private int idQounter = 0;
	UserService service = new UserService();

	void registerUser() {
		String name = "Bot";
		String email = "daz@.com";
		String password = "12iii2";
		User user = new User(idQounter++, name, email, password);
		UserDTO DTO = new UserDTO(idQounter, name, email);
		service.createUser(user, DTO);
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
//		n.unregistUser();
		n.displayUser();
		System.out.println("under construction!");
	}
}
