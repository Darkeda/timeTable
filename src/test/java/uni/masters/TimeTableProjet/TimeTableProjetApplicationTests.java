package uni.masters.TimeTableProjet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uni.masters.Repository.RouteRepository;

@SpringBootTest
class TimeTableProjetApplicationTests {

	@Autowired
	RouteRepository routeRepository;

	@Test
	void contextLoads() {
		PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();

		String passwordOne = passwordEnocder.encode("user");
		String passwordTwo = passwordEnocder.encode("user");

		if (passwordEnocder.matches(passwordOne, passwordTwo)) {
			System.out.println("Matched!");
		}

		if (passwordEnocder.matches("user", passwordOne)) {
			System.out.println("Matched!!!");
			System.out.println(passwordOne);
		}
	}

	@Test
	void testRoute() {
		System.out.println(routeRepository.filterBy(3L,5L,"1233"));
	}

}
