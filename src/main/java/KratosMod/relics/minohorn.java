package KratosMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import KratosMod.DefaultMod;
import KratosMod.util.TextureLoader;
import com.megacrit.cardcrawl.powers.DuplicationPower;


import static KratosMod.DefaultMod.makeRelicOutlinePath;
import static KratosMod.DefaultMod.makeRelicPath;

public class minohorn extends CustomRelic {


    // ID, images, text.
    public static final String ID = DefaultMod.makeID("minohorn");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("minohorn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("minohorn.png"));

    public minohorn() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    // Do whatever at the start of Battle.


    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DuplicationPower(AbstractDungeon.player, 1), 1));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
