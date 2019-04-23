
package battleshipconsola;

import java.util.Scanner;

public abstract class Jugador {
    protected int tamanoBarco, cantVida;
    protected Scanner sc = new Scanner(System.in);
    protected int contDisparos = 0, disparosFallidos=0;

    public int getContDisparos() {
        return contDisparos;
    }

    public void setContDisparos(int contDisparos) {
        this.contDisparos = contDisparos;
    }

    public int getDisparosFallidos() {
        return disparosFallidos;
    }

    public void setDisparosFallidos(int disparosFallidos) {
        this.disparosFallidos = disparosFallidos;
    }
    
    
    public Jugador(int tamanoBarco, int cantVida){
        this.tamanoBarco = tamanoBarco;
        this.cantVida = cantVida;
    }
    
    public int tamanoBarco(){
        int tamanoBarco;
        Scanner sc = new Scanner (System.in);
        
        do{
            System.out.println("Indique el tamaño de su sexto barco.(Puede tomar valores entre 2 y 7)");
            tamanoBarco= sc.nextInt();
            if(tamanoBarco>7 || tamanoBarco<2){
                System.out.println("¡Ups! Opción inválida.");
                tamanoBarco=8;
            }
        } while(tamanoBarco==8);
        return tamanoBarco;
    }
    
    public int cantVida(int dificultad){
        int cantVidaUsuario;
        switch(dificultad){
            case 1:
                cantVidaUsuario=3;
                break;
            case 2:
                cantVidaUsuario=2;
                break;
            default: 
                cantVidaUsuario=1;
                break;
        }
        return cantVidaUsuario;
    }

    public int getTamanoBarco() {
        return tamanoBarco;
    }

    public int getCantVida() {
        return cantVida;
    }

    public void setTamanoBarco(int tamanoBarco) {
        this.tamanoBarco = tamanoBarco;
    }

    public void setCantVida(int cantVida) {
        this.cantVida = cantVida;
    }
    
   public abstract void disparar(Mapa mapa, Usuario usuario, Computador computador, BarcoSinHabilidad barcoCompu, BarcoSinHabilidad barcoTamano5Compu, BarcoRevelador barcoTamano4Compu,
                    BarcoVidaExtra barcoTamano3Compu, BarcoInvulnerable barco2Tamano3Compu, BarcoQueSana barcoTamano2Compu);
   
   

    
    
}
