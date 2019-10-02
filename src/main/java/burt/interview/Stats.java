package burt.interview;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

/**

   I tried to exhibit a few different skills; therefore, the work is not optimized.
   We do not use an interface because we want to use static methods as this is a library that
   really does not need state.

   The methods could be more generic taking in Collections and they using casting but I wanted to 
   get proper compiler errors if they were used in correctly.  Of course we could "reuse" this methods
   for various types of subclasses List<? extends Number>  or Collection<?> but this work gives the general idea.

*/
class Stats
{
	public static Float mean(List<Float> m) {

              if (m == null || m.size() == 0) {
                   return Float.NaN;
              }
              if (m.size() == 1) {
                 return m.get(0);
              }

                 
              Float f = m.stream().map(Float::floatValue).reduce(0f, (a,b)->a+b);
              f = new Float(f.floatValue()/m.size());
              return f;
        }

	public static Float median(List<Float> m) {

              if (m == null || m.size() == 0) {
                   return Float.NaN;
              }
              if (m.size() == 1) {
                 return m.get(0);
              }

              Collections.sort(m);

              //if the list has an even number of elements we need to 
              //average the values of the middle two elements
              //if the list as an odd number of elements we need to return the 
              //middle element 
              if (m.size() % 2 == 0) {
                 return new Float( (m.get(m.size()/2 - 1) + m.get(m.size()/2))/2);
              } 
              else {
                 return m.get((int)(m.size()/2));
              }
        }

	public static Set<Float> mode(List<Float> m) {

              if (m == null || m.size() == 0) {
                   Set<Float> p = new HashSet<Float>();
                   p.add(Float.NaN);
                   return p;
              }
              if (m.size() == 1) {
                  Set<Float> p = new HashSet<Float>();
                  p.add(m.get(0));
                  return p;
              }

              //we could do instance of as the compiler complains that the put calls are unchecked
              HashMap mp = new HashMap<Float,Integer>();
              for (Float k :  m) {
                  if (mp.containsKey(k)) {
                      mp.put(k, new Integer( ((Integer)mp.get(k)).intValue() + 1));
                  }
                  else {
                      mp.put(k,new Integer(1));
                  }
              }
              
              //this could have been embedded in the above loop
              //find max value
              //it reads better seperately
              int max_appearance = 0;
              for (Object i : mp.values()) {
 
                  //this will always be the case (but really should have a test do not know who might change the code later) 
                  if (i instanceof Integer) {
                    Integer myInt = (Integer)i;
                    if (myInt.intValue() > max_appearance) {
                       max_appearance = myInt.intValue();
                    } 
                  }
              }
  
              if (max_appearance < 2) {
                 Set<Float> p = new HashSet<Float>();
                 p.add(Float.NaN);
                 return p;
              }
              else {

                 Set<Float> modeSet = new HashSet<Float>();

                 //find elements which have max_appearance number of occurences
                 Iterator it = mp.entrySet().iterator();
                 while (it.hasNext()) {

                      Map.Entry nextOne = (Map.Entry)it.next();

                     if ( ((Integer)nextOne.getValue()).intValue() == max_appearance) {
                        modeSet.add((Float)nextOne.getKey());
                     }
                 }
                 return modeSet;
              }
        }
}
