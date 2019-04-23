
package battleshipconsola;

// @author victoriaacuna

import java.util.Random;


public class BarcoRevelador extends Barco{
    
    private boolean haRevelado;

    public boolean isHaRevelado() {
        return haRevelado;
    }

    public void setHaRevelado(boolean haRevelado) {
        this.haRevelado = haRevelado;
    }
    
    Random aleatorio = new Random();
    
    public BarcoRevelador(boolean haRevelado, String habilidad, int tamano, PartesBarco[] partesDelBarco, boolean destruido) {
        super(habilidad, tamano, partesDelBarco, destruido);
        this.haRevelado=haRevelado;
    }
    
    public void revelarPosicion(Mapa mapa){
        int i=0, j=0, random;
        boolean encontrado=false;
        
        while((i<mapa.getMapaComputador().length) && !encontrado){
            j=0;
            while((j<mapa.getMapaComputador()[0].length) && !encontrado){
                // Se busca en la matriz dónde hay un barco oculto.
                
                    if(!mapa.getMapaComputador()[i][j].isDisparoRecibido() && mapa.getMapaComputador()[i][j].isPerteneceBarco()){
                        System.out.println("¡Se ha activado la habilidad del Barco Revelador!");
                        System.out.println("El Barco Revelador te informa que es posible que exista un barco en las siguientes"
                                + "posiciones: \n(Estas están dadas en el formato F-C, donde el primer número corresponde a "
                                + "la fila y el segundo, a la columna)");
                                random=aleatorio.nextInt(3);
                                switch(random){
                                    case 0:
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        System.out.println((i+1)+"-"+(j+1));
                                        break;
                                    case 1: 
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        System.out.println((i+1)+"-"+(j+1));
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        break;
                                    default:
                                        System.out.println((i+1)+"-"+(j+1));
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        System.out.println((aleatorio.nextInt(mapa.getTamanoMapa()+1))+"-"+
                                                (aleatorio.nextInt(mapa.getTamanoMapa()+1)));
                                        break;
                                }
                                
                        encontrado=true;
                    }
                j++;
            }
            i++;
        }
            if(!encontrado){
            System.out.println("¡Se ha activado la habilidad del Barco Revelador!");
            System.out.println("¡El Barco Revelador te informa que has descubierto ya las posiciones de todos los barcos!");
            }
    }
    
    public int[] revelarPosicionParaDisparar(Mapa mapa){
        int[] posicion = new int[2];
        int i=0, j=0,random, random1, random2, random3, random4;
        boolean encontrado=false;
        
        while((i<mapa.getMapaUsuario().length) && !encontrado){
            j=0;
            while((j<mapa.getMapaUsuario()[0].length) && !encontrado){
                // Se busca en la matriz dónde hay un barco oculto.
                
                    if(!mapa.getMapaUsuario()[i][j].isDisparoRecibido() && mapa.getMapaUsuario()[i][j].isPerteneceBarco()){
                        System.out.println("¡Se ha activado la habilidad del Barco Revelador!");
                        System.out.println("El Barco Revelador le ha dicho a la computadora que debes tener un barco en alguna "
                                + "de las siguientes posiciones: \n(Estas están dadas en el formato F-C, donde el primer número corresponde a "
                                + "la fila y el segundo, a la columna)");
                        random1=aleatorio.nextInt(mapa.getTamanoMapa());
                        random2=aleatorio.nextInt(mapa.getTamanoMapa());
                        random3=aleatorio.nextInt(mapa.getTamanoMapa());
                        random4=aleatorio.nextInt(mapa.getTamanoMapa());
                        System.out.println((random1+1)+"-"+(random2+1));
                        System.out.println((i+1)+"-"+(j+1));
                        System.out.println((random3+1)+"-"+(random4+1));
                        
                        random=aleatorio.nextInt(3);
                                switch(random){
                                    case 0:
                                        posicion[0]=random1;
                                        posicion[1]=random2;
                                        break;
                                    case 1: 
                                        posicion[0]=i;
                                        posicion[1]=j;
                                        break;
                                    default:
                                        posicion[0]=random3;
                                        posicion[1]=random4;
                                        break;
                                }
                        
                        encontrado=true;
                        return posicion;
                    }
                j++;
            }
            i++;
        }
        
        if(!encontrado){
            System.out.println("¡Se ha activado la habilidad del Barco Revelador!");
            System.out.println("Sin embargo, ya ha descubierto todos tus barcos. Así que no pudo revelar nada.");
            posicion[0]=aleatorio.nextInt(mapa.getTamanoMapa());
            posicion[1]=aleatorio.nextInt(mapa.getTamanoMapa());
            return posicion;
            }
        return posicion;
    }
    
}
