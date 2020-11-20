package com.feng.springit;

import com.feng.springit.config.SpringitProperies;
import com.feng.springit.domain.Comment;
import com.feng.springit.domain.Link;
import com.feng.springit.repository.CommentRepository;
import com.feng.springit.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperies.class)
@EnableJpaAuditing
public class SpringitApplication {

	//https://www.danvega.dev/docs/spring-boot-2-docs/#_course_details

	@Autowired
	private SpringitProperies springitProperies; //inject reference to springit class

	//get the slf4j springboot own logger on the our springit app
	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	@Bean
	@Profile({"dev","prod"})
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			System.out.println("Welcome message " + springitProperies.getWelcomeMsg());

			Link link = new Link("Getting started with Spring boot 2", "https;//therealdanvega.com/spring-boot-2");
			linkRepository.save(link);

			Comment comment = new Comment("Awesome springboot course", link);
			commentRepository.save(comment);
			link.addComment(comment);

		};
	}
}
