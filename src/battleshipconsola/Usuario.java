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
public class Usuario extends Jugador{
    public static Scanner sc = new Scanner(System.in);
    private boolean reiniciarUbicacionBarcos;
    public Usuario(int tamanoBarco, int cantVida){
        super(tamanoBarco, cantVida);
    
    }
    
    public PartesBarco[][] ubicarBarcosUsuario(PartesBarco[][] mapaUsuario, Barco barco, int tamanoMapa){
        
        // Booleano que controla si se pudo ubicar el barco.
            boolean barcoUbicado=false;
        
        int i;
        
        do{

            // Booleano que controla si hay o no otra parte de barco donde el usuario quiere ubicar uno.
            boolean noHayOtroBarco = true;

            
            System.out.println("\nPor favor, ubique su barco de tamaño " + barco.getTamano() + " en el mapa.");
            System.out.println("Habilidad: " + barco.getHabilidad());
            System.out.println("Indique si desea ubicarlo [1] vertical u [2] horizontalmente.");
            int opUserPosicion = sc.nextInt();

            // Para validar la opción del usuario.
            if(opUserPosicion==1 || opUserPosicion==2){
                
                System.out.println("Indique la fila donde lo desea ubicar.");
                int opUserFila = sc.nextInt();

                // Para validar que la fila se encuentre dentro del mapa:
                if((opUserFila>0 && opUserFila<=tamanoMapa)){
                    
                    System.out.println("Indique la columna donde lo desea ubicar.");
                    int opUserColumna = sc.nextInt();

                    // Para validar que la columna se encuentre dentro del mapa.
                    if(opUserColumna>0 && opUserColumna<=tamanoMapa){
                        
                        // Para validar que en la coordenada propuesta por el usuario no se encuentre ningún barco.
                        if(!mapaUsuario[opUserFila-1][opUserColumna-1].isPerteneceBarco()){
                            
                            // Separación de casos, si eligió [OpUserPosicion=1] vertical u [OpUserPosicion=2] horizontal.
                            if(opUserPosicion==1){
                                
                                // Para validar si el barco no se sale de los bordes.
                                if((opUserFila+barco.getTamano()-1)<=mapaUsuario.length){
                                    
                                    // Validar si en las posiciones que el usuario desea ubicar el barco hay otros barcos.
                                    i=opUserFila-1;
                                    while(noHayOtroBarco && i<((opUserFila-1)+barco.getTamano())){
                                        if(mapaUsuario[i][opUserColumna-1].isPerteneceBarco()){
                                            noHayOtroBarco=false;
                                            System.out.println("Lo sentimos. En la fila " + (i+1) + " y columna " + opUserColumna + " hay un barco.");
                                        }
                                        
                                        i++;
                                    }


                                    // Si no hay ningún otro barco, se procede a ubicar el barco que desea el usuario.
                                    if (noHayOtroBarco){
                                        int j=0;
                                        for(int k=opUserFila-1; k<(opUserFila-1+barco.getTamano());k++){
                                            mapaUsuario[k][opUserColumna-1]=barco.partesDelBarco[j];
                                            j++;
                                        }
                                        barcoUbicado=true;
                                    }
                                    
                                } else {
                                    System.out.println("Lo sentimos. El barco es demasiado grande, si se coloca en esa coordenada, supera los bordes del mapa.");
                                }

                            } else {
                                
                                // Para validar que el barco no se salga de los bordes.
                                if((opUserColumna+barco.getTamano()-1)<=mapaUsuario[0].length){
                                    
                                    // Validar si en las posiciones que el usuario desea ubicar el barco hay otros barcos.

                                    i=opUserColumna-1;
                                    while(noHayOtroBarco && i<((opUserColumna-1)+barco.getTamano())){
                                        if(mapaUsuario[opUserFila-1][i].isPerteneceBarco()){
                                            noHayOtroBarco=false;
                                            System.out.println("Lo sentimos. En la fila " + opUserFila + " y columna " + (i+1) + " hay un barco.");
                                        }
                                        i++;
                                    }
                                    
                                    // Si no hay ningún otro barco, se procede a ubicar el barco que desea el usuario.
                                    if (noHayOtroBarco){
                                        int j=0;
                                        for(int k=opUserColumna-1; k<(opUserColumna-1+barco.getTamano());k++){
                                            mapaUsuario[opUserFila-1][k]=barco.partesDelBarco[j];
                                            j++;
                                        }
                                        barcoUbicado=true;
                                    }
                                
                                } else {
                                        System.out.println("Lo sentimos. El barco es demasiado grande, si se coloca en esa coordenada, supera los bordes del mapa.");
                                    }
                            }
                        } else {
                            System.out.println("Lo sentimos. En la fila " + opUserFila + " y columna " + opUserColumna + " hay un barco.");
                        } 
                    } else {
                        System.out.println("Lo sentimos. Esa columna no existe.");
                    }
                } else {
                    System.out.println("Lo sentimos. Esa fila no existe.");
                }

            } else {
                System.out.println("\nLo sentimos. Opción inválida.");
            }
            
            if(!barcoUbicado){
                System.out.println("Es imposible ubicar el barco en esa posición. Por favor, inténtelo de nuevo."
                        + "\nFíjese bien en las posiciones disponibles del mapa.");
            }
        } while(!barcoUbicado);
        return mapaUsuario;
    }
    
