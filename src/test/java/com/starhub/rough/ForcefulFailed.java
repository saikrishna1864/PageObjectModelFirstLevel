package com.starhub.rough;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.starhub.base.Page;
import com.starhub.constants.TestBase;
import com.starhub.listners.CustomTestListners;

@Listeners(CustomTestListners.class)
public class ForcefulFailed extends Page 
{

	
	@Test
	public void verifyForcefulFailedTest()
	{
		
		
		/*try {
			verifyEquals("abc", "dfr");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		log.debug("Verify exquals has been execcuted");*/
		
		log.debug("About to validate assertion");
		
		Assert.fail();
		
		
	}
}
