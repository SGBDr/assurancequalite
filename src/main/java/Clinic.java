import java.util.ArrayList;

public class Clinic {
    private DoctorService doctorService;
    private RadioService radioService;

    public Clinic(Main.TriageType triageDoctorType, Main.TriageType triageRadioType) {
        this.doctorService = new DoctorService(triageDoctorType);
        this.radioService = new RadioService(triageRadioType);
    }

    public void triagePatient(String name, int gravity, Main.VisibleSymptom visibleSymptom) {
        if(visibleSymptom == Main.VisibleSymptom.BROKEN_BONE || visibleSymptom == Main.VisibleSymptom.SPRAIN)
            this.radioService.triagePatient(name, gravity, visibleSymptom);
        this.doctorService.triagePatient(name, gravity, visibleSymptom);
    }

    public String getPatientFromDoctor() throws NoPatientException {
        return this.doctorService.getPatient();
    }

    public String getPatientFromRadio() throws NoPatientException {
        return this.radioService.getPatient();
    }

    // D'autres méthodes peuvent être nécessaires

}