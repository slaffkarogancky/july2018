package golden.horde.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PingEventListener {

	private static final  Logger logger = LoggerFactory.getLogger(PingEventListener.class);
	
	@EventListener
	public void onApplicationEvent(PingEvent event) {
		logger.info(event.getDescription());
	}
}
