package KratosMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import KratosMod.DefaultMod;
import KratosMod.util.TextureLoader;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static KratosMod.DefaultMod.makeRelicOutlinePath;
import static KratosMod.DefaultMod.makeRelicPath;

public class amulet extends CustomRelic {


    // ID, images, text.
    public static final String ID = DefaultMod.makeID("amulet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("amulet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("amulet.png"));

    public amulet() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }


    @Override
    public int onPlayerHeal(int healAmount) {
        flash();
        return super.onPlayerHeal(healAmount+1);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    public AbstractRelic makeCopy() {return new amulet();}

}
