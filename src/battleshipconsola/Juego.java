
package battleshipconsola;

import java.util.Scanner;


public class Juego {
    
    private Scanner sc = new Scanner(System.in);
    private int ganador = 0;
    private boolean juegoTerminado;
        
    public Juego(int ganador, boolean juegoTerminado){
        this.ganador = 0;
        this.juegoTerminado = juegoTerminado;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }
    public Juego(Mapa mapa, Computador computador, Usuario usuario, Menu menu, BarcoSinHabilidad barcoCompu, 
            BarcoSinHabilidad barcoTamano5Compu, BarcoRevelador barcoTamano4Compu, BarcoVidaExtra barcoTamano3Compu, 
            BarcoInvulnerable barco2Tamano3Compu, BarcoQueSana barcoTamano2Compu, BarcoSinHabilidad barcoUsuario, 
            BarcoSinHabilidad barcoTamano5, BarcoRevelador barcoTamano4, BarcoVidaExtra barcoTamano3, 
            BarcoInvulnerable barco2Tamano3, BarcoQueSana barcoTamano2){
        
        juegoTerminado=false;
        menu.iniciarJuego();
        impresion(mapa, usuario, computador);
        int opUser;
        
        do{
          usuario.disparar(mapa, usuario, computador, barcoCompu, barcoTamano5Compu, barcoTamano4Compu, barcoTamano3Compu, barco2Tamano3Compu, 
                  barcoTamano2Compu);
          mapa.imprimirMapaComputador(mapa.getMapaComputador(), computador.getCantVida(), mapa.getTamanoMapa());
          System.out.println("\nAhora le toca disparar a la computadora...");
          computador.disparar(mapa, usuario, computador, barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, barco2Tamano3, barcoTamano2);
          mapa.imprimirMapaUsuario(mapa.getMapaUsuario(), usuario.getCantVida(), mapa.getTamanoMapa()); 
          
          if(barcoUsuario.isDestruido() && barcoTamano5.isDestruido() && barcoTamano4.isDestruido() && barcoTamano3.isDestruido()
                  && barco2Tamano3.isDestruido() && barcoTamano2.isDestruido()){
              ganador=2;
          }
          
          if(barcoCompu.isDestruido() && barcoTamano5Compu.isDestruido() && barcoTamano4Compu.isDestruido() && 
                  barcoTamano3Compu.isDestruido() && barco2Tamano3Compu.isDestruido() && barcoTamano2Compu.isDestruido()){
              ganador=1;
          }
          if(ganador==0){
                System.out.println("\nSi no desea continuar jugando, introduzca el número [1]. Si sí, introduzca cualquier otro.");
                opUser=sc.nextInt();

                if(opUser==1){
                    juegoTerminado=true;
                }
          }
          
        } while(!juegoTerminado && (ganador==0));
        
        if(ganador!=0){
            if(ganador==1){
                System.out.println("\n\n¡FELICIDADES!\nUSTED HA GANADO!");
            } else {
                System.out.println("¡Lo sentimos!\nUsted ha perdido.\n¡Suerte para la próxima!");
            }
        }
    }
    public void impresion(Mapa mapa, Usuario usuario, Computador computador){
        mapa.imprimirLeyenda();
        mapa.imprimirMapaUsuario(mapa.getMapaUsuario(), usuario.getCantVida(), mapa.getTamanoMapa());
        mapa.imprimirMapaComputador(mapa.getMapaComputador(), computador.getCantVida(), mapa.getTamanoMapa());
    }
    
    public void finBatalla(long startTime, int ganador, Usuario usuario, Computador computador){
        double minutos = 0, segundos = 0, mili = 0, exitoUser, exitoCompu, disparosAcertadosUser, disparosAcertadosCompu;
        
        disparosAcertadosUser = usuario.getContDisparos() - usuario.getDisparosFallidos();
        disparosAcertadosCompu = computador.getContDisparos() - computador.getDisparosFallidos();
        exitoUser = (disparosAcertadosUser/usuario.getContDisparos()) *100;
        exitoCompu = (disparosAcertadosCompu/computador.getContDisparos()) *100;
        
        switch(ganador){
            case 1: 
                System.out.println("Ganador: Usuario");
                break;
            default: 
                System.out.println("Ganador: Computador");
                break;
        } 
        if(((System.currentTimeMillis() - startTime)/1000) >=60){
        minutos = (int)((System.currentTimeMillis() - startTime)/1000) /60;
        segundos = (int)(((System.currentTimeMillis() - startTime)/1000) %60);
        mili = (((System.currentTimeMillis() - startTime)/1000) %100) %100;
        }
        else{
            segundos = (int)((System.currentTimeMillis() - startTime)/1000);
            mili = (int)(((System.currentTimeMillis() - startTime)/1000) %100);
        }
        System.out.println("Tiempo de Juego: "+minutos+" minutos "+segundos+" segundos "+mili+" milisegundos");
        System.out.println("Disparos acertados usuario: "+disparosAcertadosUser+"\nPorcentaje de éxito del usuario: "+exitoUser);
        System.out.println("Disparos acertados computador: "+disparosAcertadosCompu+"\nPorcentaje de éxito del computador: "+exitoCompu);
    }
    
    public void finCampana(long startTime, int victorias, Usuario usuario, Computador computador){
        double minutos = 0, segundos = 0, mili = 0;
        System.out.println("Número de victorias: "+victorias);
        System.out.println("Número de barcos destruidos: ");
        if(((System.currentTimeMillis() - startTime)/1000) >=60){
        minutos = (int)((System.currentTimeMillis() - startTime)/1000) /60;
        segundos = (int)(((System.currentTimeMillis() - startTime)/1000) %60);
        mili = (((System.currentTimeMillis() - startTime)/1000) %100) %100;
        }
        else{
            segundos = (int)((System.currentTimeMillis() - startTime)/1000);
            mili = (int)(((System.currentTimeMillis() - startTime)/1000) %100);
        }
        System.out.println("Tiempo: "+minutos+" minutos "+segundos+" segundos "+mili+" milisegundos");
    }
    
    public void aumentarVidas(Mapa mapa, int barcoAlQuePertenece, int parteBarcoAAumentar){
        int[] ubicacion = new int[3];
        int posicion, fila, columna;
        
        ubicacion=mapa.buscarBarco(mapa, barcoAlQuePertenece);
        posicion=ubicacion[0];
        fila=ubicacion[1];
        columna=ubicacion[2];
        
        if(posicion==0){ // Caso de que el barco esté ubicado verticalmente.
            int nuevaCantVida=mapa.getMapaComputador()[fila+parteBarcoAAumentar-1][columna].getCantVidas()+1;
            mapa.getMapaComputador()[fila+parteBarcoAAumentar-1][columna].setCantVidas(nuevaCantVida);
        } else { //Caso de que el barco esté ubicado horizontalmente.
            int nuevaCantVida=mapa.getMapaComputador()[fila][columna+parteBarcoAAumentar-1].getCantVidas()+1;
            mapa.getMapaComputador()[fila][columna+parteBarcoAAumentar-1].setCantVidas(nuevaCantVida);
        }
        
    }

    public int getGanador() {
        return ganador;
    }

    
}
