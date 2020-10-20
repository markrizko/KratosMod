package KratosMod.cards;

import KratosMod.characters.Kratos;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;

import static KratosMod.DefaultMod.makeCardPath;

// public class clawsofhades extends AbstractDynamicCard
public class clawsofhades extends AbstractDynamicCard {
    

    /*
    UNCOMMON ATTACK
        ( 2 ) Claws of Hades: Deal 10 (+5) damage. Heal unblocked damage dealt.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(clawsofhades.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("clawsofhades.png");// "public static final String IMG = makeCardPath("clawsofhades.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/


    public clawsofhades() { // public clawsofhades() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VampireDamageAction(m,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                // new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
