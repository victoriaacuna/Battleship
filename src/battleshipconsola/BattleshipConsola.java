
package battleshipconsola;

//import java.util.Random;
import java.util.Scanner;


public class BattleshipConsola {
    public static Scanner sc = new Scanner(System.in);
    


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int tamanoMapa, tamanoBarcoUsuario, cantVidaUsuario, cantVidaComputador, siguienteCampana, nVictorias = 0;
        
        Menu menu = new Menu(1, 3);
        Mapa mapa = new Mapa(7);
        Usuario usuario = new Usuario(2, 3);
        Computador computador = new Computador(2, 1);
        Juego juego = new Juego(0, false);
 
        menu.bienvenida();
        menu.setModoJuego(menu.modoJuego());
        
        
       Menu.opcionesUsuario(menu, mapa, usuario);
       Mapa.crearMapas(mapa);
       usuario.setCantVida(usuario.cantVida(menu.getNivelDeDificultad()));
                
       BarcoSinHabilidad barcoUsuario= new BarcoSinHabilidad("No tiene habilidad.", usuario.getTamanoBarco(), 
            Barco.crearVectorBarco(usuario.getTamanoBarco(),usuario.getCantVida(), 1,0), false);
       BarcoSinHabilidad barcoTamano5= new BarcoSinHabilidad("No tiene habilidad.", 5, 
            Barco.crearVectorBarco(5,usuario.getCantVida(),1,1), false);
       BarcoRevelador barcoTamano4 = new BarcoRevelador(false, "Luego de 5 disparos fallidos, revela tres posibles"
            + "posiciones de un barco enemigo."
            + " enermigo", 4, Barco.crearVectorBarco(4,usuario.getCantVida(),5,2), false);
       BarcoVidaExtra barcoTamano3 = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
            + "una vida extra.", 3, Barco.crearVectorBarco(3,(usuario.getCantVida()+1),4,3), false);
       BarcoInvulnerable barco2Tamano3 = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
            + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3, usuario.getCantVida(), 3,4), false);
       BarcoQueSana barcoTamano2 = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
            + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
            + "consideren dañados.", 2, Barco.crearVectorBarco(2,usuario.getCantVida(),2,5), false);

       Barco.ubicarBarcosUsuario(usuario, mapa, menu, barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, 
            barco2Tamano3, barcoTamano2);

                
       computador.setCantVida(computador.cantVida(menu.getNivelDeDificultad()));
       computador.tamanoBarco(usuario.getTamanoBarco());

       BarcoSinHabilidad barcoCompu= new BarcoSinHabilidad("No tiene habilidad.", computador.getTamanoBarco(), 
           Barco.crearVectorBarco(computador.getTamanoBarco(),computador.getCantVida(),1,0), false);
       BarcoSinHabilidad barcoTamano5Compu= new BarcoSinHabilidad("No tiene habilidad.", 5, 
           Barco.crearVectorBarco(5,computador.getCantVida(),1,1), false);
       BarcoRevelador barcoTamano4Compu = new BarcoRevelador(false, "Luego de 5 disparos fallidos, revela "
           + "tres posibles posiciones de un barco enermigo", 4,Barco.crearVectorBarco(4,computador.getCantVida(),5,2), false);
       BarcoVidaExtra barcoTamano3Compu = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
           + "una vida extra.", 3, Barco.crearVectorBarco(3,computador.getCantVida()+1,4,3), false);
       BarcoInvulnerable barco2Tamano3Compu = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
           + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3,computador.getCantVida(),3,4), false);
       BarcoQueSana barcoTamano2Compu = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
           + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
           + "consideren dañados.", 2, Barco.crearVectorBarco(2,computador.getCantVida(),2,5), false);
                
       Barco.ubicarBarcosComputador(computador, mapa, usuario, menu, barcoCompu, barcoTamano5Compu, 
           barcoTamano4Compu, barcoTamano3Compu, barco2Tamano3Compu, barcoTamano2Compu);
               
//       ES MÁS FÁCIL PROBAR EL FUNCIONAMIENTO DEL JUEGO SI SE COLOCA ESTA INSTRUCCIÓN QUE SIRVE DE GUÍA. PERO ES SOLO PARA
//       PROBAR CÓMO CORRE EL PROGRAMA.


//       System.out.println("\n\nMAPA COMPU");
//       mapa.imprimirMapaUsuario(mapa.getMapaComputador(), computador.getCantVida(), mapa.getTamanoMapa());
//       System.out.println("\n\n");
                
       Juego juega = new Juego(mapa, computador, usuario, menu, barcoCompu, 
                        barcoTamano5Compu, barcoTamano4Compu, barcoTamano3Compu, barco2Tamano3Compu, barcoTamano2Compu,
                        barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3,barco2Tamano3, barcoTamano2);

       juega.finBatalla(startTime, juega.getGanador(), usuario, computador);
                
       

 // AVANCE DEL M[ETODO DE CAMPAÑA.
 
 
