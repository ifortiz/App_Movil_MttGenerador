package n.com.mantenimientoelectrico.model;

public class Tablero {
    private DetalleActividad idMotor;
    private DetalleActividad marca;
    private DetalleActividad rangoDesde;
    private DetalleActividad rangoHasta;
    private DetalleActividad calAnterior;
    private DetalleActividad calActual;
    private DetalleActividad vecesIr150;
    private DetalleActividad vecesIr200;
    private DetalleActividad tiempoDisparo150;
    private DetalleActividad tiempoDisparo200;
    private  boolean isSave;

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public DetalleActividad getIdMotor() {
        return idMotor;
    }

    public void setIdMotor(String value,String name) {
        if (idMotor == null)
            idMotor = new DetalleActividad();
       idMotor.setValorPrueba(value);
       idMotor.setNombre(name);
    }

    public DetalleActividad getMarca() {
        return marca;
    }

    public void setMarca(String valor,String nombre) {
        if( marca == null )
            this.marca = new DetalleActividad();
        this.marca.setValorPrueba(valor);
        this.marca.setNombre(nombre);
    }


    public void setMarca(DetalleActividad marca) {
        this.marca = marca;
    }

    public DetalleActividad getRangoDesde() {
        return rangoDesde;
    }

    public void setRangoDesde(String value,String name) {
        if ( rangoDesde == null)
            this.rangoDesde = new DetalleActividad();
        this.rangoDesde.setNombre(name);
        this.rangoDesde.setValorPrueba(value);
    }

    public DetalleActividad getRangoHasta() {
        return rangoHasta;
    }

    public void setRangoHasta(String value,String name) {
        if( rangoHasta == null)
            this.rangoHasta = new DetalleActividad();
        this.rangoHasta.setValorPrueba(value);
        this.rangoHasta.setNombre(name);
    }

    public DetalleActividad getCalAnterior() {
        return calAnterior;
    }

    public void setCalAnterior(String value,String name) {
        if (calAnterior == null)
            calAnterior = new DetalleActividad();
        calAnterior.setValorPrueba(value);
        calAnterior.setNombre(name);
    }

    public DetalleActividad getCalActual() {
        return calActual;
    }

    public void setCalActual(String value,String name) {
        if ( calActual == null)
            calActual = new DetalleActividad();
        calActual.setNombre(name);
        calActual.setValorPrueba(value);
    }

    public DetalleActividad getVecesIr150() {
        return vecesIr150;
    }

    public void setVecesIr150(String value, String name) {
        if(vecesIr150 == null)
            vecesIr150 = new DetalleActividad();
        vecesIr150.setValorPrueba(value);
        vecesIr150.setNombre(name);
    }

    public DetalleActividad getVecesIr200() {
        return vecesIr200;
    }

    public void setVecesIr200(String value,String name) {
         if( vecesIr200 == null)
             vecesIr200 = new DetalleActividad();
         vecesIr200.setNombre(name);
         vecesIr200.setValorPrueba(value);
    }

    public DetalleActividad getTiempoDisparo150() {
        return tiempoDisparo150;
    }

    public void setTiempoDisparo150(String value, String name) {
       if (tiempoDisparo150 == null)
           tiempoDisparo150 = new DetalleActividad();
       tiempoDisparo150.setValorPrueba(value);
       tiempoDisparo150.setNombre(name);
    }

    public DetalleActividad getTiempoDisparo200() {
        return tiempoDisparo200;
    }

    public void setTiempoDisparo200(String value,String name) {
        if ( tiempoDisparo200 == null )
            tiempoDisparo200 = new DetalleActividad();
        tiempoDisparo200.setNombre(name);
        tiempoDisparo200.setValorPrueba(value);
    }
}
