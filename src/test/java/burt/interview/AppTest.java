package burt.interview;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

         Set<Float> setOfFloatNaN = new HashSet<Float>();
         setOfFloatNaN.add(Float.NaN);

         System.out.println("*** test null list and empty list cases***");
         List<Float> lf = new ArrayList<Float>();
         assertTrue(Stats.mean(lf).equals(Float.NaN));
         assertTrue(Stats.mean(null).equals(Float.NaN));

         assertTrue(Stats.median(lf).equals(Float.NaN));
         assertTrue(Stats.median(null).equals(Float.NaN));

         assertTrue(Stats.mode(lf).equals(setOfFloatNaN));
         assertTrue(Stats.mode(null).equals(setOfFloatNaN));

         System.out.println("*** test even number of positive and negative elements ***");
         List<Float> mixed = new ArrayList<Float>();
         mixed.add(new Float(-2));
         mixed.add(new Float(20));
         mixed.add(new Float(12));
         mixed.add(new Float(-10));
         assertTrue(Stats.mean(mixed).equals(new Float(5)));
         assertTrue(Stats.median(mixed).equals(new Float(5)));
         
         System.out.println("*** test one element case ***"); 
         List<Float> oneElement = new ArrayList<Float>();
         oneElement.add(new Float(-2));
         assertTrue(Stats.mean(oneElement).equals(new Float(-2)));
         assertTrue(Stats.median(oneElement).equals(new Float(-2)));
         Set<Float> setOfOneElement = new HashSet<Float>();
         setOfOneElement.add(new Float(-2));
         assertTrue(Stats.mode(oneElement).equals(setOfOneElement));

         System.out.println("*** test odd number of positive and negative elements with a true mode calculation ***");
         List<Float> goodMode = new ArrayList<Float>();
         goodMode.add(new Float(-12));
         goodMode.add(new Float(20));
         goodMode.add(new Float(20));
         goodMode.add(new Float(12));
         goodMode.add(new Float(-12));
         assertTrue(Stats.mean(goodMode).equals(new Float(5.6)));
         assertTrue(Stats.median(goodMode).equals(new Float(12)));
         Set<Float> modeSet = new HashSet<Float>();
         modeSet.add(new Float(-12));
         modeSet.add(new Float(20));
         assertTrue(Stats.mode(goodMode).equals(modeSet));

    }
}
