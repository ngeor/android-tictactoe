package net.ngeor.t3;

import net.ngeor.t3.models.GameDto;
import net.ngeor.t3.models.GameModelListener;
import net.ngeor.t3.models.PlayerSymbol;
import net.ngeor.t3.settings.HumanPlayerDefinition;
import net.ngeor.t3.settings.PlayerDefinition;

public class GameListener implements GameModelListener {
    private final MainActivityView view;
    private final GameDto model;

    public GameListener(MainActivityView view, GameDto model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void stateChanged() {
        updateHeaderText();
        view.invalidateBoardView();
    }

    public void updateHeaderText() {
        int resourceId;

        switch (model.getState()) {
            case Draw:
                resourceId = R.string.state_game_over_draw;
                break;
            case Victory:
                if (isHumanTurn(model)) {
                    resourceId = R.string.state_game_over_human_wins;
                } else {
                    resourceId = R.string.state_game_over_cpu_wins;
                }

                break;
            case WaitingPlayer:
                resourceId = isHumanTurn(model) ? R.string.state_waiting_for_human : R.string.state_waiting_for_cpu;
                break;
            case NotStarted:
                resourceId = R.string.state_not_started;
                break;
            default:
                throw new IndexOutOfBoundsException();
        }

        view.setHeaderText(resourceId);
    }

    private boolean isHumanTurn(GameDto model) {
        PlayerSymbol turn = model.getTurn();
        for (PlayerDefinition playerDefinition : model.getSettings().getPlayerDefinitions()) {
            if (playerDefinition.getPlayerSymbol() == turn) {
                return playerDefinition instanceof HumanPlayerDefinition;
            }
        }

        throw new IllegalArgumentException("could not find player settings");
    }
}
