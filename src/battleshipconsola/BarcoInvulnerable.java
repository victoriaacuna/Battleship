
package battleshipconsola;


public class BarcoInvulnerable extends Barco{
    
    private boolean invulneable;
    
    public BarcoInvulnerable(boolean invulneable, String habilidad, int tamano, PartesBarco[] partesDelBarco, boolean destruido) {
        super(habilidad, tamano, partesDelBarco, destruido);
        this.invulneable=invulneable;
    }
    
 
    public boolean isInvulnerable() {
        return invulneable;
    }

    public void setInvulneable(boolean invulneable) {
        this.invulneable = invulneable;
        System.out.println("¡Se ha activado la habilidad del Barco Invulnerable!"
                + "\nDurante el próximo turno, el barco no podrá ser dañado");
    }
    
    public void serInvulnerable(BarcoInvulnerable barco2Tamano3, Mapa mapa, int fila, int columna, int op, Usuario usuario,
            Computador computador){
        if(op==1){
            int vidaAux=(mapa.getMapaComputador()[fila][columna].getCantVidas()+1);
            mapa.getMapaComputador()[fila][columna].setCantVidas(vidaAux);
            barco2Tamano3.setInvulneable(false);
        } else {
            int vidaAux=(mapa.getMapaUsuario()[fila][columna].getCantVidas()+1);
            mapa.getMapaUsuario()[fila][columna].setCantVidas(vidaAux);
            barco2Tamano3.setInvulneable(false);
        }
            
    }

}
