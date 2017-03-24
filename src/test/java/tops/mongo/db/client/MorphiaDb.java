package tops.mongo.db.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import tops.front.operator.intl.inquirys.Mytest;

public class MorphiaDb {
	@Test
	public  void getProper() throws IOException{
		Morphia morphia = new Morphia();
		morphia.mapPackage("tops.front.operator.intl.inquirys");
		MongoClientOptions credentials=new MongoClientOptions.Builder()
				.socketKeepAlive(true)
				.connectTimeout(5000)
				.description("adescriptio  with the class MongoClientOptions")
				.localThreshold(10)
				.build();
		List<MongoCredential> credentialsList =new ArrayList<MongoCredential>();
		MongoCredential credential =MongoCredential.createMongoCRCredential("dba", "order", "123456".toCharArray());
		credentialsList.add(credential);
		ServerAddress address=new ServerAddress("192.168.161.82");
		MongoClient client=new MongoClient(address,credentialsList, credentials);
		Datastore datastore = morphia.createDatastore(client,"order");
		datastore.ensureIndexes();
		Mytest mytest=new Mytest();
		mytest.setDatetime(new Date());
		mytest.setName("lok");
		mytest.setTage(10);
		datastore.save(mytest);
		}
}
