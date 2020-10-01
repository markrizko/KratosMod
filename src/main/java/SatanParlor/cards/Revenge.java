package SatanParlor.cards;

import SatanParlor.characters.Kratos;
import SatanParlor.powers.Vengeance;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SatanParlor.DefaultMod;
import SatanParlor.characters.TheDefault;

import static SatanParlor.DefaultMod.makeCardPath;

public class Revenge extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Revenge.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Revenge.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    private static final int TOTAL = 1;
    private static final int UPGRADE_PLUS_TOTAL = 1;


    // /STAT DECLARATION/


    public Revenge() { // public Revenge() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        misc = 1;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = misc;
        magicNumber = baseMagicNumber = TOTAL;
        exhaust = true;
    }


    @Override
    public void applyPowers() {
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = misc;
        super.applyPowers();
        initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(uuid, misc, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Vengeance(p, p, defaultSecondMagicNumber), defaultSecondMagicNumber));
                // action here
    }


    // Upgraded stats.
    // DON'T FORGET TO ADD UPGRADE BLOCK OR BUFF HERE
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
