package fed.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // замість @Setter @Getter @EqualsAndHashCode @ToString
@NoArgsConstructor
public class User {
	private String name;
	private int age;
	private String position;

	// не знайшов, як робити конструктор копіювання, тому вирішив прописати
	// вручну
	// варіант @Builder(toBuilder = true), не сподобався
	public User(User x) {
		setName(x.getName());
		setAge(x.getAge());
		setPosition(x.getPosition());
	}
}