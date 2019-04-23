
package battleshipconsola;


public class PartesBarco {
    
    private int cantVidas, habilidad, barcoAlQuePertenece;
    private boolean perteneceBarco, disparoRecibido;
    
    // El atributo barcoAlQuePertenece corresponde a:
    // 0: barco del tamaño electo por el usuario o tamaño random en caso del de la computadora.
    // 1: barco tamaño 5.
    // 2: barco tamaño 4.
    // 3 y 4: barcos tamaño 3.
    // 5: barco tamaño 2.
    // 6: Si no pertenece a ningún barco.
    
    public PartesBarco(){
        
    }
    

   public PartesBarco(int cantVidas, int habilidad, boolean perteneceBarco, boolean disparoRecibido , int barcoAlQuePertenece) {
        this.cantVidas = cantVidas;
        this.habilidad = habilidad;
        this.perteneceBarco = perteneceBarco;
        this.disparoRecibido=disparoRecibido;
        this.barcoAlQuePertenece=barcoAlQuePertenece;
    }
   
   
    public int getCantVidas() {
        return cantVidas;
    }

    public void setCantVidas(int cantVidas) {
        this.cantVidas = cantVidas;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public boolean isPerteneceBarco() {
        return perteneceBarco;
    }

    public void setPerteneceBarco(boolean perteneceBarco) {
        this.perteneceBarco = perteneceBarco;
    }

    public boolean isDisparoRecibido() {
        return disparoRecibido;
    }

    public void setDisparoRecibido(boolean disparoRecibido) {
        this.disparoRecibido = disparoRecibido;
    }

    public int getBarcoAlQuePertenece() {
        return barcoAlQuePertenece;
    }

    public void setBarcoAlQuePertenece(int barcoAlQuePertenece) {
        this.barcoAlQuePertenece = barcoAlQuePertenece;
    }
    
    
    
    
}
