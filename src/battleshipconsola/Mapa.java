/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipconsola;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Mapa {
    private static Scanner sc = new Scanner (System.in);
    private int tamanoMapa;
    private PartesBarco[][] mapaUsuario, mapaUsuarioOriginal, mapaComputador, mapaComputadorOriginal;
    
    public Mapa(int tamanoMapa){
        this.tamanoMapa = tamanoMapa;
        
    }
    
    public int seleccionarMapa(){
        int tamanoMapa;
        do{
            System.out.println("Seleccione las dimensiones del mapa con el que jugará.\n[1] 7x7.\n[2] 8x8.\n[3] 9x9.");
            tamanoMapa= sc.nextInt();
            if(tamanoMapa>3 || tamanoMapa<1){
                System.out.println("¡Ups! Opción inválida.");
                tamanoMapa=4;
            } else {
                switch(tamanoMapa){
                    case 1:
                        tamanoMapa=7;
                        break;
                    case 2:
                        tamanoMapa=8;
                        break;
                    case 3: 
                        tamanoMapa=9;
                        break;
                }
            }
        } while(tamanoMapa==4);
        return tamanoMapa;
    }
    
        public static void crearMapas(Mapa mapa){
        // Para crear el mapa, que es una matriz de partes de barco de dos dimensiones...
        mapa.setMapaUsuario(mapa.crearMapa(mapa.getTamanoMapa()));

        /* Para crear un mapa auxiliar vacío en caso de que el usuario desee reiniciar el proceso de
        ubicación de los barcos */
        mapa.setMapaUsuarioOriginal(mapa.crearMapa(mapa.getTamanoMapa()));

        // Se hace lo mismo con las matrices para los mapas de la computadora.
        mapa.setMapaComputador(mapa.crearMapa(mapa.getTamanoMapa()));
        mapa.setMapaComputadorOriginal(mapa.crearMapa(mapa.getTamanoMapa()));
        
        }
    
     public void imprimirLeyendaParaUbicarBarcos(){
        System.out.println("\nEn el mapa, cada uno de los siguientes símbolos significa:");
        System.out.println("\"~\" Posición del mapa vacía.");
        System.out.println("\"∆\" Posición donde ha ubicado partes de un barco.");
    }
     
     public void imprimirLeyenda(){
        System.out.println("\nEn el mapa, cada uno de los siguientes símbolos significa:");
        System.out.println("\"~\" Posición del mapa vacía.");
        System.out.println("\"X\" Posición donde han ocurrido disparos fallidos.");
        System.out.println("\"∆\" Posición donde hay partes de un barco que no han sido dañadas.");
        System.out.println("\"≡\" Posición donde hay partes dañadas de un barco.");
        System.out.println("\"♦\" Posición donde hay partes destruidas de un barco.");
        
    }

    public void imprimirMapaUsuario(PartesBarco[][] mapaUsuario, int cantVidaUsuario, int tamanoMapa){
        System.out.println("\n");
        System.out.println("MAPA JUGADOR.\n");
        for(int i=0; i<mapaUsuario.length;i++){
            
            if(i==0){
                
                if(tamanoMapa==7){
                    System.out.println("           1         2         3         4         5         6         7");
                    System.out.println("           -         -         -         -         -         -         -");
                    
                }
                if(tamanoMapa==8){
                    System.out.println("           1         2         3         4         5         6         7         8");
                    System.out.println("           -         -         -         -         -         -         -         -");
                    
                }
                if(tamanoMapa==9){
                    System.out.println("           1         2         3         4         5         6         7         8         9");
                    System.out.println("           -         -         -         -         -         -         -         -         -");
                    
                }
            }
            
            for(int j=0; j<mapaUsuario[0].length; j++){
                
                if(j==0){
                    System.out.print(i+1 + "|");
                }
                
                if(mapaUsuario[i][j].isPerteneceBarco()){
                    
                    if(mapaUsuario[i][j].getBarcoAlQuePertenece()==3){
                        
                        if(mapaUsuario[i][j].getCantVidas()>cantVidaUsuario){
                            System.out.print("         ∆");
                        } else {
                            if(mapaUsuario[i][j].getCantVidas()==0){
                                System.out.print("         ♦");  
                            } else {
                                System.out.print("         ≡");
                            }
                        }
                        
                    } else {
                        
                       if(mapaUsuario[i][j].getCantVidas()==cantVidaUsuario){
                            System.out.print("         ∆");
                        } else {
                            if(mapaUsuario[i][j].getCantVidas()==0){
                                System.out.print("         ♦");  
                            } else {
                                System.out.print("         ≡");
                            }
                        } 
                    }
                    
                    
                } else {
                    if(mapaUsuario[i][j].isDisparoRecibido()){
                        System.out.print("         X");
                    } else {
                        System.out.print("         ~");
                    }
                }
                
                if(j==(mapaUsuario[0].length-1)){
                    System.out.print("         |");
                }
                
            }
            System.out.println("");
            
            if(i==(mapaUsuario.length-1)){
                if(tamanoMapa==7){
                    System.out.println("           -         -         -         -         -         -         -");
                    System.out.println("");
                }
                if(tamanoMapa==8){
                    System.out.println("           -         -         -         -         -         -         -         -");
                    System.out.println("");
                }
                if(tamanoMapa==9){
                    System.out.print("           -         -         -         -         -         -         -         -         -");
                    System.out.println("");
                }
            }
            
        }
    }
    
    public void imprimirMapaComputador(PartesBarco[][] mapaComputador, int cantVidaComputador, int tamanoMapa){
        System.out.println("\n");
        System.out.println("MAPA COMPUTADOR.\n");
        for(int i=0; i<mapaComputador.length;i++){
            
            if(i==0){
                
                if(tamanoMapa==7){
                    System.out.println("           1         2         3         4         5         6         7");
                    System.out.println("           -         -         -         -         -         -         -");
                    
                }
                if(tamanoMapa==8){
                    System.out.println("           1         2         3         4         5         6         7         8");
                    System.out.println("           -         -         -         -         -         -         -         -");
                    
                }
                if(tamanoMapa==9){
                    System.out.println("           1         2         3         4         5         6         7         8         9");
                    System.out.println("           -         -         -         -         -         -         -         -         -");
                    
                }
            }
            
            for(int j=0; j<mapaComputador[0].length; j++){
                
                if(j==0){
                    System.out.print(i+1 + "|");
                }
                
                if(mapaComputador[i][j].isDisparoRecibido()){
                    if(mapaComputador[i][j].isPerteneceBarco()){
                            if(mapaComputador[i][j].getCantVidas()==0){
                                System.out.print("         ♦");
                            } else {
                                System.out.print("         ≡");
                            }
                    } else {
                        System.out.print("         X");
                        }
                    } else {
                        System.out.print("         ~");
                    }
                
                
                if(j==(mapaComputador[0].length-1)){
                    System.out.print("         |");
                }
                
            }
            System.out.println("");
            
            if(i==(mapaComputador.length-1)){
                if(tamanoMapa==7){
                    System.out.println("           -         -         -         -         -         -         -");
                    System.out.println("");
                }
                if(tamanoMapa==8){
                    System.out.println("           -         -         -         -         -         -         -         -");
                    System.out.println("");
                }
                if(tamanoMapa==9){
                    System.out.print("           -         -         -         -         -         -         -         -         -");
                    System.out.println("");
                }
            }
            
        }
    }

    
/* Se llenan las matrices de los mapas creados con partes de barco que no tienen ni vida, ni habilidades, 
    ni pertenecen a ningún barco. */
    
   public PartesBarco[][] crearMapa (int tamanoMapa){
       PartesBarco[][] mapa = new PartesBarco[tamanoMapa][tamanoMapa];
       for(int i=0; i<mapa.length;i++){
            for(int j=0; j<mapa[0].length; j++){
                mapa[i][j]= new PartesBarco(0, 1, false, false, 6);
            }
        }
       return mapa;
   }  
   
   public boolean elBarcoEstaDestruido(Barco barco, int op){
       boolean destruido=true;
       
       if(op==1){
           for(int i=0; i<mapaComputador.length;i++){
            for(int j=0; j<mapaComputador[0].length;j++){
                
                // Se busca en la matriz dónde comenzo a ubicarse el barco.
                if(mapaComputador[i][j].getBarcoAlQuePertenece()==barco.partesDelBarco[0].getBarcoAlQuePertenece()){
                    
                    // Como se va a evaluar primero si está en posición horizontal, se evalúa si está o no en un borde para saber
                    // si es posible que esté en posición horizontal.
                    if(j+barco.getTamano()<=mapaComputador[0].length){
                    
                        // Para saber si en efecto el barco está ubicado horizontalmente.
                        if(mapaComputador[i][j+1].getBarcoAlQuePertenece()==barco.partesDelBarco[0].getBarcoAlQuePertenece()){

                            //Si está ubicado horizontalmente, se a comprobar si está destruido.
                            for(int l=j;l<(j+barco.partesDelBarco.length);l++){
                                if(mapaComputador[i][l].getCantVidas()!=0){
                                    return false;
                                }
                            }
                            return true;
                            
                        } else { // Si no está ubicado horizontalmente, lo está verticalmente. Se procede a borrarlo.

                            for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                                if(mapaComputador[l][j].getCantVidas()!=0){
                                    return false;
                                }
                            }
                            return true;
                        }
                    
                    } else { //Si se sale de los bordes cuando se trata de probar si está ubicado horizontalmente, la única
                        // opción que queda es que esté ubicado verticalmente. Así que se procede a borrar el barco.
                        
                        for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                                if(mapaComputador[l][j].getCantVidas()!=0){
                                    
                                    return false;
                                }
                        }
                        return true;
                        
                    }
                }
                
            }
        }
       } else {
           for(int i=0; i<mapaUsuario.length;i++){
            for(int j=0; j<mapaUsuario[0].length;j++){
                
                // Se busca en la matriz dónde comenzo a ubicarse el barco.
                if(mapaUsuario[i][j].getBarcoAlQuePertenece()==barco.partesDelBarco[0].getBarcoAlQuePertenece()){
                    
                    // Como se va a evaluar primero si está en posición horizontal, se evalúa si está o no en un borde para saber
                    // si es posible que esté en posición horizontal.
                    if(j+barco.getTamano()<=mapaUsuario[0].length){
                    
                        // Para saber si en efecto el barco está ubicado horizontalmente.
                        if(mapaUsuario[i][j+1].getBarcoAlQuePertenece()==barco.partesDelBarco[1].getBarcoAlQuePertenece()){

                            //Si está ubicado horizontalmente, se a comprobar si está destruido.
                            for(int l=j;l<(j+barco.partesDelBarco.length);l++){
                                if(mapaUsuario[i][l].getCantVidas()!=0){
                                    return false;
                                    
                                }
                            }
                            return true;
                            
                            
                        } else { // Si no está ubicado horizontalmente, lo está verticalmente. Se procede a borrarlo.

                            for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                                if(mapaUsuario[l][j].getCantVidas()!=0){
                                    return false;
                                }
                            }
                            return true;
                        }
                    
                    } else { //Si se sale de los bordes cuando se trata de probar si está ubicado horizontalmente, la única
                        // opción que queda es que esté ubicado verticalmente. Así que se procede a borrar el barco.
                        
                        for(int l=i;l<(i+barco.partesDelBarco.length);l++){
                                if(mapaUsuario[l][j].getCantVidas()!=0){
                                    return false;
                                }
                        }
                        return true;
                    }
                }
                
            }
        }
       }
       
       return destruido;
   }
   
   public int[] buscarBarco(Mapa mapa, int barcoAlQuePertenece){
       int i=0,j=0;
       boolean encontrado=false;
       int[] ubicacion = new int[3];
       
       while((i<mapa.getMapaUsuario().length) && !encontrado){
           j=0;
            while((j<mapa.getMapaUsuario()[0].length) && !encontrado){
                // Se busca en la matriz dónde está el barco oculto.
                    if(mapa.getMapaUsuario()[i][j].getBarcoAlQuePertenece()==barcoAlQuePertenece){
                        
                        //Se evalúa si puede estar horizontalmente;
                        if(j+1<mapa.getTamanoMapa()){
                            // Se prueba si en efecto está ubicado horizontalmente, sino, lo estará vertical.
                            if(mapa.getMapaUsuario()[i][j+1].getBarcoAlQuePertenece()==barcoAlQuePertenece){
                                ubicacion[0]=1;
                                ubicacion[1]=i;
                                ubicacion[2]=j;
                                encontrado=true;
                                return ubicacion;
                            } else {
                                ubicacion[0]=0;
                                ubicacion[1]=i;
                                ubicacion[2]=j;
                                encontrado=true;
                                return ubicacion;
                            }
                        } else { // Si no puede estar ubicado horizontalmente, entonces lo está vertical.
                            ubicacion[0]=0;
                            ubicacion[1]=i;
                            ubicacion[2]=j;
                            encontrado=true;
                            return ubicacion;
                        }
                        
                        
                    }
                j++;
            }
            i++;
        }
        return ubicacion;
   }
   

   public int getTamanoMapa() {
        return tamanoMapa;
    }

    public void setTamanoMapa(int tamanoMapa) {
        this.tamanoMapa = tamanoMapa;
    }

    public PartesBarco[][] getMapaUsuario() {
        return mapaUsuario;
    }

    public PartesBarco[][] getMapaUsuarioOriginal() {
        return mapaUsuarioOriginal;
    }

    public void setMapaUsuario(PartesBarco[][] mapaUsuario) {
        this.mapaUsuario = mapaUsuario;
    }

    public void setMapaUsuarioOriginal(PartesBarco[][] mapaUsuarioOriginal) {
        this.mapaUsuarioOriginal = mapaUsuarioOriginal;
    }

    public PartesBarco[][] getMapaComputador() {
        return mapaComputador;
    }

    public PartesBarco[][] getMapaComputadorOriginal() {
        return mapaComputadorOriginal;
    }

    public void setMapaComputador(PartesBarco[][] mapaComputador) {
        this.mapaComputador = mapaComputador;
    }

    public void setMapaComputadorOriginal(PartesBarco[][] mapaComputadorOriginal) {
        this.mapaComputadorOriginal = mapaComputadorOriginal;
    }
   
    
    
}
