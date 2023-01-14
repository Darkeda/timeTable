package uni.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"uni.masters.*"})
@EnableAutoConfiguration
public class TimeTableProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeTableProjetApplication.class, args);
	}

}
