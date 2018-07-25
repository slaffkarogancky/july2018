package golden.horde.core;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
//@EnableScheduling
public class MiscellaneousConfiguration {

	@Bean // in order to add eTag automatically
	public Filter filter() {
		return new ShallowEtagHeaderFilter();
	}
	
	@Bean
	public SimpleBean simpleBean() {
		return new SimpleBean("My favourite bean!");
	}
}
