package logica;

/**
 *
 * @author KA EduSoft
 */
public class TopArrayVentas {
    private final Venta[] listaVentas;
    private int tope;
    
    /**
     * Crea una lista vacía con una capacidad máxima igual a DEFAULT_ARRAY_LENGHT.
     */
    public TopArrayVentas(){
        this.listaVentas= new Venta[Constantes.DEFAULT_ARRAY_LENGTH];
        this.tope= -1;
    }
    
    /**
     * Crea una lista vacía con una capacidad máxima según la dada por el parámetro
     * capacidad. Si el parámetro es 0 o menor que 0, se establecerá en DEFAULT_ARRAY_LENGTH.
     * @param capacidad La capacidad máxima a asignar a la lista.
     */
    public TopArrayVentas(int capacidad){
        capacidad= (capacidad<=0) ? Constantes.DEFAULT_ARRAY_LENGTH : capacidad;
        this.listaVentas= new Venta[capacidad];
        this.tope= -1;
    }
    
    /**
     * Crea una lista con las personas pasadas como argumento ya agregadas. Además
     * se establece la capacidad máxima igual a capacidad. En caso de que dicho valor
     * sea menor que la cantidad de personas en lista, entonces se establecerá
     * dicha cantidad como capacidad máxima.
     * @param capacidad La capacidad máxima que se asignará a la lista.
     * @param lista Una lista inicial de personas.
     */
    public TopArrayVentas(int capacidad, Venta... lista){
        capacidad= (capacidad<lista.length) ? lista.length : capacidad;
        this.listaVentas= new Venta[capacidad];
        this.tope= -1;
        
        for(Venta v: lista){
            this.tope++;
            this.listaVentas[this.tope]= v;
        }
    }
    
    /**
     * Inserta la persona p al final de la lista.<br><br>
     * <b>PRECONDICIÓN</b>: La lista no está llena {@link esLlena}
     * @param v La persona a insertar.
     */
    public void insertar(Venta v){
        this.tope++;
        this.listaVentas[this.tope]= v;
    }
    
    /**
     * Inserta la persona p al inicio de la lista.<br><br>
     * <b>PRECONDICION</b>: La lista no está llena {@link esLlena}
     * @param v La persona a insetar.
     */
    public void insertarInicio(Venta v){
        this.insertarEn(v, 0);
    }
    
    /**
     * Inserta la persona p en el índice indicado.<br><br>
     * <b>PRECONDICIONES:</b><br>
     * <ul><li>La lista no está llena {@link esLlena}.</li>
     * <li>El índice es válido {@link esIndiceValido}.</li></ul>
     * @param c La persona a insertar.
     * @param indice La posición en que se insertará p.
     */
    public void insertarEn(Venta v, int indice){
        Venta aux[]= new Venta[this.listaVentas.length-1-indice];
        
        int auxI=0;
        for(int i= indice; i<=this.tope; i++){
            aux[auxI]= this.listaVentas[i];
            auxI++;
        }
        
        this.tope++;
        
        this.listaVentas[indice]= v;
        auxI=0;
        for(int i= indice+1; i<=this.tope; i++){
            this.listaVentas[i]= aux[auxI];
            auxI++;
        }
    }
    
    /**
     * Indica la capacidad máxima de la lista.
     * @return La capacidad máxima de la lista.
     */
    public int getCapacidad(){
        return this.listaVentas.length;
    }
    
    /**
     * Indica la cantidad de personas almacenadas hasta el momento.
     * @return La cantidad de personas almacenadas.
     */
    public int getCantidad(){
        return this.tope+1;
    }
    
    /**
     * Retorna la persona en el índice indicado. Los índices van de 0 hasta {@link getCantidad}-1.<br><br>
     * <b>PRECONDICIÓN</b>: El índice es válido según la operacion {@link esIndiceValido}
     * @param indice El índice de la persona buscada.
     * @return La persona en el índice indicado.
     */
    public Venta getVenta(int indice){
        return this.listaVentas[indice];
    }
    
    /**
     * Indica si el índice dado es válido para la lista en cuestión.
     * Los índices van de 0 hasta {@link getCantidad}-1.
     * @param indice El índice a validar.
     * @return TRUE si el índice es válido, FALSE si no lo es.
     */
    public boolean esIndiceValido(int indice){
        return (indice>=0) && (indice<=this.tope);
    }
    
    /**
     * Indica si la lista está completa.
     * @return TRUE si está llena y ya no se pueden agregar personas, FALSE si no.
     */
    public boolean esLlena(){
        return this.tope==this.listaVentas.length-1;
    }
    
    /**
     * Indica si la lista está vacía.
     * @return TRUE si la lista está vacía, FALSE si no.
     */
    public boolean esVacia(){
        return this.tope==-1;
    }
    
    /**
     * Quita la última persona de la lista.<br><br>
     * <b>PRECONDICIÓN</b>: La lista no está vacía {@link esVacia}.
     * @return La persona quitada.
     */
    public Venta quitarUltima(){
        Venta v= this.listaVentas[this.tope];
        this.listaVentas[this.tope]= null;
        this.tope--;
        return v;
    }
    
    /**
     * Quita la persona en la posición dada.<br><br>
     * <b>PRECONDICIÓN:</b> El índice es válido {@link esIndiceValido}.
     * @param indice El índice desde el cual se quitará la persona.
     * @return La persona quitada.
     */
    public Venta quitarEn(int indice){
        Venta p= this.listaVentas[indice];
        
        for (int i=indice; i<this.tope; i++){
            this.listaVentas[i]= this.listaVentas[i+1];
        }
        this.listaVentas[this.tope]= null;
        this.tope--;
        
        return p;
    }
    
    /**
     * Quita la persona al inicio de la lista.<br><br>
     * <b>PRECONDICIÓN:</b> La lista no está vacía {@link esVacia}.
     * @return  La persona quitada.
     */
    public Venta quitarPrimera(){
        return this.quitarEn(0);
    }
}
