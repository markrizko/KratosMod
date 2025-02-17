package KratosMod.powers;

/*
Power plays one specific card at the start of turn.

Icarus Dive: Every 3 (5) turns, gain 1 evade.

*/


import KratosMod.DefaultMod;
import KratosMod.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IcarusDive extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    private int TURNS = 0;


    public static final String POWER_ID = DefaultMod.makeID("IcarusDive");
    // don't forget to add power to powerstrings
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // make the textures mark
    private static final Texture tex84 = TextureLoader.getTexture("KratosModResources/images/powers/IcarusDive84.png");
    private static final Texture tex32 = TextureLoader.getTexture("KratosModResources/images/powers/IcarusDive32.png");


    public IcarusDive(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        TURNS = amount;
        // buff/debuff
        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        flashWithoutSound();
        amount--;
        if (amount == 0){
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Evade(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
            amount = TURNS;
        }

    }

//    @Override
//    public void atStartOfTurn() {
//        super.atStartOfTurn();
//        flashWithoutSound();
//        amount--;
//        if (amount == 0){
//            flash();
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Evade(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
//            amount = TURNS;
//        }
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + TURNS + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new RarePower(owner, source, amount);
    }
}
