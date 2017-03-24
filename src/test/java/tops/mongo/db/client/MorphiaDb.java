package tops.mongo.db.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import tops.front.operator.intl.inquirys.Mytest;
import tops.front.operator.intl.inquirys.School;

public class MorphiaDb {
	UpdateOperations ops  =null;
	public  Datastore  getDatastore(String DabaName,String Packagename,String Address) throws IOException{
		Morphia morphia = new Morphia();
		
		morphia.mapPackage(Packagename);
		/**
		 * 通过添加链接参数链接
		 */
		MongoClientOptions credentials=new MongoClientOptions.Builder()
				.socketKeepAlive(true)
				.connectTimeout(5000)
				.applicationName("MorphiaDb")
				.description("adescriptio  with the class MongoClientOptions")
				.localThreshold(10)
				.build();
		
		List<MongoCredential> credentialsList =new ArrayList<MongoCredential>();
		MongoCredential credential =MongoCredential.createMongoCRCredential("dba", "order", "123456".toCharArray());
		credentialsList.add(credential);
		MongoClient client=new MongoClient(new ServerAddress(Address),credentialsList, credentials);
		/**
		 * 第二种通过uri链接
		 */
		//MongoClientURI clientURI = new MongoClientURI("mongodb://dba:123456@192.168.161.82:27017/order");
		//MongoClient client = new MongoClient(clientURI);
		
		return  morphia.createDatastore(client,DabaName);
		}
	public  Datastore getDatastore(){
		try {
			return getDatastore("order","tops.front.operator.intl.inquirys","192.168.161.82");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void test(){
			Datastore datastore = getDatastore();
			Mytest mytest = new Mytest();
			mytest.setDatetime(new Date());
			mytest.setName("dddd");
			mytest.setTage(1050);
			datastore.save(mytest);
	}
	
	@Test
	public void find() throws IOException{
		Datastore datastore = getDatastore();
		List<Mytest> mytests = datastore.find(Mytest.class).filter("name =", "dddd").asList();
		for (Mytest mytest : mytests) {
			System.out.println(mytest.toString());
		}
	}
	
	
	@Test
	public void removess() throws IOException{
		Datastore datastore = getDatastore();
		datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1000);
		datastore.createQuery(Mytest.class).filter("tage >=", 1000);
		
		datastore.createQuery(Mytest.class).and(
			datastore.createQuery(Mytest.class).criteria("width").equal(10),
			datastore.createQuery(Mytest.class).criteria("height").equal(1)
		);
		
		
		datastore.createQuery(Mytest.class)
	    .field("x").lessThan(5)
	    .field("y").greaterThan(4)
	    .field("z").greaterThan(10);
	}
	@Test
	public void Aggregation() throws IOException{
		Datastore datastore = getDatastore();
		datastore.createAggregation(Mytest.class).group("name").out(School.class);
	}
	
	/**
	 * Limiting and Skipping
	 * @throws IOException
	 */
	@Test
	private void limit () throws IOException {
		Datastore datastore = getDatastore();
		List<Mytest> mytests =  datastore.createQuery(Mytest.class)
	    .asList(new FindOptions()
	    		.skip(10)
	    		.batchSize(10)
		        .limit(10));
	}
	
	@Test
	public void Ordering() throws IOException{
		Datastore datastore = getDatastore();
		datastore.createQuery(Mytest.class).order("name");
	}
	
	@Test
	public void update(){
		Datastore datastore = getDatastore();
	    ops = datastore.createUpdateOperations(Mytest.class).set("name", "Fairmont Chateau Laurier");
		ops = datastore.createUpdateOperations(Mytest.class).set("school.Address", "Ottawa");
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars");
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars", 4);
		ops = datastore.createUpdateOperations(Mytest.class).dec("stars");
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars", -4);
	}
	
	@Test
	public void push(){
		Datastore datastore = getDatastore();
		ops = datastore.createUpdateOperations(Mytest.class).push("roomNumbers", 11);
		ops = datastore.createUpdateOperations(Mytest.class).addToSet("roomNumbers", 11);
	}
	
	@Test
	public void remove(){
		Datastore datastore = getDatastore();
		//given roomNumbers = [ 1, 2, 3 ]
		ops = datastore.createUpdateOperations(Mytest.class).removeFirst("roomNumbers"); // [ 2, 3 ]
		
		//given roomNumbers = [ 1, 2, 3 ]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [ 1, 2 ]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [1]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [  ]
		
		//given roomNumbers = [ 1, 2, 3, 3 ]
		ops= datastore.createUpdateOperations(Mytest.class).removeAll("roomNumbers", 3);// [ 1, 2 ]
		//given roomNumbers = [ 1, 2, 3, 3 ]  
		ops=datastore.createUpdateOperations(Mytest.class).removeAll("roomNumbers", Arrays.asList(2, 3)); // [ 1 ]
	}
	
	@Test
	public void updateFirst(){
		Datastore datastore = getDatastore();
	    ops = datastore.createUpdateOperations(Mytest.class).inc("stars", 50);
		datastore.updateFirst(datastore.find(Mytest.class).order("stars"), ops);
		datastore.updateFirst(datastore.find(Mytest.class).order("-stars"),ops);
	}
	
	@Test
	public void Multiple(){
		Datastore datastore = getDatastore();
		//set city to Ottawa and increment stars by 1
		ops = datastore.createUpdateOperations(Mytest.class).set("city", "Ottawa").inc("stars");

		//if you perform multiple operations in one command on the same property, results will vary
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars", 50).inc("stars");  //increments by 1
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars").inc("stars", 50);  //increments by 50

		//you can't apply conflicting operations to the same property
		ops = datastore.createUpdateOperations(Mytest.class).set("stars", 1).inc("stars", 50); //causes error
		
	}
	@Test
	public void createIfMissing(){
		Datastore datastore = getDatastore();
		ops = datastore.createUpdateOperations(Mytest.class).inc("stars", 50);
		datastore.updateFirst(datastore.createQuery(Mytest.class).field("stars").greaterThan(100),ops, true);
		
	}
}
