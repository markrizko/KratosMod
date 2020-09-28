package SatanParlor.cards;

import SatanParlor.actions.temporAction;
import SatanParlor.powers.Vengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SatanParlor.DefaultMod;
import SatanParlor.characters.TheDefault;

import static SatanParlor.DefaultMod.makeCardPath;

public class Tempor extends AbstractDynamicCard {

    /* COMMON SKILL
        Gain 1 (+1) Vengeance. Discard the top 1 (+1) card of your draw pile.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Tempor.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Tempor.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int TOTAL = 1;
    private static final int UPGRADE_PLUS_TOTAL = 1;


    // /STAT DECLARATION/


    public Tempor() { // public Tempor() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseBUFF/BLOCK = 1;
        magicNumber = baseMagicNumber = TOTAL;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Vengeance(p, p, magicNumber), magicNumber));
        // action here
        // TODO FIX
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(magicNumber, new temporAction()));
        /*for (int i = 0; i < magicNumber; ++i){
            if (AbstractDungeon.player.drawPile.size() != 0){
                AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(AbstractDungeon.player.hand.getTopCard()));
            }
        } */
    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_TOTAL);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
