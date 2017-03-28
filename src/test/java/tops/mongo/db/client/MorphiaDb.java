package tops.mongo.db.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.FindAndModifyOptions;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import tops.front.operator.intl.inquirys.Mytest;
import tops.front.operator.intl.inquirys.School;

public class MorphiaDb {
	
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
	public void Saving(){
			Datastore datastore = getDatastore();
			Mytest mytest = new Mytest();
			School school=new School();
			school.setAddress("hulanxilu");
			school.setName("zhenlvwang");
			mytest.setDatetime(new Date());
			mytest.setName("dddd");
			mytest.setTage(1050);
			mytest.setSchool(school);
			datastore.save(mytest);
	}
	
	@Test
	public void find() throws IOException{
		Datastore datastore = getDatastore();
		List<Mytest> mytests = datastore.find(Mytest.class).filter("tage ==", 1050).asList();
		List<Mytest>   list =datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1050).asList();
		System.out.println(mytests.size());
		for (Mytest mytest : mytests) {
			System.out.println(mytest.toString());
		}
	}
	
	
/*	@Test
	public void and() throws IOException{
		Datastore datastore = getDatastore();
		CriteriaContainer container =  datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1050).and(
			datastore.createQuery(Mytest.class).criteria("name").equal("dddd")
		);
		
		
		datastore.createQuery(Mytest.class)
	    .field("x").lessThan(5)
	    .field("y").greaterThan(4)
	    .field("z").greaterThan(10);
	}*/
	
	/*@Test
	public void Aggregation() throws IOException{
		Datastore datastore = getDatastore();
		Iterator<Mytest> iterator = datastore.createAggregation(Mytest.class).group("name").out(Mytest.class);
	    if(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
	*/
	/**
	 * Limiting and Skipping
	 * @throws IOException
	 */
	@Test
	public void limit () throws IOException {
		Datastore datastore = getDatastore();
		List<Mytest> mytests =  datastore.createQuery(Mytest.class)
	    .asList(new FindOptions().skip(7).batchSize(20).limit(10));
		for (Mytest mytest : mytests) {
			System.out.println(mytest.toString());
		}
	}
	
	@Test
	public void Ordering() throws IOException{
		Datastore datastore = getDatastore();
		List<Mytest> mytests = datastore.createQuery(Mytest.class).order("-datetime").asList();
		for (Mytest mytest : mytests) {
			System.out.println(mytest.toString());
		}
	}
	/**
	 * 学会这个方法后就觉得其实就是monog的语法的包装，语法会，这个也就会了
	 */
	@Test
	public void update(){
		Datastore datastore = getDatastore();
		UpdateOperations<Mytest> ops  = datastore.createUpdateOperations(Mytest.class).set("name", "Fairmont Chateau Laurier");
		Query<Mytest>  query  = datastore.find(Mytest.class).filter("tage ==", 1050);
	    UpdateResults results =  datastore.update(query, ops);
	    System.out.println(results.toString());
	}
	
	@Test
	public void updateinc(){
		Datastore datastore = getDatastore();
		Query<Mytest>  query  =datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1050);
		UpdateOperations<Mytest> ops  = datastore.createUpdateOperations(Mytest.class).inc("tage");
		UpdateResults results =  datastore.update(query, ops);
		System.out.println(results.toString());
	}
	
	@Test
	public void findAndModify(){//只改变了第一条满足条件的
		Datastore datastore = getDatastore();
		UpdateOperations<Mytest> ops  = datastore.createUpdateOperations(Mytest.class).inc("tage", 10);
		Query<Mytest>  query  = datastore.find(Mytest.class).filter("tage ==", 1051);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.remove(false);
		options.returnNew(true);
		options.upsert(true);
		Mytest mytest =datastore.findAndModify(query, ops, options);
		System.out.println(mytest.toString());
	}
	
	
	@Test
	public void push(){
		Datastore datastore = getDatastore();
		UpdateOperations<Mytest> ops = datastore.createUpdateOperations(Mytest.class).push("roomNumbers", 13);
		Query<Mytest>  query  =datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1061);
		UpdateResults results =datastore.update(query, ops, false);
		System.out.println(results);
		
	}
	
	@Test
	public void remove(){
		Datastore datastore = getDatastore();
		UpdateOperations<Mytest> ops = datastore.createUpdateOperations(Mytest.class).removeFirst("roomNumbers"); // [ 2, 3 ]
		Query<Mytest>  query  =datastore.createQuery(Mytest.class).field("tage").greaterThanOrEq(1061);
		UpdateResults results =datastore.update(query, ops, false);
		System.out.println(results);
		
		
		/*//given roomNumbers = [ 1, 2, 3 ]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [ 1, 2 ]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [1]
		ops = datastore.createUpdateOperations(Mytest.class).removeLast("roomNumbers");// [  ]
		
		//given roomNumbers = [ 1, 2, 3, 3 ]
		ops= datastore.createUpdateOperations(Mytest.class).removeAll("roomNumbers", 3);// [ 1, 2 ]
		//given roomNumbers = [ 1, 2, 3, 3 ]  
		ops=datastore.createUpdateOperations(Mytest.class).removeAll("roomNumbers", Arrays.asList(2, 3)); // [ 1 ]
		
		Query<Mytest> overPaidQuery = datastore.createQuery(Mytest.class).filter("salary >", 100000);
		datastore.delete(overPaidQuery);*/
		
		
	}
	
	@Test
	public void orderAndUpdateFirst(){
		Datastore datastore = getDatastore();
		UpdateOperations<Mytest>   ops = datastore.createUpdateOperations(Mytest.class).inc("tage", 50);
		Query<Mytest>  query  =datastore.find(Mytest.class).order("-tage");
		UpdateResults results = datastore.updateFirst(query,ops);
		System.out.println(results);
	}
	/*
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
	
	public void Querying(){
		Datastore datastore = getDatastore();
		final Query<Mytest> query = datastore.createQuery(Mytest.class);
		final List<Mytest> employees = query.asList();
	    datastore.createQuery(Mytest.class).field("salary").lessThanOrEq(30000).asList();
	    List<Mytest> underpaid = datastore.createQuery(Mytest.class).filter("salary <=", 30000).asList();
		
	}*/
}
