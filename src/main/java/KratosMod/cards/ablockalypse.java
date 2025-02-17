package KratosMod.cards;

import KratosMod.characters.Kratos;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static KratosMod.DefaultMod.makeCardPath;

public class ablockalypse extends AbstractDynamicCard {

    /*
    RARE SKILL
        ( 2 + 1 ) Ablockalypse: Gain 35 + 25 block. Gain 3 Vulnerable. Exhaust.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ablockalypse.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("ablockalypse.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 3;

    private static final int TOTAL = 35;
    private static final int UPGRADE_PLUS_TOTAL = 25;


    // /STAT DECLARATION/


    public ablockalypse() { // public ablockalypse() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseBUFF/BLOCK = 60;
        baseBlock = TOTAL;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new VulnerablePower(p,3, false), 5));
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
