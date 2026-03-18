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

class UserDTO {
	public int idx;
	public String name;

	UserDTO(int idx, String name){
		this.idx = idx;
		this.name = name;
	}

	public String toString() {
		return "=================\nid   : " + idx + "\n" + "name : " + name;
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

	void createUser(User user) {
		repo.saveEntity(user);
	}

	void createDTO(UserDTO DTO) {
		repo.saveDTO(DTO);
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
		String name = "Bot";
		User user = new User(idQounter++, name, "jk");
		UserDTO DTO = new UserDTO(idQounter, name);
		if (service.isPassable(user)) {
			service.createUser(user);
			service.createDTO(DTO);
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
