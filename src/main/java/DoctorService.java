public class DoctorService extends Service {

    public DoctorService(Main.TriageType triageType){
        super(triageType);
    }

    @Override
    public void gravity(String name, int gravity, Main.VisibleSymptom visibleSymptom) {
        if(gravity > 5)
            this.patients.add(0, name);
        else
            this.patients.add(name);
    }
}
