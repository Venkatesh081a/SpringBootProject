package com.learning.demo;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest extends TestCase
{
	public AppTest(){
		super();
	}

	@org.junit.Test
    public void suite()
    {
        new TestSuite( AppTest.class );
    }

}

