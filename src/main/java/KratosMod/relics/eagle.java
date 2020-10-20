package KratosMod.relics;

import KratosMod.powers.Vengeance;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import KratosMod.DefaultMod;
import KratosMod.util.TextureLoader;

import static KratosMod.DefaultMod.makeRelicOutlinePath;
import static KratosMod.DefaultMod.makeRelicPath;

public class eagle extends CustomRelic {


    // ID, images, text.
    public static final String ID = DefaultMod.makeID("eagle");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("eagle.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("eagle.png"));

    public eagle() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Do whatever at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Vengeance(AbstractDungeon.player, AbstractDungeon.player,1), 1));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
