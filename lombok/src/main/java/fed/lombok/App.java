package fed.lombok;

public class App {

	public static void main(String[] args) {
		User user = new User();
		user.setName("Oleg");
		user.setAge(20);
		user.setPosition("Java Developer");

		User other = new User(user);
		System.out.println(other.equals(user));

		other.setAge(12);
		System.out.println(other.equals(user));
	}

}
