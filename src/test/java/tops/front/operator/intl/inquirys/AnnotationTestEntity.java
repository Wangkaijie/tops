package tops.front.operator.intl.inquirys;

import java.io.File;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Collation;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IdGetter;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Serialized;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.annotations.Validation;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexType;

@Entity
@Validation("{adt:{$gt:18}}")
@Indexes({ @Index(fields = @Field(value = "adt", type = IndexType.DESC)), @Index(fields = @Field("CHD")) })
public class AnnotationTestEntity {
	@Id
	private ObjectId id;// 和entity成对出现
	@Property("NewName")
	private String name;// 在数据库中保存的是 "NewName" : "Tim"
	@Reference
	private AnnotationTest annotationTest;
	@Serialized
	private File streamFilter;// 该对象存储的是序列化后的二进制
	@Transient
	private String Transient;// 数据库中没有保存该属性
	@Indexed
	private int adt;
	@Collation(locale = "dddddd")
	private int CHD;
	@Version("version")
	private Long Version;// 必须为long类型
	@Embedded
	private School school;

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

	public File getStreamFilter() {
		return streamFilter;
	}

	public void setStreamFilter(File streamFilter) {
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

	public int getCHD() {
		return CHD;
	}

	public void setCHD(int cHD) {
		CHD = cHD;
	}

	public Long getVersion() {
		return Version;
	}

	public void setVersion(Long version) {
		Version = version;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
