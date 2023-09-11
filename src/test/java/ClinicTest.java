import junit.framework.TestCase;
import org.junit.Test;

public class ClinicTest extends TestCase {

    @Test
    public void test_MigrainIsFirstInWaitingList(){
        Clinic clinic = new Clinic(Main.TriageType.FIFO, Main.TriageType.FIFO);

        clinic.triagePatient("Rodrigue", 3, Main.VisibleSymptom.MIGRAINE);

        String name = clinic.getPatientFromDoctor();

        assertEquals("Rodrigue", name);
    }

    @Test
    public void test_IfAlreadySomePatientInDoctorList_SecondWithFLUIsAdd(){
        Clinic clinic = new Clinic(Main.TriageType.FIFO, Main.TriageType.FIFO);

        clinic.triagePatient("Rodrigue", 3, Main.VisibleSymptom.MIGRAINE);
        clinic.triagePatient("KENGOUM", 3, Main.VisibleSymptom.FLU);

        clinic.getPatientFromDoctor();

        String name = clinic.getPatientFromDoctor();

        assertEquals("KENGOUM", name);
    }

}