package app.model;

import java.util.Hashtable;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Warenkorb {
	
	private Hashtable<String, String> ht;
	
	public void setItem(String key, String item) {
		ht.put(key, item);
	}
	
	public String getItem(String key) {
		return ht.get(key);
	}

}
