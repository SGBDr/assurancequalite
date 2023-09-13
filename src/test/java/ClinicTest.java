import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClinicTest {

    @Test
    public void ifNoPatientAndBrokenOrSprainArriveReturn() throws NoPatientException {
        var clinic = new Clinic(Main.TriageType.FIFO, Main.TriageType.FIFO);

        clinic.triagePatient("Anatole", 5, Main.VisibleSymptom.SPRAIN);

        assertEquals("Anatole", clinic.getPatientFromDoctor());
        assertEquals("Anatole", clinic.getPatientFromRadio());

    }

    @Test
    public void IfNoPatientReturn() {
        var clinic = new Clinic(Main.TriageType.FIFO, Main.TriageType.FIFO);

        try{
            clinic.getPatientFromDoctor();
        }catch(NoPatientException e){
            assertThat(e.getMessage(), is("No patient"));
            return;
        }
        try{
            clinic.getPatientFromRadio();
        }catch(NoPatientException e){
            assertThat(e.getMessage(), is("No patient"));
            return;
        }
        Assert.fail("No Exception thrown");
    }


    @Test
    public void FIFODoctor() throws Exception {
        var clinic = new Clinic(Main.TriageType.FIFO, Main.TriageType.FIFO);

        clinic.triagePatient("First", 4, Main.VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Second", 4, Main.VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Third", 4, Main.VisibleSymptom.MIGRAINE);

        Assert.assertEquals("First", clinic.getPatientFromDoctor());
        Assert.assertEquals("Second", clinic.getPatientFromDoctor());
        Assert.assertEquals("Third", clinic.getPatientFromDoctor());

        try{
            clinic.getPatientFromRadio();
        }catch(NoPatientException e){
            assertThat(e.getMessage(), is("No patient"));
            return;
        }
        Assert.fail("No Exception thrown");
    }
}