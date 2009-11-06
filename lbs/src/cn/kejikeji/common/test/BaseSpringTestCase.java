package cn.kejikeji.common.test;

import org.springframework.test.AbstractTransactionalSpringContextTests;

public class BaseSpringTestCase extends AbstractTransactionalSpringContextTests {

	
	
	public BaseSpringTestCase() {
		super();
		super.setPopulateProtectedVariables(true);
	}

	public BaseSpringTestCase(String arg0) {
		super(arg0);
		super.setPopulateProtectedVariables(true);
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] {    			
    			"file:web/WEB-INF/applicationContext-*.xml"    			
    	};
	}
}
