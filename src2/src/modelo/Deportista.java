package modelo;

public class Deportista {
    private String item;
    private int anio;
    private String federacion;
    private String nombres;
    private String apellidoPaterno;
    private double monto;
    private String pais;
    private String departamento;
    private String ubigeo;
    private String distrito;
    private String provincia;

    // Constructor CORREGIDO con los mismos parámetros que usa CSVReader
    public Deportista(String item, int anio, String federacion, String nombres, String apellidoPaterno,
                      double monto, String pais, String departamento, String ubigeo, String distrito, String provincia) {
        this.item = item;
        this.anio = anio;
        this.federacion = federacion;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.monto = monto;
        this.pais = pais;
        this.departamento = departamento;
        this.ubigeo = ubigeo;
        this.distrito = distrito;
        this.provincia = provincia;
    }

    // Getters
    public String getItem() { return item; }
    public int getAnio() { return anio; }
    public String getFederacion() { return federacion; }
    public String getNombres() { return nombres; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public double getMonto() { return monto; }
    public String getPais() { return pais; }
    public String getDepartamento() { return departamento; }
    public String getUbigeo() { return ubigeo; }
    public String getDistrito() { return distrito; }
    public String getProvincia() { return provincia; }
}