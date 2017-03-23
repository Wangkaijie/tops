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

}