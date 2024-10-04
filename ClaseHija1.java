public class ClaseHija1 extends ClasePadre{
    int atributo4;
    int atributo5;
    int atributo6;
    ClaseHija1(int atributo1,int atributo2,int atributo3,int atributo4,int atributo5,int atributo6){
        super(atributo1,atributo2,atributo3);
        this.atributo4 = atributo4;
        this.atributo5 = atributo5;
        this.atributo6 = atributo6;
    }
    public int getAtributo4() {
        return atributo4;
    }
    public int getAtributo5() {
        return atributo5;
    }
    public int getAtributo6() {
        return atributo6;
    }
}
