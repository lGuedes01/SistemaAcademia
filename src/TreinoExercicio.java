import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class TreinoExercicio{
    private int idTreino;
    private int idAluno;
    private int idExercicio;
    private int nSeries;
    private int minRep;
    private int maxRep;
    private float carga;
    private float descanso;

    public TreinoExercicio(int idTreino, int idAluno, int idExercicio) {
        this.idTreino = idTreino;
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
    }

    public int getIdTreino(){
        return idTreino;
    }
    public int getIdAluno(){
        return idAluno;
    }
    public int getIdExercicio(){
        return idExercicio;
    }
    public int getnSeries(){
        return nSeries;
    }
    public int getMinRep(){
        return minRep;
    }
    public int getMaxRep(){
        return maxRep;
    }
    public float getCarga(){
        return carga;
    }
    public float getDescanso(){
        return descanso;
    }

    public void setnSeries(int nSeries){
        this.nSeries = nSeries;
    }
    public void setMinRep(int minRep){
        this.minRep = minRep;
    }
    public void setMaxRep(int maxRep){
        this.maxRep = maxRep;
    }
    public void setCarga(float carga){
        this.carga = carga;
    }
    public void setDescanso(float descanso){
        this.descanso = descanso;
    }

}