//            
//        switch(menu.getModoJuego()){
//            case 1:
//                Menu.opcionesUsuario(menu, mapa, usuario);
//                Mapa.crearMapas(mapa);
//                usuario.setCantVida(usuario.cantVida(menu.getNivelDeDificultad()));
//                
//                BarcoSinHabilidad barcoUsuario= new BarcoSinHabilidad("No tiene habilidad.", usuario.getTamanoBarco(), 
//                        Barco.crearVectorBarco(usuario.getTamanoBarco(),usuario.getCantVida(), 1,0), false);
//                BarcoSinHabilidad barcoTamano5= new BarcoSinHabilidad("No tiene habilidad.", 5, 
//                        Barco.crearVectorBarco(5,usuario.getCantVida(),1,1), false);
//                BarcoRevelador barcoTamano4 = new BarcoRevelador(false, "Luego de 10 disparos fallidos, revela tres posibles"
//                        + "posiciones de un barco enemigo."
//                        + " enermigo", 4, Barco.crearVectorBarco(4,usuario.getCantVida(),5,2), false);
//                BarcoVidaExtra barcoTamano3 = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
//                        + "una vida extra.", 3, Barco.crearVectorBarco(3,(usuario.getCantVida()+1),2,3), false);
//                BarcoInvulnerable barco2Tamano3 = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
//                        + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3, usuario.getCantVida(), 3,4), false);
//                BarcoQueSana barcoTamano2 = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
//                        + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
//                        + "consideren dañados.", 2, Barco.crearVectorBarco(2,usuario.getCantVida(),4,5), false);
//
//                Barco.ubicarBarcosUsuario(usuario, mapa, menu, barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, 
//                        barco2Tamano3, barcoTamano2);
//
//                
//                computador.setCantVida(computador.cantVida(menu.getNivelDeDificultad()));
//                computador.tamanoBarco(usuario.getTamanoBarco());
//
//                BarcoSinHabilidad barcoCompu= new BarcoSinHabilidad("No tiene habilidad.", computador.getTamanoBarco(), 
//                            Barco.crearVectorBarco(computador.getTamanoBarco(),computador.getCantVida(),1,0), false);
//                BarcoSinHabilidad barcoTamano5Compu= new BarcoSinHabilidad("No tiene habilidad.", 5, 
//                            Barco.crearVectorBarco(5,computador.getCantVida(),1,1), false);
//                BarcoRevelador barcoTamano4Compu = new BarcoRevelador(false, "Luego de 10 disparos fallidos, revela "
//                        + "tres posibles posiciones de un barco enermigo", 4,Barco.crearVectorBarco(4,computador.getCantVida(),5,2), false);
//                BarcoVidaExtra barcoTamano3Compu = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
//                            + "una vida extra.", 3, Barco.crearVectorBarco(3,computador.getCantVida()+1,4,3), false);
//                BarcoInvulnerable barco2Tamano3Compu = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
//                            + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3,computador.getCantVida(),3,4), false);
//                BarcoQueSana barcoTamano2Compu = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
//                            + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
//                            + "consideren dañados.", 2, Barco.crearVectorBarco(2,computador.getCantVida(),2,5), false);
//                
//                Barco.ubicarBarcosComputador(computador, mapa, usuario, menu, barcoCompu, barcoTamano5Compu, 
//                        barcoTamano4Compu, barcoTamano3Compu, barco2Tamano3Compu, barcoTamano2Compu);
//               
//                
//                System.out.println("\n\nMAPA COMPU");
//                mapa.imprimirMapaUsuario(mapa.getMapaComputador(), computador.getCantVida(), mapa.getTamanoMapa());
//                System.out.println("\n\n");
//                
//                Juego juega = new Juego(mapa, computador, usuario, menu, barcoCompu, 
//                        barcoTamano5Compu, barcoTamano4Compu, barcoTamano3Compu, barco2Tamano3Compu, barcoTamano2Compu,
//                        barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3,barco2Tamano3, barcoTamano2);
//
//                juega.finBatalla(startTime, juega.getGanador(), usuario, computador);
//                
//                if(menu.getModoJuego()==2){
//                    if(juega.getGanador()==1){
//                        nVictorias++;
//                        usuario.aumentarVidas(barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, barco2Tamano3, 
//                                barcoTamano2, juega, mapa);
//                        
//                    }
//                }
//                if(juegaCampana.getGanador() == 1){
//                    nVictorias++;
//                    usuario.aumentarVidas(ubarcoUsuarioCampana,barcoTamano5Campana, barcoTamano4Campana, barcoTamano3Campana, 
//                            barco2Tamano3Campana, barcoTamano2Campana, );
//                }
//                
//                }while(juego.getGanador() == 1);
//                
//                break;
//                
//            case 2: 
//                
//                Menu.opcionesUsuario(menu, mapa, usuario);
//                Mapa.crearMapas(mapa);
//                usuario.setCantVida(usuario.cantVida(menu.getNivelDeDificultad()));
//                BarcoSinHabilidad barcoUsuarioCampana= new BarcoSinHabilidad("No tiene habilidad.", usuario.getTamanoBarco(), 
//                        Barco.crearVectorBarco(usuario.getTamanoBarco(),usuario.getCantVida(), 1,0), false);
//                BarcoSinHabilidad barcoTamano5Campana= new BarcoSinHabilidad("No tiene habilidad.", 5, 
//                        Barco.crearVectorBarco(5,usuario.getCantVida(),1,1), false);
//                BarcoRevelador barcoTamano4Campana = new BarcoRevelador(false, "Luego de 5 disparos fallidos, revela tres posibles"
//                        + "posiciones de un barco enemigo."
//                        + " enermigo", 4, Barco.crearVectorBarco(4,usuario.getCantVida(),5,2), false);
//                BarcoVidaExtra barcoTamano3Campana = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
//                        + "una vida extra.", 3, Barco.crearVectorBarco(3,(usuario.getCantVida()+1),2,3), false);
//                BarcoInvulnerable barco2Tamano3Campana = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
//                        + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3, usuario.getCantVida(), 3,4), false);
//                BarcoQueSana barcoTamano2Campana = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
//                        + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
//                        + "consideren dañados.", 2, Barco.crearVectorBarco(2,usuario.getCantVida(),4,5), false);
//
//                Barco.ubicarBarcosUsuario(usuario, mapa, menu, barcoUsuarioCampana, barcoTamano5Campana, 
//                        barcoTamano4Campana, barcoTamano3Campana, barco2Tamano3Campana, barcoTamano2Campana);
//
//                
//                computador.setCantVida(computador.cantVida(menu.getNivelDeDificultad()));
//                computador.tamanoBarco(usuario.getTamanoBarco());
//
//                BarcoSinHabilidad barcoCompuCampana= new BarcoSinHabilidad("No tiene habilidad.", computador.getTamanoBarco(), 
//                            Barco.crearVectorBarco(computador.getTamanoBarco(),computador.getCantVida(),1,0), false);
//                BarcoSinHabilidad barcoTamano5CompuCampana= new BarcoSinHabilidad("No tiene habilidad.", 5, 
//                            Barco.crearVectorBarco(5,computador.getCantVida(),1,1), false);
//                BarcoRevelador barcoTamano4CompuCampana = new BarcoRevelador(false, "Luego de 5 disparos fallidos, revela "
//                        + "tres posibles posiciones de un barco enermigo", 4,Barco.crearVectorBarco(4,computador.getCantVida(),5,2), false);
//                BarcoVidaExtra barcoTamano3CompuCampana = new BarcoVidaExtra("Al iniciar la partida, todas sus partes tienen "
//                            + "una vida extra.", 3, Barco.crearVectorBarco(3,computador.getCantVida()+1,4,3), false);
//                BarcoInvulnerable barco2Tamano3CompuCampana = new BarcoInvulnerable(false, "Barco que al ser dañado, en el siguiente "
//                            + "turno, es invulnerable.", 3, Barco.crearVectorBarco(3,computador.getCantVida(),3,4), false);
//                BarcoQueSana barcoTamano2CompuCampana = new BarcoQueSana(false, "Barco cuyas partes dañadas van recuperando una "
//                            + "vida por turno, siempre que no sean atacados en dicho turno, hasta que ya no se "
//                            + "consideren dañados.", 2, Barco.crearVectorBarco(2,computador.getCantVida(),2,5), false);
//                
//                Barco.ubicarBarcosComputador(computador, mapa, usuario, menu, barcoCompuCampana, barcoTamano5CompuCampana, 
//                        barcoTamano4CompuCampana, barcoTamano3CompuCampana, barco2Tamano3CompuCampana, barcoTamano2CompuCampana);
//                
//                System.out.println("\n\nMAPA COMPU");
//                mapa.imprimirMapaUsuario(mapa.getMapaComputador(), computador.getCantVida(), mapa.getTamanoMapa());
//                System.out.println("\n\n");
//                do{
//                Juego juegaCampana = new Juego(mapa, computador, usuario, menu, barcoCompuCampana, 
//                        barcoTamano5CompuCampana, barcoTamano4CompuCampana, barcoTamano3CompuCampana, barco2Tamano3CompuCampana, barcoTamano2CompuCampana,
//                        barcoUsuarioCampana, barcoTamano5Campana, barcoTamano4Campana, barcoTamano3Campana,barco2Tamano3Campana, barcoTamano2Campana);
//                
//                juegaCampana.finBatalla(startTime, juegaCampana.getGanador(), usuario, computador);  
//                
//                if(juegaCampana.getGanador() == 1){
//                    nVictorias++;
//                    usuario.aumentarVidas(ubarcoUsuarioCampana,barcoTamano5Campana, barcoTamano4Campana, barcoTamano3Campana, 
//                            barco2Tamano3Campana, barcoTamano2Campana, );
//                }
//                
//                }while(juego.getGanador() == 1);
//   
//            default:
//                break;
//        }
        
        
        
        
    }
    
    

   
   
    
}
