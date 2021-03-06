package tops.front.operator.intl.inquirys;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Text;
import org.mongodb.morphia.annotations.Transient;


@Entity("Mytest")
public class Mytest {
	@Id
	private ObjectId id;
	@Property("mmmm")
	private String name;
	@Indexed(options=@IndexOptions(unique=true))
	private int tage;
	private Date datetime;
	@Embedded
	private School school;
	@Text
	@Transient
	private int roomNumbers;
	
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
	public int getRoomNumbers() {
		return roomNumbers;
	}
	public void setRoomNumbers(int roomNumbers) {
		this.roomNumbers = roomNumbers;
	}
	@Override
	public String toString() {
		return "Mytest [id=" + id + ", name=" + name + ", tage=" + tage + ", datetime=" + datetime + ", school="
				+ school + ", roomNumbers=" + roomNumbers + "]";
	}
	
    
}