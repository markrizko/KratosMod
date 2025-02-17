package KratosMod.cards;

import KratosMod.characters.Kratos;
import KratosMod.powers.Vengeance;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static KratosMod.DefaultMod.makeCardPath;

public class Endure extends AbstractDynamicCard {

/*
COMMON SKILL
    ( 1 ) Endure: Gain 7 (+4) block. Draw 1 for each stack of Vengeance.
 */



    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Endure.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Endure.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int TOTAL = 7;
    private static final int UPGRADE_PLUS_TOTAL = 4;


    // /STAT DECLARATION/


    public Endure() { // public Endure() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseBUFF/BLOCK = 7;
        baseBlock = TOTAL;
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPower tmp = AbstractDungeon.player.getPower(Vengeance.POWER_ID);
        if (tmp != null){
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber = AbstractDungeon.player.getPower(Vengeance.POWER_ID).amount;
        }
        else{
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber = 0;
        }
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, block));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, defaultBaseSecondMagicNumber));
                // action here
    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeBlock(UPGRADE_PLUS_TOTAL);
            initializeDescription();
        }
    }
}
