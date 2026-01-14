package ca.ulaval.lemes3.domain;

public class Listener {

    private ListenerId listenerId;

    public Listener(ListenerId listenerId) {
        this.listenerId = listenerId;
    }

    public ListenerId getId() {
        return this.listenerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final Listener other = (Listener) obj;
        return this.listenerId.equals(other.listenerId);
    }
}
