package KratosMod.cards;

import KratosMod.actions.styxAction;
import KratosMod.characters.Kratos;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static KratosMod.DefaultMod.makeCardPath;

public class styx extends AbstractDynamicCard {


    /* RARE SKILL
        ( X ) Styx Cleanse: Lose X max HP, gain X intangible. NL Exhaust.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(styx.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("styx.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = -1;

    // /STAT DECLARATION/


    public styx() { // public styx() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        if (!upgraded){
            this.exhaust = true;
        }
        //baseBUFF/BLOCK = X;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new styxAction(p, this.upgraded, freeToPlayOnce, energyOnUse));
        if (energyOnUse != 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, energyOnUse), energyOnUse));
        }
        // action here
    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }
}
