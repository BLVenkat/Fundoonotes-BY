package com.bridgelabz.fundoonotes.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapLoaderLifecycleSupport;

public class ProductLoader implements MapLoader<Long, User>, MapLoaderLifecycleSupport {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void init(HazelcastInstance hazelcastInstance, Properties properties, String mapName) {

		System.err.println("csaca");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User load(Long key) {
		System.out.println(key);
		return userRepository.findById(key).get();
	}

	@Override
	public Map<Long, User> loadAll(Collection<Long> keys) {
		Map<Long, User> productMap= new HashMap<>();
		for(Long key: keys){
			User product=this.load(key);
		if(product!=null){
		productMap.put(key, product);
		  }
		}
		System.out.println(productMap);
		return productMap;	
		}

	@Override
	public Iterable<Long> loadAllKeys() {
		return null;
	}
	
	//@Override 
	public void setApplicationContext(ApplicationContext appContext){
		System.err.println("csaca");

		userRepository= appContext.getBean(UserRepository.class);
	}
}