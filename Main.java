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
		return "\nname : " + name + "\nemail: " + email + "\n";
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
class ApiResponse<T> {
	String status;
	String message;
	T data;

	ApiResponse(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String toString() {
		return "status = " + status + "\nmessage = " + message + "\ndata = " + data;
	}
}

class UserRepository {
	private ArrayList<User> users = new ArrayList<>();

	void save(User user) {
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

	ArrayList<User> findAll() {
		return users;
	}

	
}

class UserService {
	private UserRepository repo;

	UserService(UserRepository repo) {
		this.repo = repo;
	}

	UserResponseDTO createUser(UserRequestDTO users) {
		if (isValid(users)) {
			User user = new User(users.getId(), users.getName(), users.getEmail(), users.getPassword());
			UserResponseDTO dto  = mapToResponse(user);
			repo.save(user);
			return dto;
		}
		else {
			return null;
		}
	}

	UserResponseDTO mapToResponse(User u) {
		return new UserResponseDTO(u.getName(), u.getEmail());
	}

	boolean isValid(UserRequestDTO user) {
		return  !user.getName().isEmpty() &&
			!user.getPassword().isEmpty() &&
			user.getPassword().length() >= 5 &&
			user.getEmail().contains("@email.com");
	}

	UserResponseDTO deleteUser(int id) {
		User u = repo.findById(id);
		if (u != null) {
			UserResponseDTO dto = mapToResponse(u);
			repo.remove(u);
			return dto;
		}
		else {
			return null;
		}
	}

	UserResponseDTO getUser() {
		ArrayList<User> users = repo.findAll();

		for (User u : users) {
			UserResponseDTO dto = mapToResponse(u);
			return dto;
		}
		return null;
	}

	UserResponseDTO getThatOneUser(int id) {
		User u = repo.findById(id);
		if (u != null) {
			UserResponseDTO dto = mapToResponse(u);
			return dto;
		}
		else {
			return null;
		}
	}

}



public class Main {
	private int idQounter = 1;
	UserRepository repo = new UserRepository();
	UserService service = new UserService(repo);

	ApiResponse<UserResponseDTO> registerUser() {
		String name = "Bot";
		String email = "daz@email.com";
		String password = "12i2fk"; 
		UserRequestDTO userDTO = new UserRequestDTO(idQounter++, name, email, password);
		service.createUser(userDTO);
		UserResponseDTO dto = service.createUser(userDTO);
		 if (dto != null) {
			 return new ApiResponse<>("success", "created", dto);
		 }
		 else { return new ApiResponse<>("error", "invalid", null);
		 }
		
	}

	ApiResponse<UserResponseDTO> unregistUser() {
		UserResponseDTO dto = service.deleteUser(1);
		if (dto != null) {
			return new ApiResponse<>("success", "deleted", dto);
		}
		else {
			return new ApiResponse<>("error", "not found", dto);
		}
	}

	void displayUser() {
		service.getUser();
	}

	ApiResponse<UserResponseDTO> searchUser() {
		UserResponseDTO dto = service.getThatOneUser(2);
		if (dto != null) {
			return new ApiResponse<>("success", "found", dto);
		}
		else {
			return new ApiResponse<>("error", "not found", null);
		}
	}

	void wrapperResponse(ApiResponse<?> response) {
		System.out.println("Status : " + response.status);
		System.out.println("Message: " + response.message);
		if (response.data != null) {
			System.out.println("Data   : " + response.data);
		}
		else {
			System.out.println("Data   : " + response.data);
		}
	}


	public static void main(String[] args) {
		Main n = new Main();
		ApiResponse<UserResponseDTO> t = n.registerUser();
		n.wrapperResponse(t);
		n.registerUser();
		n.registerUser();
		ApiResponse<UserResponseDTO> e = n.unregistUser();
		n.wrapperResponse(e);
		n.displayUser();
		ApiResponse<UserResponseDTO> r = n.searchUser();
		n.wrapperResponse(r);
		System.out.println("under construction!");
	}
}
