
package battleshipconsola;


public class Barco {
    
    protected String habilidad;
    protected int tamano;
    protected PartesBarco[] partesDelBarco;
    protected boolean destruido;
    
    public Barco(){
        
    }
    
     public Barco(String habilidad, int tamano, PartesBarco[] partesDelBarco, boolean destruido) {
        this.habilidad = habilidad;
        this.tamano = tamano;
        this.partesDelBarco = partesDelBarco;
        this.destruido=destruido;
    }
    
    public static PartesBarco[] crearVectorBarco(int tamanoBarco, int cantVida, int habilidad, int barcoAlQuePertenece){
        
        PartesBarco[] partesBarco1 = new PartesBarco[tamanoBarco];
        for(int i=0;i<partesBarco1.length;i++){
                partesBarco1[i]= new PartesBarco(cantVida, habilidad, true, false, barcoAlQuePertenece);
        }
        
        return partesBarco1;
    }
    

    
    public static Barco[] ordenarBarcosSegunTamano(BarcoSinHabilidad barcoUsuario, BarcoSinHabilidad barcoTamano5,
            BarcoRevelador barcoTamano4, BarcoVidaExtra barcoTamano3, BarcoInvulnerable barco2Tamano3, 
            BarcoQueSana barcoTamano2){
                            
        
        Barco[] vectorBarcosOrdenados = {barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, barco2Tamano3, barcoTamano2};
        Barco aux;
        
        for(int i=0; i<vectorBarcosOrdenados.length;i++){
            int imin=i;
            for(int j=i+1; j<vectorBarcosOrdenados.length;j++){
                if(vectorBarcosOrdenados[j].getTamano()<vectorBarcosOrdenados[imin].getTamano()){
                    imin=j;
                }
            }
            aux= vectorBarcosOrdenados[i];
            vectorBarcosOrdenados[i]=vectorBarcosOrdenados[imin];
            vectorBarcosOrdenados[imin]=aux;
        }
        
        return vectorBarcosOrdenados;
    }
    
    public static void ubicarBarcosComputador(Computador computador, Mapa mapa, Usuario usuario, Menu menu,
            BarcoSinHabilidad barcoCompu, BarcoSinHabilidad barcoTamano5Compu, BarcoRevelador barcoTamano4Compu,
                    BarcoVidaExtra barcoTamano3Compu, BarcoInvulnerable barco2Tamano3Compu, BarcoQueSana barcoTamano2Compu){

            Barco[] vectorBarcosOrdenadosCompu = new Barco[6];

            vectorBarcosOrdenadosCompu = ordenarBarcosSegunTamano(barcoCompu, barcoTamano5Compu, barcoTamano4Compu,
                    barcoTamano3Compu, barco2Tamano3Compu, barcoTamano2Compu);

            mapa.setMapaComputador(computador.ubicarBarcoComputador(mapa.getMapaComputador(), vectorBarcosOrdenadosCompu, 
                    mapa.getTamanoMapa(), 1, vectorBarcosOrdenadosCompu.length-1, false, mapa.getMapaComputadorOriginal(), 
                    barcoTamano2Compu, barcoTamano3Compu, barco2Tamano3Compu, barcoTamano4Compu, 
                    barcoTamano5Compu, barcoCompu));
            
           
    }
    
    public static void ubicarBarcosUsuario(Usuario usuario, Mapa mapa, Menu menu, BarcoSinHabilidad barcoUsuario, BarcoSinHabilidad barcoTamano5,
            BarcoRevelador barcoTamano4, BarcoVidaExtra barcoTamano3, BarcoInvulnerable barco2Tamano3, 
            BarcoQueSana barcoTamano2){
            
        Barco[] vectorBarcosOrdenados = new Barco[6];

        vectorBarcosOrdenados = ordenarBarcosSegunTamano(barcoUsuario, barcoTamano5, barcoTamano4, barcoTamano3, 
                barco2Tamano3, barcoTamano2);

        do{
            System.out.println("\nEl mapa donde ubicará sus barcos es el siguiente:\n");
            mapa.imprimirLeyendaParaUbicarBarcos();
            mapa.imprimirMapaUsuario(mapa.getMapaUsuario(), usuario.getCantVida(), mapa.getTamanoMapa());
            usuario.setReiniciarUbicacionBarcos(false);
            
            for(int i = 5; i>=0; i--){
            mapa.setMapaUsuario(usuario.ubicarBarcosUsuario(mapa.getMapaUsuario(), vectorBarcosOrdenados[i], mapa.getTamanoMapa()));
            mapa.imprimirLeyendaParaUbicarBarcos();
            mapa.imprimirMapaUsuario(mapa.getMapaUsuario(), usuario.getCantVida(), mapa.getTamanoMapa());
            usuario.setReiniciarUbicacionBarcos(usuario.reiniciarUbicacionBarcos());
            if(usuario.isReiniciarUbicacionBarcos()){
                // mapa.setMapaUsuario(mapa.getMapaUsuarioOriginal());
                
                for(int j=0; j<mapa.getMapaUsuario().length;j++){
                    for(int k=0; k<mapa.getMapaUsuario()[0].length; k++){
                        mapa.getMapaUsuario()[j][k]= mapa.getMapaUsuarioOriginal()[j][k];
                    }
                }
            
                    i=0;
                }
            }
                

        } while (usuario.isReiniciarUbicacionBarcos());
        
    }
    


    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public PartesBarco[] getPartesDelBarco() {
        return partesDelBarco;
    }

    public void setPartesDelBarco(PartesBarco[] partesDelBarco) {
        this.partesDelBarco = partesDelBarco;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }
    
   
    
    
}
