package gestorAplicacion.usuario;

public enum comportamientoPago {
    BUENO(10),REGULAR(5),MALO(1);

    private int nivel;

    private comportamientoPago(int nivel) { this.nivel=nivel;	}

    private static final List<comportamientoPago> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static comportamientoPago randomNivel()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public void setNivel(int level) {
        this.nivel = level; }

    public int getNivel() {
        return this.nivel; }

}