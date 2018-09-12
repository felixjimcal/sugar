package cat.zarpa.felix.sugar;

/**
 * Created by ${Felix} on ${16//11/2017}.
 */
public enum Metric {
    ml (0),
    cl (1),
    dl (2),
    l (3),
    dal (4),
    hl (5),
    kl (6),
    kg (7),
    hg (8),
    dag (9),
    g (10),
    dg (11),
    cg (12),
    mg (13);

    private final int id;

    Metric(int identifier) {
        this.id = identifier;
    }

    public int getId() {
        return id;
    }

}
