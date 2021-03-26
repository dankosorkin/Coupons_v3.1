package core.sessions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * encapsulates the state of a single client. keep a map of attributes. it has a
 * unique id (token)
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class Session {

	private Map<String, Object> attributes = new HashMap<String, Object>();
	public final String token;
	private long lastAccessed;

	@Value("${session.max.inactive.interval:5}")
	private long maxInactiveInterval;

	private static final int TOKEN_MAX_LENGTH = 15;

	{
		this.token = UUID.randomUUID().toString().replace("-", "").substring(TOKEN_MAX_LENGTH);
		resetLastAccessed();
	}

	public void resetLastAccessed() {
		this.lastAccessed = System.currentTimeMillis();
	}

	@PostConstruct
	private void init() {
		maxInactiveInterval = TimeUnit.MINUTES.toMillis(maxInactiveInterval);
	}

	public void setAttribute(String attrName, Object attrValue) {
		attributes.put(attrName, attrValue);
	}

	public Object getAttribute(String attrName) {
		return attributes.get(attrName);
	}

	public long getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	public void setMaxInactiveInterval(long maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

}
