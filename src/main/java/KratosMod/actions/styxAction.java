package KratosMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class styxAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int magicNumber;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private boolean upgraded = false;

    public styxAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.p = p;
        actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            p.getRelic(ChemicalX.ID).flash();
        }
        if (effect > 0) {
            AbstractDungeon.player.decreaseMaxHealth(effect);
            // this is where magic happens
            if (!freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
        isDone = true;
    }
}
