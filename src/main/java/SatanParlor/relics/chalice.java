package SatanParlor.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import SatanParlor.DefaultMod;
import SatanParlor.util.TextureLoader;

import static SatanParlor.DefaultMod.makeRelicOutlinePath;
import static SatanParlor.DefaultMod.makeRelicPath;

public class chalice extends CustomRelic {


    // ID, images, text.
    public static final String ID = DefaultMod.makeID("chalice");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("chalice.png"));
    // private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("chalice.png"));

    public chalice() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.currentHealth > 1){
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,1, AbstractGameAction.AttackEffect.POISON));
        }
    }

    // on equip.
    @Override
    public void onEquip() {
        // AbstractDungeon.player.energy.energyMaster += 1; // gain 1 energy
    }

    // Lose buff on unequip.
    @Override
    public void onUnequip() {
        // AbstractDungeon.player.energy.energyMaster -= 1; // lose 1 energy
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
