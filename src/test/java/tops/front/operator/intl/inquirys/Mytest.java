package tops.front.operator.intl.inquirys;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import com.sun.corba.se.spi.ior.ObjectId;

@Entity("Mytest")
public class Mytest {
	@Id
	private ObjectId id;
	@Transient
	private String name;
	private int tage;
	private Date datetime;
	@Reference
	private Mytest mytest;
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTage() {
		return tage;
	}
	public void setTage(int tage) {
		this.tage = tage;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Mytest getMytest() {
		return mytest;
	}
	public void setMytest(Mytest mytest) {
		this.mytest = mytest;
	}
	
	

}