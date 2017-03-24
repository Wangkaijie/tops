package tops.front.operator.intl.inquirys;

import java.util.Date;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

import com.sun.corba.se.spi.ior.ObjectId;

@Entity("Mytest")

//@Indexes({@Index(fields = {@Field(value = "name")})})
public class Mytest {
	@Id
	private ObjectId id;
	
	private String name;
	@Indexed(options=@IndexOptions(unique=true))
	private int tage;
	private Date datetime;
	@Embedded
	private School school;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
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
	@Override
	public String toString() {
		return "Mytest [id=" + id + ", name=" + name + ", tage=" + tage + ", datetime=" + datetime + "]";
	}
    
}