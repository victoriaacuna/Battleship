
package battleshipconsola;

import java.util.Random;


public class Computador extends Jugador{
    
    Random aleatorio = new Random();
    
    public Computador(int tamanoBarco, int cantVida){
        super(tamanoBarco, cantVida);
    }
    
    @Override
    public int cantVida(int dificultad){
        int cantVidaComputador;
        switch(dificultad){
            case 5:
                cantVidaComputador=3;
                break;
            case 4:
                cantVidaComputador=2;
                break;
            default: 
                cantVidaComputador=1;
                break;
        }
        return cantVidaComputador;
    }
    
    
     public void tamanoBarco(int tamanoBarcoUsuario){
         
        Random aleatorio = new Random();
        int random=aleatorio.nextInt(8);
        
        if(random<tamanoBarcoUsuario){
            //Utilización de recursividad para generar un número aleatorio entre el tamaño del barco electo por el usuario y 7.
            tamanoBarco(tamanoBarcoUsuario);
        } else {
            setTamanoBarco(random);
        }
        
    }
     
     public static PartesBarco[][] ubicarBarcoComputador(PartesBarco[][] mapaComputador, Barco[] vectorBarcosOrdenadosCompu, 
            int tamanoMapa, int cont, int indice, boolean ultimoBarcoUbicado, PartesBarco[][] mapaComputadorAuxiliar, 
            Barco barcoTam2, Barco barcoTam3, Barco barco2Tam3,Barco barcoTam4, Barco barcoTam5, Barco barcoCompu){
        
        // Booleano que controla si se pudo o no ubicar el barco en cuestión.
        boolean barcoUbicado=false;
        
        // Este representaría el caso base. Si el último barco se ubicó, devuelve el mapa por default.
        if(ultimoBarcoUbicado){
            return mapaComputador;
            
        } else { //En caso contrario, se evalúan ciertas condiciones para saber qué retornar.
            
            // Si el contador es mayor a 30, se debe seguir intentando ubicar el mismo barco.
            if(cont<31){
        
                Random aleatorio = new Random();
                // Se generan de manera aleatoria la fila, la columna, y la posición que va a ocupar el barco.
                // Las filas/columnas posibles son desde el 0 hasta el (tamanoMapa-1), mientras que la posición tomará el valor de 0 o 1. 
                // La posición 0 indica orientación vertical y la 1 horizontal.


                int i;

                int fila = aleatorio.nextInt(tamanoMapa);
                int columna = aleatorio.nextInt(tamanoMapa);
                int posicion = aleatorio.nextInt(2);

                // Booleano que controla si hay o no otra parte de barco donde se quiere ubicar uno.
                boolean noHayOtroBarco = true;

                // Para validar que en la coordenada aleatoria no haya ningún barco.
                if(!mapaComputador[fila][columna].isPerteneceBarco()){

                    // Para el caso de la posición vertical como primera opción.
                    if(posicion==0){
                        
                        // Para validar si el barco no se sale de los bordes.
                        if((fila+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador.length){

                            // Para validar si en las posiciones contiguas hay otros barcos.
                            i=fila;
                            while(noHayOtroBarco && i<(fila+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                if(mapaComputador[i][columna].isPerteneceBarco()){
                                    noHayOtroBarco=false;
                                }               
                            i++;
                            }

                            // Si no hay ningún otro barco, se procede a ubicar el barco.
                            if (noHayOtroBarco){
                                int j=0;
                                for(int k=fila; k<(fila+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                    mapaComputador[k][columna]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                    j++;
                                }
                                barcoUbicado=true;
                                
                                // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                if(indice==0){
                                    ultimoBarcoUbicado=true;
                                }
                                
                            } else { // Si no se puede ubicar verticalmente, se prueba si se puede ubicar horizontalmente
                                // en dicha coordenada.
                                
                                //Debe comprabar si con este intento no se excede del límite permitido.
                                if(cont+1<31){

                                    //El contador debe aumentar en una unidad, ya que sería otro intento de posicionamiento del barco. 
                                    cont++;

                                    // Para validar si el barco no se sale de los bordes.
                                    if((columna+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador[0].length){

                                        // Se le otroga el valor de verdad al booleano, para poder validar si hay otro barco 
                                        // las posiciones contiguas. 
                                        noHayOtroBarco=true;
                                        i=columna;
                                        while(noHayOtroBarco && i<(columna+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                            if(mapaComputador[fila][i].isPerteneceBarco()){
                                                noHayOtroBarco=false;
                                            }               
                                        i++;
                                        }

                                        // Si no hay ningún otro barco, se procede a ubicar el barco.
                                        if (noHayOtroBarco){
                                            int j=0;
                                            for(int k=columna; k<(columna+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                                mapaComputador[fila][k]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                                j++;
                                            }
                                            barcoUbicado=true;
                                            // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                            // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                            if(indice==0){
                                                ultimoBarcoUbicado=true;
                                            }

                                        }

                                    }
                                }
                            }

                        } else { //Si se sale de los bordes al tratar de ubicarlo verticalmente, se prueba ubicarlo horizontal.
                                
                                // Debe probar si con este intento no se excede del límite permitido.
                                if(cont+1<31){

                                    //El contador debe aumentar en una unidad, ya que sería otro intento de posicionamiento del barco. 
                                    cont++;

                                    // Para validar si el barco no se sale de los bordes.
                                    if((columna+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador[0].length){

                                        i=columna;
                                        while(noHayOtroBarco && i<(columna+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                            if(mapaComputador[fila][i].isPerteneceBarco()){
                                                noHayOtroBarco=false;
                                            }               
                                        i++;
                                        }

                                        // Si no hay ningún otro barco, se procede a ubicar el barco.
                                        if (noHayOtroBarco){
                                            int j=0;
                                            for(int k=columna; k<(columna+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                                mapaComputador[fila][k]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                                j++;
                                            }
                                            barcoUbicado=true;

                                            // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                            // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                            if(indice==0){
                                                ultimoBarcoUbicado=true;
                                            }

                                        }

                                    }
                            } 
                        }
 
                    } else { // Para el caso de la posición horizontal como primera opción.
                        
                        // Para validar si el barco no se sale de los bordes.
                        if((columna+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador[0].length){

                            // Para validar si en las posiciones contiguas hay otros barcos.
                            i=columna;
                            while(noHayOtroBarco && i<(columna+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                if(mapaComputador[fila][i].isPerteneceBarco()){
                                    noHayOtroBarco=false;
                                }               
                            i++;
                            }

                            // Si no hay ningún otro barco, se procede a ubicar el barco.
                            if (noHayOtroBarco){
                                int j=0;
                                for(int k=columna; k<(columna+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                    mapaComputador[fila][k]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                    j++;
                                }
                                barcoUbicado=true;
                                
                                // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                if(indice==0){
                                    ultimoBarcoUbicado=true;
                                }
                                
                            } else { // Si no se pudo ubicar de manera horizontal, se prueba ubicarlo verticalmente en la misma
                                // coordenada.
                                
                                //Debe comprabar si con este intento no se excede del límite permitido.
                                if(cont+1<31){

                                    //El contador debe aumentar en una unidad, ya que sería otro intento de posicionamiento del barco.
                                    cont++;

                                    // Para validar si el barco no se sale de los bordes.
                                    if((fila+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador.length){

                                        // Se le otroga el valor de verdad al booleano, para poder validar si hay otro barco 
                                        // las posiciones contiguas. 
                                        noHayOtroBarco=true;
                                        i=fila;
                                        while(noHayOtroBarco && i<(fila+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                            if(mapaComputador[i][columna].isPerteneceBarco()){
                                                noHayOtroBarco=false;
                                            }               
                                        i++;
                                        }

                                        // Si no hay ningún otro barco, se procede a ubicar el barco.
                                        if (noHayOtroBarco){
                                            int j=0;
                                            for(int k=fila; k<(fila+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                                mapaComputador[k][columna]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                                j++;
                                            }
                                            barcoUbicado=true;

                                            // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                            // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                            if(indice==0){
                                                ultimoBarcoUbicado=true;
                                            }

                                        }

                                    }
                                
                            }
                               
                            }

                        } else { // Si se sale de los bordes al tratar de ubicarlo horizontalmente, se prueba hacerlo verticalmente.
                            
                                //Debe comprabar si con este intento no se excede del límite permitido.
                                if(cont+1<31){

                                    //El contador debe aumentar en una unidad, ya que sería otro intento de posicionamiento del barco.
                                    cont++;

                                    // Para validar si el barco no se sale de los bordes.
                                    if((fila+vectorBarcosOrdenadosCompu[indice].getTamano())<=mapaComputador.length){

                                        // Se le otroga el valor de verdad al booleano, para poder validar si hay otro barco 
                                        // las posiciones contiguas. 
                                        noHayOtroBarco=true;
                                        i=fila;
                                        while(noHayOtroBarco && i<(fila+vectorBarcosOrdenadosCompu[indice].getTamano())){
                                            if(mapaComputador[i][columna].isPerteneceBarco()){
                                                noHayOtroBarco=false;
                                            }               
                                        i++;
                                        }

                                        // Si no hay ningún otro barco, se procede a ubicar el barco.
                                        if (noHayOtroBarco){
                                            int j=0;
                                            for(int k=fila; k<(fila+vectorBarcosOrdenadosCompu[indice].getTamano());k++){
                                                mapaComputador[k][columna]=vectorBarcosOrdenadosCompu[indice].partesDelBarco[j];
                                                j++;
                                            }
                                            barcoUbicado=true;

                                            // En caso de que este barco sea el último a ubicar y esté ubicado, se le da el valor de
                                            // verdadero al booleano para que entre en el caso base cuando se aplique la recursividad.
                                            if(indice==0){
                                                ultimoBarcoUbicado=true;
                                            }

                                        }

                                    }
                            }
                        }

                    }
                }
                
                if(barcoUbicado){
                    //Si logró ubicar el barco antes de exceder el límite de intentos permitidos(30), pasa a ubicar el siguiente
                    // valiéndose de la recursividad.
                    return ubicarBarcoComputador(mapaComputador, vectorBarcosOrdenadosCompu, tamanoMapa, 1, indice-1, 
                            ultimoBarcoUbicado, mapaComputadorAuxiliar, barcoTam2, barcoTam3, barco2Tam3,barcoTam4, barcoTam5, 
                            barcoCompu);
                } else {
                    //Si no logró ubicar el barco, sigue intentando (utilizando recursividad) hasta llegar al límite de intentos 
                    // permitidos (30).
                    
                    return ubicarBarcoComputador(mapaComputador, vectorBarcosOrdenadosCompu, tamanoMapa, cont+1, indice, 
                            ultimoBarcoUbicado, mapaComputadorAuxiliar, barcoTam2, barcoTam3, barco2Tam3,barcoTam4, barcoTam5, 
                            barcoCompu);
                }
                
                
            } else {
                // Si ya excedió el límite de la cantidad de veces para intentar reubicar el mismo barco, pueden existir dos casos.
                
                // En caso de no poder ubicar el barco en la última posición del vectorBarcosOrdenadosCompu, 
                // que es el de mayor tamaño, ya habrá agotado todas las posiciones posibles y se devuelve el mapa por default.
                if(indice==5){
                    return mapaComputadorPorDefault(mapaComputadorAuxiliar, barcoTam2, barcoTam3, barco2Tam3, barcoTam4, barcoTam5,
                            barcoCompu, tamanoMapa);
                } else { // Caso en el que el índice no implique la ultima posición del vector, se utiliza recursividad,
                    // borrando el barco anterior del mapa y volviéndolo a ubicar.
                    
                    mapaComputador=borrarBarco(mapaComputador, vectorBarcosOrdenadosCompu[indice+1]);
                    return ubicarBarcoComputador(mapaComputador, vectorBarcosOrdenadosCompu,tamanoMapa, 1, indice+1, 
                            ultimoBarcoUbicado, mapaComputadorAuxiliar, barcoTam2, barcoTam3, barco2Tam3,barcoTam4, barcoTam5, 
                            barcoCompu);
                }
            }
            
        }
    }
     
     public static PartesBarco[][] borrarBarco(PartesBarco[][] mapa, Barco barco){
        
        for(int i=0; i<mapa.length;i++){
            for(int j=0; j<mapa[0].length;j++){
                
                // Se busca en la matriz dónde comenzo a ubicarse el barco.
                if(mapa[i][j]==barco.partesDelBarco[0]){
                    
                    // Como se va a evaluar primero si está en posición horizontal, se evalúa si está o no en un borde para saber
                    // si es posible que esté en posición horizontal.
                    if(j+barco.getTamano()<=mapa[0].length){
                    
                        // Para saber si en efecto el barco está ubicado horizontalmente.
                        if(mapa[i][j+1]==barco.partesDelBarco[1]){

                            //Si está ubicado horizontalmente, se procede a borrarlo.
                            for(int l=j;l<(j+barco.partesDelBarco.length);l++){
                                mapa[i][l]=new PartesBarco(0, 1, false, false,6);
                            }
                        } else { // Si no está ubicado horizontalmente, lo está verticalmente. Se procede a borrarlo.

                            for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                                mapa[l][j]=new PartesBarco(0, 1, false, false,6);
                            }
                        }
                    
                    } else { //Si se sale de los bordes cuando se trata de probar si está ubicado horizontalmente, la única
                        // opción que queda es que esté ubicado verticalmente. Así que se procede a borrar el barco.
                        for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                            mapa[l][j]=new PartesBarco(0, 1, false, false,6);
                        }
                    }
                }
                
            }
        }
        
        return mapa;
    }
    

    
       public static PartesBarco[][] mapaComputadorPorDefault(PartesBarco[][] mapaComputador, Barco barcoTam2, 
               Barco barcoTam3, Barco barco2Tam3,Barco barcoTam4, Barco barcoTam5, Barco barcoCompu, int tamanoMapa){
        
        int r;
        switch(tamanoMapa){
            case 7:
                r=0;
                for(int i=0;i<(0+barcoCompu.getTamano());i++){
                    mapaComputador[i][1]=barcoCompu.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=0;i<(0+barcoTam5.getTamano());i++){
                    mapaComputador[i][6]=barcoTam5.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=3;i<(3+barcoTam4.getTamano());i++){
                    mapaComputador[i][5]=barcoTam4.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=2;i<(2+barco2Tam3.getTamano());i++){
                    mapaComputador[i][0]=barco2Tam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=3;i<(3+barcoTam3.getTamano());i++){
                    mapaComputador[1][i]=barcoTam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=5;i<(5+barcoTam2.getTamano());i++){
                    mapaComputador[i][6]=barcoTam2.partesDelBarco[r];
                    r++;
                }
                return mapaComputador;
            case 8: 
                r=0;
                for(int i=1;i<(1+barcoCompu.getTamano());i++){
                    mapaComputador[7][i]=barcoCompu.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=3;i<(3+barcoTam5.getTamano());i++){
                    mapaComputador[0][i]=barcoTam5.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=1;i<(1+barcoTam4.getTamano());i++){
                    mapaComputador[i][0]=barcoTam4.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=2;i<(2+barcoTam3.getTamano());i++){
                    mapaComputador[5][i]=barcoTam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=2;i<(2+barco2Tam3.getTamano());i++){
                    mapaComputador[i][4]=barco2Tam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=2;i<(2+barcoTam2.getTamano());i++){
                    mapaComputador[i][6]=barcoTam2.partesDelBarco[r];
                    r++;
                }
                return mapaComputador;

            default:
                r=0;
                for(int i=0;i<(0+barcoCompu.getTamano());i++){
                    mapaComputador[i][6]=barcoCompu.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=1;i<(1+barcoTam5.getTamano());i++){
                    mapaComputador[8][i]=barcoTam5.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=0;i<(0+barcoTam4.getTamano());i++){
                    mapaComputador[5][i]=barcoTam4.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=1;i<(1+barcoTam3.getTamano());i++){
                    mapaComputador[0][i]=barcoTam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=1;i<(1+barco2Tam3.getTamano());i++){
                    mapaComputador[i][8]=barco2Tam3.partesDelBarco[r];
                    r++;
                }
                r=0;
                for(int i=2;i<(2+barcoTam2.getTamano());i++){
                    mapaComputador[2][i]=barcoTam2.partesDelBarco[r];
                    r++;
                }
                return mapaComputador;

        }   
        
       
    }

    @Override
    public void disparar(Mapa mapa, Usuario usuario, Computador computador, BarcoSinHabilidad barcoCompu, BarcoSinHabilidad barcoTamano5Compu, BarcoRevelador 
            barcoTamano4Compu, BarcoVidaExtra barcoTamano3Compu, BarcoInvulnerable barco2Tamano3Compu, BarcoQueSana 
            barcoTamano2Compu) {
        
       int fila, columna, nuevaCantVida, barcoAlQuePertenece=6, habilidad=0, vidaAux;
       boolean destruido=false;
       
       int[] filacolumna = new int[2];
       filacolumna=seleccionarFilaYColumna(mapa);
       fila=filacolumna[0];
       columna=filacolumna[1];
       
       if(disparosFallidos%5==0 && disparosFallidos!=0){
           int[] posicion = new int[2];
           posicion=barcoTamano4Compu.revelarPosicionParaDisparar(mapa);
           fila=posicion[0];
           columna=posicion[1];
           barcoTamano4Compu.setHaRevelado(true);
       }
       
       if(mapa.getMapaUsuario()[fila][columna].isPerteneceBarco()){
           // Si esa parte del mapa pertenece a un barco, procede a quitarle vidas si puede y a indicar que recibió un disparo.
           if(mapa.getMapaUsuario()[fila][columna].getCantVidas()!=0){
               
               // Para el implemento de las habilidades...
               habilidad=mapa.getMapaUsuario()[fila][columna].getHabilidad();
               switch(habilidad){
                   case 2:
                       if(!barcoTamano2Compu.isSana()){
                           barcoTamano2Compu.setSana(true);
                       } 
                       break;
                   case 3:
                       if(!barco2Tamano3Compu.isInvulnerable()){
                           barco2Tamano3Compu.setInvulneable(true);
                           
                       } else {
                           barco2Tamano3Compu.serInvulnerable(barco2Tamano3Compu, mapa, fila, columna,2,usuario, computador);
                           
                       }
                       break;
                   default:
                       break;
                   
               }
               
            if(habilidad!=2 && barcoTamano2Compu.isSana()){
                barcoTamano2Compu.Sanar(mapa, barcoTamano2Compu, 2, usuario, computador);
            }  
            nuevaCantVida=mapa.getMapaUsuario()[fila][columna].getCantVidas()-1;
            mapa.getMapaUsuario()[fila][columna].setDisparoRecibido(true);
            mapa.getMapaUsuario()[fila][columna].setCantVidas(nuevaCantVida);
            barcoAlQuePertenece=mapa.getMapaUsuario()[fila][columna].getBarcoAlQuePertenece();
            
           
            if(habilidad != 3 && barco2Tamano3Compu.isInvulnerable()){
                barco2Tamano3Compu.setInvulneable(false);
            }
            
            if(habilidad==3 && !barco2Tamano3Compu.isInvulnerable()){
                if(mapa.getMapaUsuario()[fila][columna].getCantVidas()==usuario.getCantVida()){
                    mapa.getMapaUsuario()[fila][columna].setDisparoRecibido(false);
                }
            }
            

            // Si esta parte se quedó sin vidas, pasa a evaluar si el barco puede estar destruido.
            if(nuevaCantVida==0){
                switch(barcoAlQuePertenece){
                    case 0:
                        destruido=mapa.elBarcoEstaDestruido(barcoCompu, 2);
                        if(destruido){
                            barcoCompu.setDestruido(destruido);
                        }
                        break;
                    case 1:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano5Compu, 2);
                        if(destruido){
                            barcoTamano5Compu.setDestruido(destruido);
                        }
                        break;
                    case 2:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano4Compu, 2);
                        if(destruido){
                            barcoTamano4Compu.setDestruido(destruido);
                        }
                        break;
                    case 3:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano3Compu, 2);
                        if(destruido){
                            barcoTamano3Compu.setDestruido(destruido);
                        }
                        break;
                    case 4:
                        destruido=mapa.elBarcoEstaDestruido(barco2Tamano3Compu, 2);
                        if(destruido){
                            barco2Tamano3Compu.setDestruido(destruido);
                        }
                        break;
                    default:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano2Compu, 2);
                        if(destruido){
                            barcoTamano2Compu.setDestruido(destruido);
                        }
                        break;    
                }
            }

            }
       } else {
           
           disparosFallidos=disparosFallidos+1;
           if(barcoTamano2Compu.isSana()){
                barcoTamano2Compu.Sanar(mapa, barcoTamano2Compu, 2, usuario, computador);
            }
           mapa.getMapaUsuario()[fila][columna].setDisparoRecibido(true);
       }  
       
       contDisparos++;
       
       
        
       
    }

    public int[] seleccionarFilaYColumna(Mapa mapa){
        int[] filacolumna = new int[2];
        int fila, columna;
        boolean encontrado=true;
        
        do{
            encontrado=true;
            fila=aleatorio.nextInt((mapa.getTamanoMapa()));
            columna=aleatorio.nextInt((mapa.getTamanoMapa()));
            
            if(mapa.getMapaUsuario()[fila][columna].isDisparoRecibido() && !mapa.getMapaUsuario()[fila][columna].isPerteneceBarco()){
               encontrado=false; 
            }
            
        } while(!encontrado);
        
        filacolumna[0]=fila;
        filacolumna[1]=columna;
        
        return filacolumna;
    }
       
}
