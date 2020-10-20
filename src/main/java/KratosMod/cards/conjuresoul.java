package KratosMod.cards;

import KratosMod.characters.Kratos;
import KratosMod.powers.conjure;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;

import static KratosMod.DefaultMod.makeCardPath;

public class conjuresoul extends AbstractDynamicCard {

    /*
    UNCOMMON POWER
        ( 2 ) Conjure Soul: At the start of your turn, heal 3 and lose 2 (-1) dexterity.
     */



    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(conjuresoul.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("conjuresoul.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.POWER;       //
    // default color, change manually
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int MAGIC = 2;
    private static final int UPGRADED_PLUS_MAGIC = -1;

    // /STAT DECLARATION/


    public conjuresoul() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new conjure(p, p, magicNumber), magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_PLUS_MAGIC);
            upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
