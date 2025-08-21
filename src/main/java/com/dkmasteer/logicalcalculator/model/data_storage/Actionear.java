package com.dkmasteer.logicalcalculator.model.data_storage;

/**
 * @author daniilkrasnov
 * @date 20.08.2025 10:01
 */

public class Actionear {
    private static Actionear instance;
    private Action currentAction = Action.NOT_SELECTED;

    private Actionear() {
    }

    public static Actionear getInstance() {
        if (instance == null) {
            instance = new Actionear();
        }
        return instance;
    }

    public boolean isCell() {
        return (currentAction != Action.NOT_SELECTED
                && currentAction != Action.LOOKING_FOR_INPUT
                && currentAction != Action.DELETE);
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public void drop() {
        setCurrentAction(Action.NOT_SELECTED);
    }

    public void look() {
        setCurrentAction(Action.LOOKING_FOR_INPUT);
    }
}
