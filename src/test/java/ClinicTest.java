import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ClinicTest {

    @Test
    public void WithOnePreviousPatient_IfGravityGreaterThanFive_ReturnGreaterGravity() throws NoPatientException {
        var clinic = new Clinic(Main.TriageType.GRAVITY, Main.TriageType.FIFO);

        clinic.triagePatient("Anatole", 2, Main.VisibleSymptom.MIGRAINE);
        clinic.triagePatient("Rodrigue", 6, Main.VisibleSymptom.MIGRAINE);

        assertEquals("Rodrigue", clinic.getPatientFromDoctor());
        assertEquals("Anatole", clinic.getPatientFromDoctor());
    }

    @Test
    public void WithOnePreviousPatient_IfGravityGreaterThanFiveWithSprain_ReturnGreaterGravity()
            throws NoPatientException {
        var clinic = new Clinic(Main.TriageType.GRAVITY, Main.TriageType.FIFO);

        clinic.triagePatient("Anatole", 2, Main.VisibleSymptom.SPRAIN);
        clinic.triagePatient("Rodrigue", 6, Main.VisibleSymptom.SPRAIN);

        assertEquals("Rodrigue", clinic.getPatientFromDoctor());
        assertEquals("Anatole", clinic.getPatientFromRadio());
        assertEquals("Rodrigue", clinic.getPatientFromRadio());
    }

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