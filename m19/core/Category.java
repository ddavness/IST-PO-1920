package m19.core;

/**
 * Category Enum
 */
enum Category {
    REFERENCE("Referência"),
    FICTION("Ficção"),
    SCITECH("Técnica e Científica");

    private String _name;
    private Category(String name) {
        _name = name;
    }

    public String toString() {
        return _name;
    }
}