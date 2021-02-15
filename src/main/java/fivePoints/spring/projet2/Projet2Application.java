package fivePoints.spring.projet2;

import fivePoints.spring.projet2.models.Poste;
import fivePoints.spring.projet2.models.Role;
import fivePoints.spring.projet2.models.User;
import fivePoints.spring.projet2.models.UserDetails;
import fivePoints.spring.projet2.repositories.PosteRepository;
import fivePoints.spring.projet2.repositories.RoleRepository;
import fivePoints.spring.projet2.repositories.UserRepository;
import fivePoints.spring.projet2.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Projet2Application implements ApplicationRunner {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PosteRepository posteRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(Projet2Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Clean up database tables
		this.roleRepository.deleteAllInBatch();
		this.userRepository.deleteAllInBatch();
		this.userDetailsRepository.deleteAllInBatch();
		this.posteRepository.deleteAllInBatch();

		// Save roles
		Role superAdminRole = this.roleRepository.save(new Role("super-admin"));
		Role adminRole = this.roleRepository.save(new Role("admin"));
		Role userRole = this.roleRepository.save(new Role("user"));
		Role guestRole = this.roleRepository.save(new Role("guest"));


		// Save users
		User user1 = new User("hatem", "dagbouj",
				"hatem.dagbouj@fivepoints.fr", "123456789");

		// Save users details
		UserDetails userDetails1 = new UserDetails(20, new Date("11/11/1994"),
				"github.com/hatem", "linkedin.com/hatem");

		// Affect user1 to userDetails1
		user1.setDetails(userDetails1); // Set child reference
		userDetails1.setUser(user1); // Set parent reference
		this.userRepository.save(user1);


		// Save Posts
		Poste post1 = new Poste("","", true);
		Poste post2 = new Poste("","", true);
		Poste post3 = new Poste("","", true);

		// associate user1 to posts
		post1.setUser(user1);
		post2.setUser(user1);
		post3.setUser(user1);
		this.posteRepository.save(post1);
		this.posteRepository.save(post2);
		this.posteRepository.save(post3);
		this.userRepository.save(user1);

		// ManyToMany Relations
		Set<Role> roles = new HashSet<>();
		roles.add(superAdminRole);
		roles.add(adminRole);
		roles.add(userRole);
		roles.add(guestRole);
		user1.setRoles(roles);
		this.userRepository.save(user1);
	}


}
