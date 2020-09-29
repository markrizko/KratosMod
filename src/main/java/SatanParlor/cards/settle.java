package SatanParlor.cards;

import SatanParlor.characters.Kratos;
import SatanParlor.powers.Vengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SatanParlor.DefaultMod;
import SatanParlor.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LosePowerEffect;

import static SatanParlor.DefaultMod.makeCardPath;

public class settle extends AbstractDynamicCard {

    /* UNCOMMON SKILL
       ( 1 - 1 ) Settle: Lose 2 Vengeance. Gain 2 Energy. Exhaust.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(settle.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("settle.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int TOTAL = 2;
    private static int vcount;


    // /STAT DECLARATION/


    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPower tmp = AbstractDungeon.player.getPower(Vengeance.POWER_ID);
        if (tmp != null){
            vcount = AbstractDungeon.player.getPower(Vengeance.POWER_ID).amount;
        }
    }

    public settle() { // public settle() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        baseMagicNumber = magicNumber = TOTAL;
    }



    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (vcount < 2){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, Vengeance.POWER_ID));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new Vengeance(p, p, -magicNumber), -magicNumber));
        }

        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));

    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
