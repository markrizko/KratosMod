package SatanParlor.relics;

import SatanParlor.patches.SatanTags;
import basemod.abstracts.CustomRelic;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import SatanParlor.DefaultMod;
import SatanParlor.util.TextureLoader;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import javax.swing.*;
import java.util.Iterator;

import static SatanParlor.DefaultMod.*;

/*
RAGE: For every blades of chaos in your deck, gain 2 strength at the start of combat.

 */

public class rage extends CustomRelic {


    // ID, images, text.
    public static final String ID = DefaultMod.makeID("rage");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rage.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("rage.png"));

    public rage() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.CLINK);
        this.counter = 0;
    }

    // Do whatever at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
        this.counter = countCards();
        // count n blade of chaos cards
        // gain n*3 strength at the start of battle
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, countCards()*2), 2));
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

    public AbstractRelic makeCopy() { return new rage(); }

    public static int countCards(){
        int count = 0;
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()){
            c = (AbstractCard)var1.next();
            if (c.hasTag(SatanTags.BLADES)){
                ++count;
            }
        }
        var1 = AbstractDungeon.player.drawPile.group.iterator();
        while(var1.hasNext()){
            c = (AbstractCard)var1.next();
            if (c.hasTag(SatanTags.BLADES)){
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();
        while(var1.hasNext()){
            c = (AbstractCard)var1.next();
            if (c.hasTag(SatanTags.BLADES)){
                ++count;
            }
        }
        logger.info("Number of blades: " + count);
        return count;
    }

}
