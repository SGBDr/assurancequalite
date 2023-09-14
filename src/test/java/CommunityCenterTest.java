import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommunityCenterTest {

    @Test
    public void WithOnePreviousPatient_IfGravityGreaterThanFive_ReturnGreaterGravity() throws NoPatientException {
        var communityCenter = new CommunityCenter(Main.TriageType.GRAVITY);

        communityCenter.triagePatient("Anatole", 2);
        communityCenter.triagePatient("Rodrigue", 6);

        assertEquals("Rodrigue", communityCenter.getPatientFromNurse());
        assertEquals("Anatole", communityCenter.getPatientFromNurse());
    }

    @Test
    public void IfNoPatientReturn() {
        var community = new CommunityCenter(Main.TriageType.GRAVITY);

        try{
            community.getPatientFromNurse();
        }catch(NoPatientException e){
            assertThat(e.getMessage(), is("No patient"));
            return;
        }
        Assert.fail("No Exception thrown");
    }


    @Test
    public void FIFONurse() throws Exception {
        var community = new CommunityCenter(Main.TriageType.FIFO);

        community.triagePatient("First", 5);
        community.triagePatient("Second", 7);
        community.triagePatient("Third", 4);

        Assert.assertEquals("First", community.getPatientFromNurse());
        Assert.assertEquals("Second", community.getPatientFromNurse());
        Assert.assertEquals("Third", community.getPatientFromNurse());
    }
}