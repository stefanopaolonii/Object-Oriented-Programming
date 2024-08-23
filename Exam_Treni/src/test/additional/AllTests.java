package test.additional;

import junit.framework.Test;
import junit.framework.TestSuite;



public class AllTests {

  public static void main(String[] args) {
    junit.textui.TestRunner.run(AllTests.class);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for default package");
    //$JUnit-BEGIN$
    suite.addTest(new TestSuite(TestR1_Percorsi.class));
    suite.addTest(new TestSuite(TestR2_Fermate.class));
    suite.addTest(new TestSuite(TestR3_Treni.class));
    suite.addTest(new TestSuite(TestR4_Passaggi.class));
    suite.addTest(new TestSuite(TestR5_Statistiche.class));
    //$JUnit-END$
    return suite;
  }
}
