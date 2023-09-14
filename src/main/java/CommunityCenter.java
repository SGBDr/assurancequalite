public class CommunityCenter {
    private NurseService nurseService;

    public CommunityCenter(Main.TriageType triageNurseType) {
        this.nurseService = new NurseService(triageNurseType);
    }

    public void triagePatient(String name, int gravity, Main.VisibleSymptom symptom) {
        this.nurseService.triagePatient(name, gravity, symptom);
    }

    public void triagePatient(String name, int gravity) {
        this.nurseService.triagePatient(name, gravity);
    }

    public String getPatientFromNurse() throws NoPatientException {
        return this.nurseService.getPatient();
    }

}
