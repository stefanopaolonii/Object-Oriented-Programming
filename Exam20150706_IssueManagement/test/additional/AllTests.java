package additional;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Users.class);
        suite.addTestSuite(TestR2_Components.class);
        suite.addTestSuite(TestR3_TicketOpen.class);
        suite.addTestSuite(TestR4_TicketCycle.class);
        suite.addTestSuite(TestR5_Stats.class);
        //$JUnit-END$
        return suite;
    }

}
