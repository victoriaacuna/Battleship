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
public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private int modoJuego, nivelDeDificultad, turno = 0;
    
    public Menu(int modoJueog, int nivelDeDificultad){
        this.modoJuego = modoJuego;
        this.nivelDeDificultad = nivelDeDificultad;
    }
    
    public void bienvenida(){
        System.out.println("¡Bienvenido a Battleship!");
    }
    
    public void iniciarJuego(){
        System.out.println("\n\n ¡COMENZÓ EL JUEGO!\n");
        System.out.println("Es importante que sepa que para cada turno se mostrará un primer mapa, correspondiente al suyo,"
                + "\ncon los barcos ubicados donde usted quiso, y una leyenda que indica el estado de cada barco.\n"
                + "Asimismo, se mostrará un segundo mapa, correspondiente al de su contrincante.\n");
        System.out.println("¡Qué gane el mejor!\n\n");
    }
    
    public int modoJuego(){
        int modoJuego;
        do{
            System.out.println("Seleccione el modo de juego de su preferencia.\n[1] Batalla.\n[2] Campaña.");
            modoJuego=sc.nextInt();
            
            if(modoJuego==2){
                System.out.println("Lo sentimos mucho. Esa versión todavía no está disponible, pero puede jugar una batalla.");
            }

            if(modoJuego!=1 && modoJuego!=2){
                System.out.println("¡Ups! Opción inválida.");
            }

        } while(modoJuego!=1 && modoJuego!=2);
        return modoJuego;
    }
    
    public int nivelDeDificultad(){
        int dificultad;
        do{
            System.out.println("Seleccione el nivel de dificultad.\n[1] Muy fácil.\n[2] Fácil.\n[3] Normal.\n[4] Difícil.\n[5] Muy difícil.");
            dificultad= sc.nextInt();
            if(dificultad>5 || dificultad<1){
                System.out.println("¡Ups! Opción inválida.");
            }
        } while(dificultad>5 || dificultad<1);
        return dificultad;
    }
    
    public static void opcionesUsuario(Menu menu, Mapa mapa, Usuario usuario){
        menu.setNivelDeDificultad(menu.nivelDeDificultad());
        mapa.setTamanoMapa(mapa.seleccionarMapa());
        usuario.setTamanoBarco(usuario.tamanoBarco());
    }
    
    public int turno(int turno){
        this.turno = turno;
        return turno;
    }

    public int getModoJuego() {
        return modoJuego;
    }

    public int getNivelDeDificultad() {
        return nivelDeDificultad;
    }

    public void setModoJuego(int modoJuego) {
        this.modoJuego = modoJuego;
    }

    public void setNivelDeDificultad(int nivelDeDificultad) {
        this.nivelDeDificultad = nivelDeDificultad;
    }
    
    
    
    
    
    
    
    
    
}
