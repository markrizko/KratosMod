package SatanParlor.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import SatanParlor.DefaultMod;
import SatanParlor.util.TextureLoader;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static SatanParlor.DefaultMod.makeRelicOutlinePath;
import static SatanParlor.DefaultMod.makeRelicPath;

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
