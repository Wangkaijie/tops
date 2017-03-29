package tops.front.operator.intl.inquirys;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class AnnotationTest {
	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