    public boolean reiniciarUbicacionBarcos(){
        
        Scanner sc = new Scanner(System.in);
        boolean reiniciarUbicacionBarcos = false, ejecutar=false;
        
        do{
            System.out.println("\n¿Desea reiniciar el proceso de ubicación de los barcos? [1] Sí. [2] No.");
            int opUser= sc.nextInt();
            if(opUser!=1 && opUser!=2){
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                ejecutar=true;
            } else {
                if (opUser==1){
                    reiniciarUbicacionBarcos=true;
                } else {
                    reiniciarUbicacionBarcos = false;
                }
                ejecutar=false;
            }
        } while(ejecutar);
        return reiniciarUbicacionBarcos;
    }

    public boolean isReiniciarUbicacionBarcos() {
        return reiniciarUbicacionBarcos;
    }

    public void setReiniciarUbicacionBarcos(boolean reiniciarUbicacionBarcos) {
        this.reiniciarUbicacionBarcos = reiniciarUbicacionBarcos;
    }
    
    @Override
    public void disparar(Mapa mapa, Usuario usuario, Computador computador, BarcoSinHabilidad barcoCompu, BarcoSinHabilidad barcoTamano5Compu, BarcoRevelador barcoTamano4Compu,
                    BarcoVidaExtra barcoTamano3Compu, BarcoInvulnerable barco2Tamano3Compu, BarcoQueSana barcoTamano2Compu){
       
       int fila, columna, nuevaCantVida, barcoAlQuePertenece=6, habilidad;
       boolean destruido=false;
       
       if(disparosFallidos%5==0 && disparosFallidos!=0){
            barcoTamano4Compu.revelarPosicion(mapa);
       }
       
       do{
         System.out.println("Ingrese el número de la fila en la que desea disparar");
         fila = sc.nextInt();  
         if(fila<0 || fila>(mapa.getTamanoMapa())){
             System.out.println("Coordenada inválida. Por favor, vuelva a intentarlo.");
         }
       } while(fila<0 || fila>(mapa.getTamanoMapa()));
       fila=fila-1;
       
       do{
           System.out.println("Ingrese el número de la columna en la que desea disparar");
           columna = sc.nextInt();
           if(columna<0 || columna>(mapa.getTamanoMapa())){
             System.out.println("Coordenada inválida. Por favor, vuelva a intentarlo.");
         }
       } while(columna<0 || columna>(mapa.getTamanoMapa()));
       columna=columna-1;
       
       if(mapa.getMapaComputador()[fila][columna].isPerteneceBarco()){
           // Si esa parte del mapa pertenece a un barco, procede a quitarle vidas si puede y a indicar que recibió un disparo.
           if(mapa.getMapaComputador()[fila][columna].getCantVidas()!=0){
               
               //Para el implemento de las habilidades...
               habilidad=mapa.getMapaComputador()[fila][columna].getHabilidad();
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
                           barco2Tamano3Compu.serInvulnerable(barco2Tamano3Compu, mapa, fila, columna,1, usuario, computador);
                       }
                       break;
                   default:
                       break;
                   
               }
               
               if(habilidad!=2 && barcoTamano2Compu.isSana()){
                   barcoTamano2Compu.Sanar(mapa, barcoTamano2Compu, 1, usuario, computador);
                }
               
            nuevaCantVida=mapa.getMapaComputador()[fila][columna].getCantVidas()-1;
            mapa.getMapaComputador()[fila][columna].setDisparoRecibido(true);
            mapa.getMapaComputador()[fila][columna].setCantVidas(nuevaCantVida);
            barcoAlQuePertenece=mapa.getMapaComputador()[fila][columna].getBarcoAlQuePertenece();
           
            
            if(habilidad != 3 && barco2Tamano3Compu.isInvulnerable()){
                barco2Tamano3Compu.setInvulneable(false);
            }
            if(habilidad==3 && !barco2Tamano3Compu.isInvulnerable()){
                if(mapa.getMapaComputador()[fila][columna].getCantVidas()==computador.getCantVida()){
                    mapa.getMapaComputador()[fila][columna].setDisparoRecibido(false);
                }
            }
            
            // Si esta parte se quedó sin vidas, pasa a evaluar si el barco puede estar destruido.
            if(nuevaCantVida==0){
                switch(barcoAlQuePertenece){
                    case 0:
                        destruido=mapa.elBarcoEstaDestruido(barcoCompu, 1);
                        if(destruido){
                            barcoCompu.setDestruido(destruido);
                        }
                        break;
                    case 1:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano5Compu, 1);
                        if(destruido){
                            barcoTamano5Compu.setDestruido(destruido);
                        }
                        break;
                    case 2:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano4Compu, 1);
                        if(destruido){
                            barcoTamano4Compu.setDestruido(destruido);
                        }
                        break;
                    case 3:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano3Compu, 1);
                        if(destruido){
                            barcoTamano3Compu.setDestruido(destruido);
                        }
                        break;
                    case 4:
                        destruido=mapa.elBarcoEstaDestruido(barco2Tamano3Compu, 1);
                        if(destruido){
                            barco2Tamano3Compu.setDestruido(destruido);
                        }
                        break;
                    default:
                        destruido=mapa.elBarcoEstaDestruido(barcoTamano2Compu, 1);
                        if(destruido){
                            barcoTamano2Compu.setDestruido(destruido);
                        }
                        break;    
                }
            }

            }
       } else {
           disparosFallidos++;
           if(barcoTamano2Compu.isSana()){
                barcoTamano2Compu.Sanar(mapa, barcoTamano2Compu, 1, usuario, computador);
            }
           mapa.getMapaComputador()[fila][columna].setDisparoRecibido(true);
       }  
       
       
       contDisparos++;
    }
    
    public void aumentarVidas(BarcoSinHabilidad barcoUsuarioCampana, BarcoSinHabilidad barcoTamano5Campana, 
            BarcoRevelador barcoTamano4Campana, BarcoVidaExtra barcoTamano3Campana, BarcoInvulnerable barco2Tamano3Campana, 
            BarcoQueSana barcoTamano2Campana,Juego juega, Mapa mapa){
        
        int disparosAcertados = contDisparos - disparosFallidos;
        int opBarco, opParte, opNuevo;
        boolean seguirAumentandoVidas=true;
        
        System.out.println("¡Ahora puedes aumentar la vida de las partes de tus barcos! \nPor cada disparo acertado, podrás aumentar 1 vida.");
        System.out.println("Disparos acertados: "+disparosAcertados);
        do{
            do{
            System.out.println("¿A qué barco desea aumentarle vida? Digite la opción de su preferencia y luego eliga la parte "
                    + "correspondiente que desea aumentar.");
            System.out.println("[1] Barco Tamaño "+barcoUsuarioCampana.getTamano()+" sin habilidad");
            System.out.println("[2] Barco Tamaño "+barcoTamano5Campana.getTamano()+" sin habilidad");
            System.out.println("[3] Barco Tamaño "+barcoTamano4Campana.getTamano()+" revelador");
            System.out.println("[4] Barco Tamaño "+barcoTamano3Campana.getTamano()+" con vida extra");
            System.out.println("[5] Barco Tamaño "+barco2Tamano3Campana.getTamano()+" invulnerable");
            System.out.println("[6] Barco Tamaño "+barcoTamano2Campana.getTamano()+" que sana");

            opBarco = sc.nextInt();

            if(opBarco!=1 && opBarco!=2 && opBarco!=3 && opBarco!=4 && opBarco!=5 && opBarco!=6){
                System.out.println("Opción inválida. Por favor selecciones una correcta.");
            }
            } while(opBarco!=1 && opBarco!=2 && opBarco!=3 && opBarco!=4 && opBarco!=5 && opBarco!=6);

            switch(opBarco){
                case 6:
                    do{
                        System.out.println("[1] Parte 1 [2] Parte 2");
                        opParte = sc.nextInt();
                        if(opParte !=1 && opParte!=2){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                        } while(opParte !=1 && opParte!=2);
                    juega.aumentarVidas(mapa, 5, opParte);
                    disparosAcertados--;
                    break;
                case 5:
                    do{
                        System.out.println("[1] Parte 1 [2] Parte 2 [3] Parte 3");
                        opParte = sc.nextInt();
                        if(opParte !=1 && opParte!=2 && opParte!=3){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                    } while(opParte !=1 && opParte!=2 && opParte!=3);
                    juega.aumentarVidas(mapa, 4, opParte);
                    disparosAcertados--;
                    break;
                case 4:
                    do{
                        System.out.println("[1] Parte 1 [2] Parte 2 [3] Parte 3");
                        opParte = sc.nextInt();
                        if(opParte !=1 && opParte!=2 && opParte!=3){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                    } while(opParte !=1 && opParte!=2 && opParte!=3);
                    juega.aumentarVidas(mapa, 3, opParte);
                    disparosAcertados--;
                    break;
                case 3:
                    do{
                        System.out.println("[1] Parte 1 [2] Parte 2 [3] Parte 3 [4] Parte 4");
                        opParte = sc.nextInt();
                        if(opParte<1 || opParte>4){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                    } while(opParte<1 || opParte>4);
                    juega.aumentarVidas(mapa, 2, opParte);
                    disparosAcertados--;
                    break;
                case 2:
                    do{
                        System.out.println("[1] Parte 1 [2] Parte 2 [3] Parte 3 [4] Parte 4 [5] Parte 5");
                        opParte = sc.nextInt();
                        if(opParte<1 || opParte>5){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                    } while(opParte<1 || opParte>5);
                    juega.aumentarVidas(mapa, 1, opParte);
                    disparosAcertados--;
                    break;
                default:
                    do{
                        System.out.println("Digite el número de la parte a aumentar.");
                        opParte = sc.nextInt();
                        if(opParte<1 || opParte>barcoUsuarioCampana.getTamano()){
                            System.out.println("Opción inválida. Por favor, selecciones una correcta.");
                        }
                    } while(opParte<1 || opParte>barcoUsuarioCampana.getTamano());
                    juega.aumentarVidas(mapa, 0, opParte);
                    disparosAcertados--;
                    break;

            }
            do{
                System.out.println("¿Desea aumentar otra vida de otra parte?\nLe quedan " +disparosAcertados+" vidas para repartir");
                System.out.println("[1] Sí [2] No.");
                opNuevo=sc.nextInt();
                if(opNuevo!=1 && opNuevo!=2){
                    System.out.println("Opción inválida. Elija una válida.");
                }
            }while(opNuevo!=1 && opNuevo!=2);
            
            if(opNuevo==2){
                seguirAumentandoVidas=false;
            }
            
            
        } while(disparosAcertados>0 && seguirAumentandoVidas);
        
    }

    
    
}
