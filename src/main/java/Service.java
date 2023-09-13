import java.util.ArrayList;
import java.util.Arrays;

abstract class Service {
     protected ArrayList<String> patients = new ArrayList<>();
     protected Main.TriageType triageType;

     public Service(Main.TriageType triageType){
         this.triageType = triageType;
     }

     public void triagePatient(String name, int gravity, Main.VisibleSymptom visibleSymptom){
         switch (this.triageType){
             case FIFO:
                this.fifo(name, visibleSymptom);
                break;
             case GRAVITY:
                this.gravity(name, gravity, visibleSymptom);
                 break;
         }
         //System.out.println(Arrays.toString(this.patients.toArray()));
     }

     public void fifo(String name, Main.VisibleSymptom visibleSymptom){
         this.patients.add(name);
     }

     public abstract void gravity(String name, int gravity, Main.VisibleSymptom visibleSymptom);

     public String getPatient() throws NoPatientException {
        if(this.patients.isEmpty())
            throw new NoPatientException("No patient");
        return this.patients.remove(0);
     }
}
