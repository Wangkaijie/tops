package tops.front.operator.intl.inquirys;

import javax.xml.stream.StreamFilter;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity
@Validation("{adt:{$gt:18}}")

public class AnnotationTestEntity {
	@Id
	private ObjectId id;
	@Property("NewName")
	private String name;
	@Reference
	private AnnotationTest annotationTest;
	@Serialized
	private StreamFilter streamFilter;
	@Transient
	private String Transient;
	private int adt;
	@Version("version")
	private String Version;

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

	public AnnotationTest getAnnotationTest() {
		return annotationTest;
	}

	public void setAnnotationTest(AnnotationTest annotationTest) {
		this.annotationTest = annotationTest;
	}

	public StreamFilter getStreamFilter() {
		return streamFilter;
	}

	public void setStreamFilter(StreamFilter streamFilter) {
		this.streamFilter = streamFilter;
	}

	public String getTransient() {
		return Transient;
	}

	public void setTransient(String transient1) {
		Transient = transient1;
	}

	public int getAdt() {
		return adt;
	}

	public void setAdt(int adt) {
		this.adt = adt;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

}
