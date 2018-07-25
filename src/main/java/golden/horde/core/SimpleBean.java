package golden.horde.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;

public class SimpleBean {

	private static final String MY_TXT_FILE = "classpath:my.txt";

	private static final  Logger logger = LoggerFactory.getLogger(SimpleBean.class);
	
	private final String name;	
	
	@Autowired
	private ApplicationContext ctx;
	
	public SimpleBean(String name) {
		this.name = name;
	}

	@PostConstruct
	public void init() {
		logger.info(String.format("Bean with name '%s' was created", this.name));	
		logger.info(String.format("The display name is '%s'", ctx.getDisplayName()));
		displayFileContent();
	}
	
	@PreDestroy
	public void destroy() {
		logger.info(String.format("Bean with name '%s' was destroyed", this.name));	
	}
	
	@Scheduled(fixedDelay=1000)
	public void tick() {
		logger.info("tick -> " + LocalDateTime.now().getNano()/1000000);
	}
	
	private void displayFileContent() {
		try {
			Resource resource = ctx.getResource(MY_TXT_FILE);
			File file = resource.getFile(); 
			//File theSameFile = ResourceUtils.getFile(MY_TXT_FILE);
			String content = new String(Files.readAllBytes(file.toPath()));
			logger.info(String.format("My.txt content: %s", content));
		} catch (IOException e) {
			logger.error("", e);
		}	
	}
	

	
	
}
