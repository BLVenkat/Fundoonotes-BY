package com.bridgelabz.fundoonotes.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.entity.User;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.MapStoreConfig.InitialLoadMode;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class CacheConfig {

	//@Autowired
	//UserRepository userRepository;
	
//	@Bean
//	public HazelcastInstance hazlecastInstance(){
//	Config config= new Config();
//	config.setInstanceName("hazelcast-instance");
//	config.addMapConfig(new MapConfig().setName("NoteCache"));
//	return Hazelcast.newHazelcastInstance(config);
//
//	}
//	
	@Bean
	public Map<Long, User> noteMap(HazelcastInstance hazelcastInstance) {
		Map<Long,User> cacheMap = hazelcastInstance.getMap("Product");
		return cacheMap;

	}

	
	
	@Bean
	public HazelcastInstance hazlecastInstance(){
	Config config= new Config();
	config.addMapConfig(mapProductConfig());
	return Hazelcast.newHazelcastInstance(config);
	}
	
	MapConfig mapProductConfig(){
		ProductLoader loader= new ProductLoader();
		MapStoreConfig msc= new MapStoreConfig();
		msc.setEnabled(true).setClassName("com.bridgelabz.fundoonotes.configuration.ProductLoader").setInitialLoadMode(InitialLoadMode.EAGER);
		msc.setImplementation(loader);
		MapConfig mapConfig= new MapConfig("Product");
		mapConfig.setName("Product");
		return mapConfig;
		}
	
}
