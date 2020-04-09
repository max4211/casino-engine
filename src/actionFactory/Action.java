package actionFactory;

public abstract class Action implements ActionInterface {

    protected boolean takesCard;

    public Action() {

    }

    protected boolean isTakesCard() {
        return this.takesCard;
    }

}
