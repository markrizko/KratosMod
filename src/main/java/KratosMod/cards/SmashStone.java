package KratosMod.cards;

import KratosMod.characters.Kratos;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;

import static KratosMod.DefaultMod.makeCardPath;

public class SmashStone extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SmashStone.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("SmashStone.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.NONE;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = -2; // unplayable

    private static final int TOTAL = 5;
    private static final int UPGRADE_PLUS_TOTAL = 4;


    // /STAT DECLARATION/


    public SmashStone() { // public SmashStone() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        isEthereal = true;
        magicNumber = baseMagicNumber = TOTAL;
        //baseBUFF/BLOCK = 5;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.getRandomMonster(), AbstractDungeon.player, magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            upgradeMagicNumber(UPGRADE_PLUS_TOTAL);
        }
    }
}
