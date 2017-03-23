package tops.mongo.db.client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MorphiaDb {
	static MongoClient client=null;
	@Test
	public  void getProper() throws IOException{
		Properties properties = new Properties();
		InputStream in = new BufferedInputStream (new FileInputStream("/home/wangkaijie/tops.front.operator.intl.inquirys/src/test/resources/mongoclient.properties"));
		MongoClientOptions clientOptions = new MongoClientOptions.Builder().connectionsPerHost(50).maxWaitTime(2000).build();
		properties.load(in);     ///加载属性列表
		List<MongoCredential> lstCredentials =  Arrays.asList(MongoCredential.createMongoCRCredential(
				properties.getProperty("user"), properties.getProperty("tadabase"), properties.getProperty("password").toCharArray()));
		client = new MongoClient(new ServerAddress(properties.getProperty("address")),lstCredentials, clientOptions);
		
		client.close();
	}
}
