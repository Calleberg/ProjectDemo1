package tda367.operation5a.gamemode;
import tda367.operation5a.game.GameController;
import tda367.operation5a.game.GameModel;
import tda367.operation5a.game.GameView;

public class Testmode extends Gamemode {

	private static final long serialVersionUID = 1L;	//default serialID.

	@Override
	protected void init() {
		GameModel model = new GameModel();
		GameController ctrl = new GameController(model, 60);
		GameView view = new GameView(model, ctrl, this);
		model.addListener(view);
		this.add(view);
		//view.requestFocus();
	}
}
