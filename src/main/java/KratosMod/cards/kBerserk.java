package KratosMod.cards;

import KratosMod.characters.Kratos;
import KratosMod.powers.kratosBerserk;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static KratosMod.DefaultMod.makeCardPath;

public class kBerserk extends AbstractDynamicCard {


    /* RARE POWER
        ( 0 ) Berserk: Gain 2 (-1) Vulnerable. At the start of your turn, gain 1 energy.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(kBerserk.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("kBerserk.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.POWER;       //
    // default color, change manually
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int MAGIC = 2;
    private static final int UPGRADED_PLUS_MAGIC = -1;

    // /STAT DECLARATION/


    public kBerserk() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, magicNumber, false), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new kratosBerserk(p, p, 1), 1));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_PLUS_MAGIC);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new kBerserk();
    }
}
