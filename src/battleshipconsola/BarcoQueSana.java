
package battleshipconsola;

// @author victoriaacuna

public class BarcoQueSana extends Barco{
    private boolean sana;
    

    public BarcoQueSana(boolean sana, String habilidad, int tamano, PartesBarco[] partesDelBarco, boolean destruido) {
        super(habilidad, tamano, partesDelBarco, destruido);
        this.sana = sana;
    }
    
    public boolean isSana() {
        return sana;
    }

    public void setSana(boolean sana) {
        this.sana = sana;
        
            System.out.println("¡Se ha activado la habilidad del Barco que Sana! "
                + "\nMientras no sea atacado, el barco regenerará la vida de aquellas partes dañadas. (No las destruidas).");
        
        
    }
    
    public void Sanar(Mapa mapa, BarcoQueSana barcoTam2, int op, Usuario usuario, Computador computador){
        
        int[] posicionBarcoQueSana = new int[3];
        posicionBarcoQueSana = buscarBarcoSana(mapa, barcoTam2, op);
        boolean habilidadAplicada=false;
        int i=0, cantVidaNueva;
        
        if(op==1){
            if(posicionBarcoQueSana[0]==1){ // Caso de que el barco esté ubicado verticalmente.
                i=posicionBarcoQueSana[1];
                while(i < (posicionBarcoQueSana[1] + barcoTam2.getTamano()) && !habilidadAplicada) {
                    if(mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].getCantVidas()!=computador.getCantVida()){
                        if(mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].getCantVidas()!=0){
                            
                            cantVidaNueva=mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].getCantVidas()+1;
                            mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].setCantVidas(cantVidaNueva);
                            habilidadAplicada=true;
                            if(mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].getCantVidas()==computador.getCantVida()){
                                mapa.getMapaComputador()[i][posicionBarcoQueSana[2]].setDisparoRecibido(false);
                            }
                            System.out.println("¡El Barco que Sana ha recuperado una vida!");
                        }
                    }
                    i++;
                }
            } else { //En caso de que esté horizontal.
                
                i=posicionBarcoQueSana[2];
                while(i < (posicionBarcoQueSana[2] + barcoTam2.getTamano()) && !habilidadAplicada) {
                    if(mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].getCantVidas()!=computador.getCantVida()){
                        if(mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].getCantVidas()!=0){
                            cantVidaNueva=mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].getCantVidas()+1;
                            mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].setCantVidas(cantVidaNueva);
                            habilidadAplicada=true;
                            if(mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].getCantVidas()==computador.getCantVida()){
                                mapa.getMapaComputador()[posicionBarcoQueSana[1]][i].setDisparoRecibido(false);
                            }
                            System.out.println("¡El Barco que Sana ha recuperado una vida!");
                        }
                    }
                    i++;
                }
                
            }
        } else {
            if(posicionBarcoQueSana[0]==1){ // Caso de que el barco esté ubicado verticalmente.
                i=posicionBarcoQueSana[1];
                while(i < (posicionBarcoQueSana[1] + barcoTam2.getTamano()) && !habilidadAplicada) {
                    if(mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].getCantVidas()!=usuario.getCantVida()){
                        if(mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].getCantVidas()!=0){
                            
                            cantVidaNueva=mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].getCantVidas()+1;
                            mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].setCantVidas(cantVidaNueva);
                            habilidadAplicada=true;
                            
                            if(mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].getCantVidas()==usuario.getCantVida()){
                                mapa.getMapaUsuario()[i][posicionBarcoQueSana[2]].setDisparoRecibido(false);
                            }
                            System.out.println("¡El Barco que Sana ha recuperado una vida!");
                        }
                    }
                    i++;
                }
            } else { //En caso de que esté horizontal.
                
                i=posicionBarcoQueSana[2];
                while(i < (posicionBarcoQueSana[2] + barcoTam2.getTamano()) && !habilidadAplicada) {
                    if(mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].getCantVidas()!=usuario.getCantVida()){
                        if(mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].getCantVidas()!=0){
                            
                            cantVidaNueva=mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].getCantVidas()+1;
                            mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].setCantVidas(cantVidaNueva);
                            habilidadAplicada=true;
                            if(mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].getCantVidas()==usuario.getCantVida()){
                                mapa.getMapaUsuario()[posicionBarcoQueSana[1]][i].setDisparoRecibido(false);
                            }
                            
                            System.out.println("¡El Barco que Sana ha recuperado una vida!");
                        }
                    }
                    i++;
                }
                
            }
        }
        
        
        
        
    }
    
    public int[] buscarBarcoSana(Mapa mapa, BarcoQueSana barcoTam2, int op){
        int[] posicionBarcoQueSana = new int[3];
        //Este vector se va a llenar en su posición [0], con [1] si está ubicado verticalmente y con [2] si está horizontal.
        // La posición [2] se llenará con el número de la fila donde está ubicada su primera parte y la [3], la columna.
        
        if(op==1){
            
            for(int i=0; i<mapa.getMapaComputador().length;i++){
            for(int j=0; j<mapa.getMapaComputador()[0].length;j++){
                
                // Se busca en la matriz dónde comenzo a ubicarse el barco.
                if(mapa.getMapaComputador()[i][j].getBarcoAlQuePertenece()==barcoTam2.partesDelBarco[0].getBarcoAlQuePertenece()){
                    
                    // Como se va a evaluar primero si está en posición horizontal, se evalúa si está o no en un borde para saber
                    // si es posible que esté en posición horizontal.
                    if(j+barcoTam2.getTamano()<=mapa.getMapaComputador()[0].length){
                    
                        // Para saber si en efecto el barco está ubicado horizontalmente.
                        if(mapa.getMapaComputador()[i][j+1].getBarcoAlQuePertenece()==barcoTam2.partesDelBarco[1].getBarcoAlQuePertenece()){

                            //Si está ubicado horizontalmente, se procede a pasar la coordenada.
                            posicionBarcoQueSana[0]=2;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                            
                        } else { // Si no está ubicado horizontalmente, lo está verticalmente. 

                            posicionBarcoQueSana[0]=1;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                        }
                    
                    } else { //Si se sale de los bordes cuando se trata de probar si está ubicado horizontalmente, la única
                        // opción que queda es que esté ubicado verticalmente. 
                        
                            posicionBarcoQueSana[0]=1;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                    }
                }
                
            }
        }
            
        } else {
            
            for(int i=0; i<mapa.getMapaUsuario().length;i++){
            for(int j=0; j<mapa.getMapaUsuario()[0].length;j++){
                
                // Se busca en la matriz dónde comenzo a ubicarse el barco.
                if(mapa.getMapaUsuario()[i][j].getBarcoAlQuePertenece()==barcoTam2.partesDelBarco[0].getBarcoAlQuePertenece()){
                    
                    // Como se va a evaluar primero si está en posición horizontal, se evalúa si está o no en un borde para saber
                    // si es posible que esté en posición horizontal.
                    if(j+barcoTam2.getTamano()<=mapa.getMapaUsuario()[0].length){
                    
                        // Para saber si en efecto el barco está ubicado horizontalmente.
                        if(mapa.getMapaUsuario()[i][j+1].getBarcoAlQuePertenece()==barcoTam2.partesDelBarco[1].getBarcoAlQuePertenece()){

                            //Si está ubicado horizontalmente, se procede a pasar la coordenada.
                            posicionBarcoQueSana[0]=2;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                            
                        } else { // Si no está ubicado horizontalmente, lo está verticalmente. 

                            posicionBarcoQueSana[0]=1;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                        }
                    
                    } else { //Si se sale de los bordes cuando se trata de probar si está ubicado horizontalmente, la única
                        // opción que queda es que esté ubicado verticalmente. 
                        
                            posicionBarcoQueSana[0]=1;
                            posicionBarcoQueSana[1]=i;
                            posicionBarcoQueSana[2]=j;
                            return posicionBarcoQueSana;
                    }
                }
                
            }
            
        }
        
        
    }
    
   return posicionBarcoQueSana;
   
}
}
