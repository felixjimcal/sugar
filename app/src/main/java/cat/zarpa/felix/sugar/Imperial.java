package cat.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public enum Imperial {
    oz(14);

    private final int id;

    Imperial(int identifier) {
        this.id = identifier;
    }

    public int getId() {
        return id;
    }

}
