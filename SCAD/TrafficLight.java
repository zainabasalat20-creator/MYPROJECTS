//State interface
interface State {
    void handle(Context context);
}

// Concrete State 1
class RedState implements State {
    public void handle(Context context) {
        System.out.println("Red Light – STOP");
        context.setState(new GreenState());
    }
}

// Concrete State 2
class GreenState implements State {
    public void handle(Context context) {
        System.out.println("Green Light – GO");
        context.setState(new YellowState());
    }
}

// Concrete State 3
class YellowState implements State {
    public void handle(Context context) {
        System.out.println("Yellow Light – SLOW");
        context.setState(new RedState());
    }
}

// Context
class Context {
    private State state;

    public Context() {
        state = new RedState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle(this);
    }
}

// Client
public class TrafficLight {
    public static void main(String[] args) {
        Context light = new Context();

        for (int i = 0; i < 5; i++) {
            light.request();
        }
    }
}  