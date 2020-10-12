package SatanParlor.powers;

/*
Power plays one specific card at the start of turn. 

*/


import SatanParlor.powers.RarePower;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import SatanParlor.DefaultMod;
import SatanParlor.cards.DefaultRareAttack;
import SatanParlor.util.TextureLoader;

import java.util.Iterator;

public class RattlePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("RattlePower");
    // don't forget to add power to powerstrings
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // make the textures mark
    private static final Texture tex84 = TextureLoader.getTexture("SatanParlorResources/images/powers/rattle84.png");
    private static final Texture tex32 = TextureLoader.getTexture("SatanParlorResources/images/powers/rattle32.png");

    public RattlePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        // buff/debuff
        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {// At the start of your turn
       if (amount == 1){
           flash();
           // random enemy
           if (Math.random() > 0.25){
               // apply weak
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerToRandomEnemyAction(owner,new WeakPower(owner,1,false), 1, true, AbstractGameAction.AttackEffect.NONE));
           }
           else{
               // -1 strength
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerToRandomEnemyAction(owner, new StrengthPower(owner, -1), -1, false, AbstractGameAction.AttackEffect.NONE));
//               AbstractDungeon.actionManager.addToBottom(new ApplyPowerToRandomEnemyAction(owner,new VulnerablePower(owner,1,false), 1, true, AbstractGameAction.AttackEffect.NONE));
           }
       }
       else{
           flash();
           // all enemies
           Iterator var1 = AbstractDungeon.getMonsters().monsters.iterator();
           if (Math.random() > 0.5){
               while(var1.hasNext()){
                   AbstractMonster m = (AbstractMonster)var1.next();
                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new WeakPower(owner, 1, false), 1));
               }
           }
           else{
               while(var1.hasNext()){
                   AbstractMonster m = (AbstractMonster)var1.next();
                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new StrengthPower(owner, -1), -1, false, AbstractGameAction.AttackEffect.NONE));
//                   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new VulnerablePower(owner, 1, false), 1));
               }
           }
       }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new RarePower(owner, source, amount);
    }
}
